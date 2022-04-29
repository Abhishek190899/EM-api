# Employee Management API using Play 2.8 and reactive Mongo


Building an asynchronous & non-blocking REST application using the Play framework and reactive mongo plugin that allows us to perform the basic CRUD operations for the data stored in a mongo database.  

Prerequisite :

       • JVM 14 or above
       • Scala 2.x
       • SBT 1.3.x
       • Postman v6 or above

# How to Run

• Set up mongo server

Create a directory *c://data/db* and set up a local mongo server from command prompt by using the following command in the specified Mongodb directory as follows :

``` mongod --storageEngine=mmapv1 --dbpath C : \data\db ```

• Run the API using following command :

``` sbt run ```

• Once the server gets started after loading all the required dependencies from the local api directory, open **Postman** and write the url :

``` http://localhost:9000/employees ``` 

Perform CRUD operations to test the API.
