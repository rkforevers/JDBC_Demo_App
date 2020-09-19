# JDBC_Demo_App

# Basic of JDBC (Java Database Connectivity)

JDBC basically used to connect your application to database.
So JDBC act as a bridge between your application and database.
In this project we have created simple JDBC operation (CRUD) i.e. Insert a record, delete a record, select a record, update a record and select all record from database table.

Following are the steps required to connect your application to database 

#### Import the packages: 
Requires that you include the packages containing the JDBC classes needed for database programming. Most often, using import java.sql.* will suffice.

#### Register the JDBC driver: 
Requires that you initialize a driver so you can open a communication channel with the database.

#### Open a connection:
Requires using the DriverManager.getConnection() method to create a Connection object, which represents a physical connection with the database.

#### Execute a query:
Requires using an object of type Statement for building and submitting an SQL statement to the database.

#### Extract data from result set:
Requires that you use the appropriate ResultSet.getXXX() method to retrieve the data from the result set.

#### Clean up the environment:
Requires explicitly closing all database resources versus relying on the JVM's garbage collection. Basically closing the database connection is one important thing.

## Prerequisite
- JAVA_HOME should be setup in the classpath
- Apache ant should be setup in the classpath
- Should know core java concept
- Should know basic SQL concept
- Ant (add advantage) 

Following is database SQL 

```bash
CREATE TABLE Persons
(
  PersonID NUMBER(5),
  LastName varchar(255),
  FirstName varchar(255),
  Address varchar(255),
  City varchar(255),
  CONSTRAINT UK_Persons UNIQUE (PersonID)
);
```

## Steps to setup

1. Execute above SQL in Oracle database
2. Make directory name as JDBCAppsDemo
2. Make directory src\jdbcapplicationdemo (Windows) inside JDBCAppsDemo
3. Make directory lib (Windows) inside JDBCAppsDemo
4. Copy *.java file src\jdbcapplicationdemo inside JDBCAppsDemo
5. Download required ojdbc<version-name>.jar in lib folder.
6. Copy build.xml in root directory i.e. JDBCAppsDemo

## Steps to run

1. Update the database details in the ConnectionHelper.java
2. Run the project using below command

```ant 
ant run
```

```java
## Output
[java] Program Started....
[java] Initializing database connection
[java] Database connection created
[java]
[java] Display all records from table
[java]
[java] No record found in database table !!
[java]
[java] Insert New record in table
[java]
[java] Inserted record with ID : 123
[java] Total row inserted in table : 1
[java]
[java] Select Person whose PersonId :123
[java] 123 Mac     John    MG Gandhi       Spanish
[java]
[java] Update record whose PersonID :123
[java]
[java] Updated City of PersonID 123
[java]
[java] Deleting record whose PersonID :123
[java] Deleted record whose PersonID :123
[java] Program Finished....
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
Information not available.
