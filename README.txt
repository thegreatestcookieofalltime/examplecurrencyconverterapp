To launch this application, first read its license in the "LICENSE.txt" file.

Then you can build it by launching "mvn clean package" in the main directory.
After you successfully build it, you can launch it using the 
"java -jar target/currencies-converter-0.0.1-SNAPSHOT.jar" command.

The application asks for user input and behaves according to it.

It uses a single Maven module, because Spring Boot doesn't work well with 
multi-module applications, if they happen to use the command line input.
Of course that wouldn't be a problem, if the application weren't controlled
using the command line - but the assignment required me to "read user input",
so I assumed that the application should use that form of control and I just
kept it that way.

Dependencies are properly set using inversion of control though, so the
application still works like a proper web application.

Every Java file in the project includes Javadoc and was verified against
code smells and typical bugs using SonarQube.

The data source for the application is of course fake, because (if I understood
correctly) the assignment was more about overall application building skill
than about implementing real life tool. It can be easily changed to real one
though, thanks to being separated into another Spring Bean and used through 
its interface.

