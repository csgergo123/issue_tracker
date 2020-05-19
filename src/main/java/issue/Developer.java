package issue;

/**
 * Represents a Developer user with higher access.
 *
 * @author Csipkes Gergo
 * @author csipkesgeri@gmail.com
 * @version 0.3
 */
public class Developer extends User {
    private String position;

    /**
     * Creates a {@code Developer} entity.
     *
     * @param firstName First name of the developer.
     * @param lastName Last name of the developer.
     * @param position Position of the developer.
     */
    public Developer(String firstName, String lastName, String position) {
        super(firstName, lastName);
        this.position = position;
        //Logger.info("A new Developer created: " + this.toString());
    }

    /**
     * Get the position of the Developer.
     *
     * @return A string with the position of the Developer.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Set the position of the Developer.
     *
     * @param position The position of the developer.
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Returns a string representation of the object. In general, the toString method returns a string that "textually represents" this object. The result should be a concise but informative representation that is easy for a person to read. It is recommended that all subclasses override this method.
     * The toString method for class Object returns a string consisting of the name of the class of which the object is an instance, the at-sign character `@', and the unsigned hexadecimal representation of the hash code of the object. In other words, this method returns a string equal to the value of:
     *
     * @return A string representation of the object.
     */
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
    /*public void setIssueSolved(Issue issue) {
        issue.setSolved(true);
    }*/
}
