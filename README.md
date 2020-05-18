# Issue tracker

An issue tracker example application with basic GUI.
The project written in JavaFX, used the MVC architecture and JPA for persistent data handling.

Users can add new issues to the database, modify their properties, set solved status and list all of the issues or just the unfinished ones.

### Usage

Issue tracker requires
  - JDK 8 or laters
  - Maven 3

Install the dependencies and run these commands.

```sh
$ mvn package
$ java -jar target/IssueTracker1.0.jar
```

### Tech

Issue Tracker uses a number of open source projects to work properly:

* [JavaFX] - HTML enhanced for web apps!
* [MySQL] - MySQL is the world's most popular open source database.
* [Hibernate] - Hibernate ORM enables developers to more easily write applications whose data outlives the application process.
* [JDBi] - Jdbi provides convenient, idiomatic access to relational data in Java.
* [Lombok] - Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
* [SL4J] - The Simple Logging Facade for Java.

And of course Issue Tracker itself is open source with a [public repository][Issue Tracker]
 on GitHub.

### Todos

 - Write MORE Tests
 - Add User levels with custom privileges

License
----


GNU [GPLv3]



**Free Software, Hell Yeah!**

[Issue Tracker]: <https://github.com/csgergo123/issue_tracker>
[JavaFX]: <https://openjfx.io/>
[Hibernate]: <http://hibernate.org/orm/>
[JDBi]: <http://jdbi.org/>
[MySQL]: <https://dev.mysql.com/doc/connector-j/8.0/en/>
[Lombok]: <https://projectlombok.org/>
[SL4J]: <http://www.slf4j.org/>
[GPLv3]: <https://www.gnu.org/licenses/gpl-3.0.html>