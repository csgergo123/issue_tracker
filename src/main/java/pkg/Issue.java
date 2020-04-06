package pkg;

import org.tinylog.Logger;

/** Represents an issue.
 * @author Csipkée Gergo
 * @author csipkesgeri@gmail.com
 * @version 0.1
 */
public class Issue {
    private int id;
    private int createdBy;
    private String title;
    private String text;
    private String dateCreated;
    private String dateFinished;
    private boolean solved;

    public Issue(int createdBy, String title, String text, String dateCreated) {
        this.createdBy = createdBy;
        this.title = title;
        this.text = text;
        this.dateCreated = dateCreated;
        Logger.info("A new issue created: " + this.toString());
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
     * Get the Issue's text.
     * @return A string with the Issue's text.
     */
    public String getText() {
        return text;
    }

    /**
     * Set the Issue's text.
     * @param text A string with the Issue's text.
     */
    public void setText(String text) {
        this.text = text;
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

    /**
     * Get the Issue's solution status.
     * @return A boolean with the Issue's solution status.
     * If it's true, the issue is already solved, if it's false the problem is not solved yet
     */
    public boolean isSolved() {
        return solved;
    }

    /**
     * Set the Issue's solution status.
     * @param solved A boolean with the Issue's completion time.
     */
    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateFinished='" + dateFinished + '\'' +
                ", solved=" + solved +
                '}';
    }
}
