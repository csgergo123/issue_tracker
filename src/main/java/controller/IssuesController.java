package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;

import guice.PersistenceModule;

@Slf4j
public class IssuesController implements Initializable {
    // Egy lista, ami objektumot tárol
    // POJO (Issue objektumok
    private ObservableList<Issue> data = FXCollections.observableArrayList();

    private final String MENU_ISSUES = "Issues";
    private final String MENU_LIST = "All issues";
    private final String MENU_UNFINISHED = "Unfinihed issues";
    private final String MENU_EXIT = "Exit";

    private IssueDao issueDao;

    @FXML
    TableView table;
    @FXML
    TextField inputTitle;
    @FXML
    TextArea inputDetails;
    @FXML
    Button saveIssueButton;
    @FXML
    StackPane menuPane;
    @FXML
    Pane issuePane;
    @FXML
    Pane unfinishedPane;
    @FXML
    TextField inputExport;
    @FXML
    Button exportButton;


    private Issue createIssue() {
        return new Issue(1, "First issue", "Issue detail");
    }

    /**
     * Connect to the DB and create an issue.
     * @return
     */
    private void initDB() {
        Injector injector = Guice.createInjector(new PersistenceModule("jpa-persistence-unit-1"));
        IssueDao issueDao = injector.getInstance(IssueDao.class);
        Issue issue = createIssue();
        issueDao.persist(issue);
        data.add(issue);
        log.info("A new issue is save to the database" + issue);
    }

    /**
     * Connect to the DB and read the values.
     * @return
     */
    private void readFromDB() {
        Injector injector = Guice.createInjector(new PersistenceModule("jpa-persistence-unit-1"));
        IssueDao issueDao = injector.getInstance(IssueDao.class);
        // ArrayList cast to ObservalbeList
        data = FXCollections.observableArrayList(issueDao.findAll());
    }

    /**
     * Initialize the issues table data
     */
    private void setTableData() {
        TableColumn titleCol = new TableColumn("Title");
        titleCol.setMinWidth(100);
        // Ebben az oszlopban minden cellának textfield legyen a tartalma
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // PropertyValueFactory<POJO neve, milyen típussal jelenítsük meg az értéket, amit ki akarunk venni>("milyen néven találja az értéket")
        titleCol.setCellValueFactory(new PropertyValueFactory<Issue, String>("title"));

        // Táblázatban a címre kattintva módoítva mi történjen
        titleCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Issue, String>>) t -> ((Issue) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setTitle(t.getNewValue())
        );

        TableColumn detailsCol = new TableColumn("Details");
        detailsCol.setMinWidth(500);
        detailsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        detailsCol.setCellValueFactory(new PropertyValueFactory<Issue, String>("details"));

        // Táblázatban a detailre kattintva módoítva mi történjen
        detailsCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Issue, String>>) t -> ((Issue) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setDetails(t.getNewValue())
        );

        TableColumn dateCreatedCol = new TableColumn("Date created");
        dateCreatedCol.setMinWidth(150);
        // Ebben az oszlopban minden cellának textfield legyen a tartalma
        dateCreatedCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // PropertyValueFactory<POJO neve, milyen típussal jelenítsük meg az értéket, amit ki akarunk venni>("milyen néven találja az értéket")
        dateCreatedCol.setCellValueFactory(new PropertyValueFactory<Issue, String>("dateCreated"));

        // Táblázatban a címre kattintva módoítva mi történjen
        dateCreatedCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Issue, String>>) t -> ((Issue) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setTitle(t.getNewValue())
        );

        TableColumn dateFinishedCol = new TableColumn("Date finished");
        dateFinishedCol.setMinWidth(150);
        // Ebben az oszlopban minden cellának textfield legyen a tartalma
        dateFinishedCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // PropertyValueFactory<POJO neve, milyen típussal jelenítsük meg az értéket, amit ki akarunk venni>("milyen néven találja az értéket")
        dateFinishedCol.setCellValueFactory(new PropertyValueFactory<Issue, String>("dateFinished"));

        // Táblázatban a címre kattintva módoítva mi történjen
        dateFinishedCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Issue, String>>) t -> ((Issue) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setTitle(t.getNewValue())
        );

        table.getColumns().addAll(titleCol, detailsCol, dateCreatedCol, dateFinishedCol);
        table.setItems(data);
    }


    /**
     * Initialize the menu in the left side
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
                        unfinishedPane.setVisible(false);
                        issuePane.setVisible(true);
                    } else if(selectedMenu ==  MENU_UNFINISHED) {
                        issuePane.setVisible(false);
                        unfinishedPane.setVisible(true);
                    } else if(selectedMenu ==  MENU_EXIT) {
                        System.exit(0);
                    }
                }
            }
        });

    }

    @FXML
    private void saveIssue(ActionEvent event) {
        try {
            if (inputTitle.getText().length() > 3 &&  inputDetails.getText().length() > 3) {
                // Mentjük az adatbázisba
                Injector injector = Guice.createInjector(new PersistenceModule("jpa-persistence-unit-1"));
                IssueDao issueDao = injector.getInstance(IssueDao.class);
                Issue issue = new Issue(
                        1,
                        inputTitle.getText(),
                        inputDetails.getText()
                );
                issueDao.persist(issue);
                log.info("A new issue is save to the database" + issue);

                // Hozzáadjuk az ObservableList-hez
                data.add(issue);
                inputTitle.clear();
                inputDetails.clear();
            }
        } catch(Exception ex) {
            log.error("Failed to save issue to database.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //issueDao = IssueDao.getInstance();

        //initDB();
        readFromDB();
        setTableData();
        setMenuData();
    }


}
