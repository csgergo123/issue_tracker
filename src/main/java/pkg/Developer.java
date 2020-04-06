package pkg;

import org.tinylog.Logger;

/** Represents a Developer user with higher access.
 * @author Csipkes Gergo
 * @author csipkesgeri@gmail.com
 * @version 0.1
 */
public class Developer extends User {
    private String position;

    public Developer(String firstName, String lastName, String position) {
        super(firstName, lastName);
        this.position = position;
        Logger.info("A new Developer created: " + this.toString());
    }

    /**
     * Get the position of the Developer.
     * @return A string with the position of the Developer.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Set the position of the Developer.
     * @param position The position of the developer.
     */
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    /**
     * Set solved a given issue.
     * @param issue An Issue type issue.
     */
    public void setIssueSolved(Issue issue) {
        issue.setSolved(true);
    }
}
