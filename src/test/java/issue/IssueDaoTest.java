package issue;

import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.PersistenceModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;


public class IssueDaoTest {
    private ObservableList<Issue> data = FXCollections.observableArrayList();

    Issue issue = new Issue(1, "Test issue", "Test detail");

    Injector injector = Guice.createInjector(new PersistenceModule("jpa-persistence-unit-1"));
    IssueDao issueDao = injector.getInstance(IssueDao.class);

    @Test
    public void testPersist() {
        issueDao.persist(issue);
    }

    @Test
    public void testUpdate() {
        issue.setTitle("New title");
        issueDao.update(issue);
    }

    @Test
    public void testRemove() {
        issueDao.remove(issue);
    }

    @Test
    public void testList() {
        data = FXCollections.observableArrayList(issueDao.findAll());
    }

    @Test
    public void testUnfinishedList() {
        data = FXCollections.observableArrayList(issueDao.findUnfinished());
    }

    @Test
    public void testFindByTitleAndDetails() {
        Issue issue = new Issue(1, "Test issue", "Test detail");
        issueDao.persist(issue);

        Optional<Issue> issueTest = issueDao.findByTitleAndDetails("Test issue", "Test detail");

        assertTrue(issueTest.isPresent());
        issueDao.remove(issue);
    }

    @Test
    public void testFindByTitleAndDetailsShouldGiveNullWhenTheIssueIsNotExists() {
        Optional<Issue> issue = issueDao.findByTitleAndDetails("Test issue", "Test detail");
        assertFalse(issue.isPresent());
    }
}
