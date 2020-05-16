package issue;

/** Represents a Guest user with limited access.
 * @author Csipkes Gergo
 * @author csipkesgeri@gmail.com
 * @version 0.1
 */
public class Guest extends User {
    public Guest(String firstName, String lastName) {
        super(firstName, lastName);
        //Logger.info("A new Guest created: " + this.toString());
    }
}
