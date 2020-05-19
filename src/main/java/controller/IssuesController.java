package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import com.google.inject.Guice;
import com.google.inject.Injector;
import issue.IssueDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import issue.Issue;
import lombok.extern.slf4j.Slf4j;

import guice.PersistenceModule;

import javax.persistence.PersistenceException;

/**
 * @author Csipkes Gergo
 * @author csipkesgeri@gmail.com
 * @version 0.3
 *
 * This class is the controller of the {@link Issue} and the {@code issues.xml}.
 */
@Slf4j
public class IssuesController implements Initializable {
    // Egy lista, ami objektumot tárol
    // POJO (Issue objektumok)
    /**
     * An ObservableList contains the {@link Issue}s.
     */
    private ObservableList<Issue> allIssues = FXCollections.observableArrayList();
    private ObservableList<Issue> unfinishedIssues = FXCollections.observableArrayList();

    private final String MENU_ISSUES = "Issues";
    private final String MENU_LIST = "All issues";
    private final String MENU_UNFINISHED = "Unfinished issues";
    private final String MENU_EXIT = "Exit";

    //private IssueDao issueDao;
    Injector injector = Guice.createInjector(new PersistenceModule("jpa-persistence-unit-1"));
    IssueDao issueDao = injector.getInstance(IssueDao.class);

    @FXML
    StackPane menuPane;

    @FXML
    Pane unfinishedIssuePane;
    @FXML
    TableView unfinishedIssueTable;

    @FXML
    Pane allIssuePane;
    @FXML
    TableView allIssueTable;

    @FXML
    Pane newIssuePane;
    @FXML
    TextField inputTitle;
    @FXML
    TextArea inputDetails;
    @FXML
    Button saveIssueButton;

    @FXML
    Pane alertPane;
    @FXML
    Button alertButton;

    /**
     * This function is for test purpose.
     * Create a predefined {@code issue}.
     *
     * @return Issue A predefined {@code issue}.
     */
    private Issue createIssue() {
        return new Issue(1, "First issue", "Issue detail");
    }

    /**
     * This function is for test purpose.
     * Connect to the DB and create an issue.
     */
    private void initDB() {
        Issue issue = createIssue();
        issueDao.persist(issue);
        issueDao.persist(issue);
        issueDao.persist(issue);
        allIssues.add(issue);
        unfinishedIssues.add(issue);
        log.info("A new issue is save to the database" + issue);
    }


    /**
     * Connect to the DB and get all issues.
     */
    private void getAllIssuesFromDB() {
        try {
            // ArrayList cast to ObservalbeList
            allIssues = FXCollections.observableArrayList(issueDao.findAll());
            log.debug("Get all issues from DB" + allIssues);
        } catch (Exception ex) {
            log.error("Could not read issues from the DB");
        }
    }

    /**
     * Connect to the DB and get the unfinished issues.
     */
    private void getUnfinishedIssuesFromDB() {
        try {
            // ArrayList cast to ObservalbeList
            unfinishedIssues = FXCollections.observableArrayList(issueDao.findUnfinished());
            log.debug("Get unfinished issues from DB" + unfinishedIssues);
        } catch (Exception ex) {
            log.error("Could not read unfinished issues from the DB");
        }
    }


