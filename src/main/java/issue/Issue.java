package issue;

import javafx.beans.property.SimpleStringProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/** Represents an issue.
 * @author Csipkes Gergo
 * @author csipkesgeri@gmail.com
 * @version 0.1
 */
@Data
@SuperBuilder
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Issue {

    @GeneratedValue
    @Id
    private int id;

    @Column(nullable=false)
    private int createdBy;
    // Azok a POJO-k amik SimpleStringProperty-vel rendelkeznek, ezek lesznek képesek kommunikálni az adatbázis táblával

    @Column(nullable=false)
    private String title;

    @Column(nullable=false)
    private String details;

    @Column(nullable=false)
    private String dateCreated;

    @Column(nullable=true)
    private String dateFinished;

    public Issue() {
        this.createdBy = 1;
        this.title = "";
        this.details = "";
        this.dateCreated = "";
        this.dateFinished = "";
    }

    public Issue(int createdBy, String title, String details) {
        this.createdBy = createdBy;
        this.title = title;
        this.details = details;
        // MySQL Datetime format
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dateCreated = sdf.format(dt);
    }

    /**
     * Get the Issue's ID.
     * @return An int with the Issue's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the Issue's ID.
     * @param id An int with the Issue's ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the Issue's creator.
     * @return An int with the Issue's creator.
     */
    public int getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the Issue's creator.
     * @param createdBy An int with the Issue's creator.
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get the Issue's title.
     * @return A string with the Issue's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the Issue's title.
     * @param title A string with the Issue's title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the Issue's details.
     * @return A string with the Issue's details.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Set the Issue's details.
     * @param details A string with the Issue's details.
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Get the Issue's creation time.
     * @return A string with the Issue's creation time.
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * Set the Issue's creation time.
     * @param dateCreated A string with the Issue's creation time.
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Get the Issue's completion time.
     * @return A string with the Issue's completion time.
     */
    public String getDateFinished() {
        return dateFinished;
    }

    /**
     * Set the Issue's completion time.
     * @param dateFinished A string with the Issue's completion time.
     */
    public void setDateFinished(String dateFinished) {
        this.dateFinished = dateFinished;
    }


    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateFinished='" + dateFinished + '\'' +
                '}';
    }
}
