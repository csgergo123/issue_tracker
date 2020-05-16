package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class IssuesController implements Initializable {

    Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
    jdbi.installPlugin(new SqlObjectPlugin());

    // TODO ObservableList
    private final List<Issue> data = jdbi.withExtension(IssueDao.class, dao -> {
        dao.createTable();
        dao.insertIssue(new Issue(1, "First issue", "Issue detail"));
        dao.insertIssue(new Issue(1, "Second issue", "Issue detail"));
        dao.insertIssue(new Issue(1, "Merge bugfix", "Merge detail"));
        //System.out.println(dao.getIssue("75211").get());
        dao.listIssues().stream().forEach(System.out::println);

        return dao.listIssues();
    });

    // Egy lista, ami objektumot tárol
    // POJO (Issue objektumok)
    /*
    private final ObservableList<Issue> data =
        FXCollections.observableArrayList(
                new Issue(1, "First issue", "Issue detail"),
                new Issue(1, "Second issue", "Issue detail"),
                new Issue(1, "Merge bugfix", "Merge detail")
        );
     */

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
    Pane exportPane;
    @FXML
    TextField inputExport;
    @FXML
    Button exportButton;

    private final String MENU_ISSUES = "Issues";
    private final String MENU_LIST = "List";
    private final String MENU_EXPORT = "Export";
    private final String MENU_EXIT = "Exit";


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

        table.getColumns().addAll(titleCol, detailsCol);
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
        Node exportNode = new ImageView(new Image(getClass().getResourceAsStream("/img/icons/export.png")));

        TreeItem<String> nodeItemA1 = new TreeItem<>(MENU_LIST, issuesNode);
        TreeItem<String> nodeItemB1 = new TreeItem<>(MENU_EXPORT, exportNode);

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
                    switch (selectedMenu) {
                        case MENU_ISSUES:
                            selectedItem.setExpanded(true);
                            break;
                        case MENU_LIST:
                            exportPane.setVisible(false);
                            issuePane.setVisible(true);
                            break;
                        case MENU_EXPORT:
                            issuePane.setVisible(false);
                            exportPane.setVisible(true);
                            break;
                        case MENU_EXIT:
                            System.exit(0);
                            break;
                    }
                }
            }
        });

    }

    @FXML
    private void saveIssue(ActionEvent event) {
        if (inputTitle.getText().length() > 3 &&  inputDetails.getText().length() > 3) {
            // Hozzáadjuk az ObservableList-hez
            data.add(new Issue(
                    1,
                    inputTitle.getText(),
                    inputDetails.getText()
            ));
            inputTitle.clear();
            inputDetails.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableData();
        setMenuData();
    }


}
