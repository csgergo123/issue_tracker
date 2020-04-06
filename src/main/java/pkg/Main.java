package pkg;

public class Main {
    public static void main(String[] args) {
        Developer dev1 = new Developer("Csipkes", "Gergo", "Software developer");
        System.out.println(dev1.toString());

        Issue issue = dev1.addIssue(1, "Test issue", "Test text");

        dev1.setIssueSolved(issue);
    }
}
