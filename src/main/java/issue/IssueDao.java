package issue;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

/**
 * DAO interface for the {@link Issue} entity.
 */
@RegisterBeanMapper(Issue.class)
public interface IssueDao {

    @SqlUpdate("""
            CREATE TABLE IF NOT EXISTS Issue (
               id INT NOT NULL AUTO_INCREMENT,
               title VARCHAR(100) NULL,
               details VARCHAR(255) NULL,
               dateCreated DATETIME NULL,
               dateFinished DATETIME NULL
               PRIMARY KEY (id))
            """
    )
    void createTable();

    @SqlUpdate("INSERT INTO Issue VALUES (:title, :details, :dateCreated, :dateFinished)")
    void insertIssue(@Bind("title") String title, @Bind("details") String details, @Bind("dateCreated") String dateCreated, @Bind("dateFinished") String dateFinished);

    @SqlUpdate("INSERT INTO Issue VALUES (:title, :details, :dateCreated, :dateFinished)")
    void insertIssue(@BindBean Issue Issue);

    @SqlQuery("SELECT * FROM Issue WHERE title = :title")
    Optional<Issue> getIssue(@Bind("title") String title);

    @SqlQuery("SELECT * FROM Issue ORDER BY dateCreated")
    List<Issue> listIssues();

}