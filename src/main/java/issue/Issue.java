package issue;

import javafx.beans.property.SimpleStringProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/** Class represents an {@code issue}.
 *
 * @author Csipkes Gergo
 * @author csipkesgeri@gmail.com
 * @version 0.2
 */
@Data
@SuperBuilder
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Issue {
    /**
     * The ID of the issue.
     */
    @GeneratedValue
    @Id
    private int id;

    /**
     * An ID representing which user belongs this issue.
     */
    @Column(nullable=false)
    private int createdBy;

    /**
     * The title of the issue.
     */
    @Column(nullable=false)
    private String title;

    /**
     * The details of the issue.
     */
    @Column(nullable=false)
    private String details;

    /**
     * The creation datetime of the issue.
     */
    @Column(nullable=false)
    private String dateCreated;

    /**
     * The finish datetime of the issue.
     */
    @Column(nullable=true)
    private String dateFinished;

    /**
     * Creates an {@code Issue} object with empty properties.
     */
    public Issue() {
        this.createdBy = 1;
        this.title = "";
        this.details = "";
        this.dateCreated = "";
        this.dateFinished = "";
    }

    /**
     * Creates an {@code Issue} object with the given parameters.
     * The creation date will be the current datetime and the finish date will be empty.
     *
     * @param createdBy The ID of the user which belongs the issue.
     * @param title The title of the issue.
     * @param details The details of the issue.
     */
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
     *
     * @return An int with the Issue's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the Issue's ID.
     *
     * @param id An int with the Issue's ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the Issue's creator.
     *
     * @return An int with the Issue's creator.
     */
    public int getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the Issue's creator.
     *
     * @param createdBy An int with the Issue's creator.
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get the Issue's title.
     *
     * @return A string with the Issue's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the Issue's title.
     *
     * @param title A string with the Issue's title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the Issue's details.
     *
     * @return A string with the Issue's details.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Set the Issue's details.
     *
     * @param details A string with the Issue's details.
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Get the Issue's creation time.
     *
     * @return A string with the Issue's creation time.
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * Set the Issue's creation time.
     *
     * @param dateCreated A string with the Issue's creation time.
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Get the Issue's completion time.
     *
     * @return A string with the Issue's completion time.
     */
    public String getDateFinished() {
        return dateFinished;
    }

    /**
     * Set the Issue's completion time.
     *
     * @param dateFinished A string with the Issue's completion time.
     */
    public void setDateFinished(String dateFinished) {
        this.dateFinished = dateFinished;
    }

    /**
     * Returns a string representation of the object. In general, the toString method returns a string that "textually represents" this object. The result should be a concise but informative representation that is easy for a person to read. It is recommended that all subclasses override this method.
     * The toString method for class Object returns a string consisting of the name of the class of which the object is an instance, the at-sign character `@', and the unsigned hexadecimal representation of the hash code of the object. In other words, this method returns a string equal to the value of:
     *
     * @return A string representation of the object.
     */
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

    /**
     * Check whether the issue is solved.
     *
     * @return {@code true} if the issue is solved (the finished date is not empty), {@code false} otherwise
     */
    public Boolean isSolved() {
        if(this.getDateFinished() != null && !this.getDateFinished().isEmpty() && this.getDateFinished().length() >= 4) {
            System.out.println(this.getDateFinished());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set the current datetime of the issue.
     */
    public void setSolved() {
        // MySQL Datetime format
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dateFinished = sdf.format(dt);
    }
}
