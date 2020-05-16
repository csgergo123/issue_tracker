package issue;

/** Represents a User (base class)
 * @author Csipkes Gergo
 * @author csipkesgeri@gmail.com
 * @version 0.1
 */
public class User {
    protected int id;
    protected String firstName;
    protected String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Get the User's ID.s
     * @return An int with the User ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the User's ID.
     * @param id An int with the User ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the User's first name.
     * @return firstName A string with the User's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the User's first name.
     * @param firstName A string with the User's first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the User's last name.
     * @return lastName A string with the User's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the User's lastName.
     * @param lastName A string with the User's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Adds a new Issue to the user.
     * @param userID An int with the User's ID.
     * @param title A string with the issue's title.
     * @param text A string with the issue's text.
     * @param dateCreated A String with the creation time of the issue.
     * @return The created Issue model.
     */
    protected Issue addIssue(int userID, String title, String text, String dateCreated) {
        Issue issue = new Issue(userID, title, text);
        return issue;
    }

    /**
     * Adds a new issue, but not need to pass the creation time of the issue as a parameter.
     * The default value of the creation time is the current timestamp.
     *
     * @param userID An int with the User's ID.
     * @param title A string with the issue's title.
     * @param text A string with the issue's text.
     * @return The created Issue model.
     */
    // Instead of optional operator
    protected Issue addIssue(int userID, String title, String text) {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);

        return addIssue(userID, title, text, currentTime);
    }
}