    /**
     * Set the common parts of the tables (Title, Details, Date created).
     *
     * @return Vector<TableColumn> Vector of TableColumns
     */
    private Vector<TableColumn> setTableCommonColumn() {
        Vector<TableColumn> tableColumns = new Vector<>();

        TableColumn titleCol = new TableColumn("Title");
        titleCol.setMinWidth(100);
        // Ebben az oszlopban minden cellának textfield legyen a tartalma
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // PropertyValueFactory<POJO neve, milyen típussal jelenítsük meg az értéket, amit ki akarunk venni>("milyen néven találja az értéket")
        titleCol.setCellValueFactory(new PropertyValueFactory<Issue, String>("title"));

        // Táblázatban a címre kattintva módosítva mi történjen
        titleCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Issue, String>>) t -> {
                    Issue issue = (Issue) t.getTableView().getItems().get(t.getTablePosition().getRow());
                    try {
                        issue.setTitle(t.getNewValue());
                        issueDao.update(issue);
                    } catch (Exception ex) {
                        log.error("The update of the issue was failed." + issue.toString());
                    }
                }
        );
        tableColumns.add(titleCol);

        TableColumn detailsCol = new TableColumn("Details");
        detailsCol.setMinWidth(500);
        detailsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        detailsCol.setCellValueFactory(new PropertyValueFactory<Issue, String>("details"));

        // Táblázatban a detailre kattintva módoítva mi történjen
        detailsCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Issue, String>>) t -> {
                    Issue issue = (Issue) (t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    try {
                        issue.setDetails(t.getNewValue());
                        issueDao.update(issue);
                    } catch (Exception ex) {
                        log.error("The update of the issue was failed." + issue.toString());
                    }
                }
        );
        tableColumns.add(detailsCol);

        TableColumn dateCreatedCol = new TableColumn("Date created");
        dateCreatedCol.setMinWidth(150);
        // Ebben az oszlopban minden cellának textfield legyen a tartalma
        dateCreatedCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // PropertyValueFactory<POJO neve, milyen típussal jelenítsük meg az értéket, amit ki akarunk venni>("milyen néven találja az értéket")
        dateCreatedCol.setCellValueFactory(new PropertyValueFactory<Issue, String>("dateCreated"));

        // Táblázatban a Date createdre kattintva módosítva mi történjen
        dateCreatedCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Issue, String>>) t -> {
                    Issue issue = (Issue) t.getTableView().getItems().get(t.getTablePosition().getRow());
                    try {
                        issue.setDateCreated(t.getNewValue());
                        issueDao.update(issue);
                    } catch (Exception ex) {
                        log.error("The update of the issue was failed." + issue.toString());
                    }
                }
        );
        tableColumns.add(dateCreatedCol);

        return tableColumns;
    }


    /**
     * Initialize the table which shows all of the issues.
     * Set the columns and fill it with data from the database.
     */
    private void setAllIssuesTableData() {
        Vector<TableColumn> tableColumns = setTableCommonColumn();

        // We need one more column for the date of finished data
        TableColumn dateFinishedCol = new TableColumn("Date finished");
        dateFinishedCol.setMinWidth(150);
        // Ebben az oszlopban minden cellának textfield legyen a tartalma
        dateFinishedCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // PropertyValueFactory<POJO neve, milyen típussal jelenítsük meg az értéket, amit ki akarunk venni>("milyen néven találja az értéket")
        dateFinishedCol.setCellValueFactory(new PropertyValueFactory<Issue, String>("dateFinished"));

        // Táblázatban a Date finishedre kattintva módosítva mi történjen
        dateFinishedCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Issue, String>>) t -> {
                    Issue issue = (Issue) t.getTableView().getItems().get(t.getTablePosition().getRow());
                    try {
                        issue.setDateFinished(t.getNewValue());
                        issueDao.update(issue);
                        unfinishedIssues.remove(issue);
                    } catch (Exception ex) {
                        issue.setDateFinished(t.getOldValue());
                        log.error("The update of the issue was failed." + issue.toString());
                    }

                }
        );
        tableColumns.add(dateFinishedCol);

        tableColumns.forEach((tableColumn ->
                allIssueTable.getColumns().addAll(tableColumn)
        ));

        // Read data from DB and write it to the table
        getAllIssuesFromDB();
        allIssueTable.setItems(allIssues);
    }


    /**
     * Initialize the table which shows all of the issues where the finished date is empty.
     * Set the columns and fill it with data from the database.
     * Unfinished issue is where the finished date is empty.
     */
    private void setUnfinishedIssuesTableData() {
        Vector<TableColumn> tableColumns = setTableCommonColumn();
        tableColumns.forEach((tableColumn ->
                unfinishedIssueTable.getColumns().addAll(tableColumn)
                ));

        getUnfinishedIssuesFromDB();

        unfinishedIssueTable.setItems(unfinishedIssues);
    }


    /**
     * Initialize the menu in the left side.
     */
    private void setMenuData() {
        TreeItem<String> treeItemRoot1 = new TreeItem<>("Menu");
        TreeView<String> treeView = new TreeView<>(treeItemRoot1);
        treeView.setShowRoot(false);

        TreeItem<String> nodeItemA = new TreeItem<>(MENU_ISSUES);
        TreeItem<String> nodeItemB = new TreeItem<>(MENU_EXIT);

        nodeItemA.setExpanded(true);

        Node issuesNode = new ImageView(new Image(getClass().getResourceAsStream("/img/icons/list.png")));
        Node unfinishedNode = new ImageView(new Image(getClass().getResourceAsStream("/img/icons/unfinished.png")));

        TreeItem<String> nodeItemA1 = new TreeItem<>(MENU_LIST, issuesNode);
        TreeItem<String> nodeItemB1 = new TreeItem<>(MENU_UNFINISHED, unfinishedNode);

        nodeItemA.getChildren().addAll(nodeItemA1, nodeItemB1);
        treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB);

        menuPane.getChildren().add(treeView);

        // A menü egy kattintással lenyíljon
        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                String selectedMenu;
                // Itt a value a Menü nevét tárolja
                selectedMenu = selectedItem.getValue();
                //System.out.println(selectedMenu);

                if (null != selectedMenu) {
                    if (selectedMenu == MENU_ISSUES) {
                        selectedItem.setExpanded(true);
                    } else if(selectedMenu ==  MENU_LIST) {
                        unfinishedIssuePane.setVisible(false);
                        allIssuePane.setVisible(true);
                    } else if(selectedMenu ==  MENU_UNFINISHED) {
                        allIssuePane.setVisible(false);
                        unfinishedIssuePane.setVisible(true);
                    } else if(selectedMenu ==  MENU_EXIT) {
                        System.exit(0);
                    }
                }
            }
        });

    }


    /**
     * Save an {@code Issue} to the database and display it in the tables.
     *
     * @param event
     * @throws PersistenceException When could not save the {@code issue} in the database.
     */
    @FXML
    private void saveIssue(ActionEvent event) {
        try {
            if (inputTitle.getText().length() > 3 &&  inputDetails.getText().length() > 3) {
                // Mentjük az adatbázisba
                Issue issue = new Issue(
                        1,
                        inputTitle.getText(),
                        inputDetails.getText()
                );
                issueDao.persist(issue);
                log.info("A new issue is save to the database" + issue);

                // Hozzáadjuk az ObservableList-hez
                allIssues.add(issue);
                unfinishedIssues.add(issue);
                inputTitle.clear();
                inputDetails.clear();
            } else {
                alertPane.setVisible(true);
                allIssuePane.setDisable(true);
                unfinishedIssuePane.setDisable(true);
                menuPane.setDisable(true);
                newIssuePane.setDisable(true);
                allIssuePane.setOpacity(30);
                unfinishedIssuePane.setOpacity(30);
                menuPane.setOpacity(30);
                // TODO show a dialog with a message
            }
        } catch(PersistenceException ex) {
            log.error("Failed to save issue to the database." + ex);
        }
    }


    /**
     * When a new issue creation called with not valid parameters this action runs.
     * It set the other panes an opacity, disable them and display a pop up pane to inform the user about the error.
     *
     * @param event
     */
    @FXML
    public void popupAlertPane(ActionEvent event) {
        alertPane.setVisible(false);
        allIssuePane.setDisable(false);
        unfinishedIssuePane.setDisable(false);
        menuPane.setDisable(false);
        newIssuePane.setDisable(false);
        allIssuePane.setOpacity(100);
        unfinishedIssuePane.setOpacity(100);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initDB();

        setMenuData();

        setAllIssuesTableData();

        setUnfinishedIssuesTableData();
    }


}
