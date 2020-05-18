package issue;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IssueTest {
    Issue issue = new Issue(1, "Test issue", "Test detail");

    @Test
    void testIsSolved() {
        assertFalse(new Issue().isSolved());
        assertFalse(issue.isSolved(), "Check solved status");
    }

    @Test
    void testSetSolved() {
        issue.setSolved();
        assertTrue(issue.isSolved(), "Check solved status");
    }
}
