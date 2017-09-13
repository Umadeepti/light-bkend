#Tito opp module

This module uses Gradle and Java 8.

###Build and run:
Navigate to the tito-opp-application directory. Build the project.
```
cd tito-opp-application
gradle build
```
Navigate to the tito-opp-data directory. Build the project. (To incorporate latest Liquibase changesets)
```
cd tito-opp-data
gradlew update
```
Navigate to the build directory then the libs directory. Run the war file.
```
cd build
cd libs
java -jar tito-opp-0.0.1-SNAPSHOT.war
```