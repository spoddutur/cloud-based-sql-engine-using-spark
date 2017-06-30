# Spark As CLoudBased SQL Engine
This project shows how to use SPARK as Cloud-based SQL Engine and expose your big-data as a JDBC/ODBC data source via the Spark thrift server. 

### Central Idea
Traditional relational Database engines like SQL had scalability problems and so evolved couple of SQL-on-Hadoop frameworks like Hive, Cloudier Impala, Presto etc. These frameworks are essentially cloud-based solutions and they all come with their own advantages and limitations. This project will demo how SparkSQL comes across as one more SQL-on-Hadoop framework as listed below:
- Data from multiple sources can be pushed into Spark and then exposed as SQLtable
- These tables are then made accessible as a JDBC/ODBC data source via the Spark thrift server.
- Multiple clients like ```Beeline CLI```, ```JDBC```, ```ODBC``` or ```BI tools like Tableau``` connect to Spark thrift server. - Once the connection is established, ThriftServer will contact SparkSQL engine to access Hive or Spark temp tables and run the sql queries on ApacheSpark framework.
- Spark Thrift basically works similar to HiveServer2 thrift where HiveServer2 submits the sql queries as Hive MapReduce job vs Spark thrift server will use Spark SQL engine which underline uses full spark capabilities.

#### Complete Guide - To know more about this topic, please refer to my blog [here](https://spoddutur.github.io/spark-notes/spark-as-cloud-based-sql-engine-via-thrift-server) where I briefed the concept in detail.

### Architecture
Following picture illustrates the idea we discussed above:
<img src="https://user-images.githubusercontent.com/22542670/27733176-54b684c2-5db2-11e7-946b-5b5ef5595e43.png" width="600" />

### Structure of the project:
- **data:** Contains input json used in MainApp to register sample data with SparkSql.
- **src/main/java/MainApp.scala:** Spark 2.1 implementation where it starts SparkSession and registers data from input.json with SparkSQL. (To keep the spark-session alive, there's a continuous while-loop in there).
- **src/test/java/TestThriftClient.java:** Java class to demo how to connect to thrift server and query the registered data

### How to run this project?
This project does 2 things:
1. Demo how to register data with SparkSql
2. Demo how to query registered data via Spark ThriftServer

### Demo how to register data with SparkSql
- Download this project.
- Build it: `mvn clean install` and
- Run MainApp: `spark-submit MainApp cloud-based-sql-engine-using-spark.jar`. Tht's it! 
- It'll register some sample data in `records` table with SparkSQL.

### Demo how to query registered data via Spark Thrift Server?
For this, first connect to Spark ThriftServer. Once the connection is established, just like HiveServer2, access Hive or Spark temp tables to run the sql queries on ApacheSpark framework. I'll show 2 ways to do this:

1. **Beeline:** Perhaps, the simplest is to use beeline command-line tool provided in Spark's bin folder. 
```markdown
`$> beeline`
Beeline version 2.1.1-amzn-0 by Apache Hive

// Connect to spark thrift server..
`beeline> !connect jdbc:hive2://localhost:10000`
Connecting to jdbc:hive2://localhost:10000
Enter username for jdbc:hive2://localhost:10000:
Enter password for jdbc:hive2://localhost:10000:

// run your sql queries and access data..
`jdbc:hive2://localhost:10000> show tables;,`
```
2. **Java JDBC:** Please refer to this project's test folder where I've shared a java example `TestThriftClient.java` to demo the same.

### Requirements
- Spark 2.1.0, Java 1.8 and Scala 2.11

### References:
- Complete guide and references to this project are briefed in my blog [here](https://spoddutur.github.io/spark-notes/spark-as-cloud-based-sql-engine-via-thrift-server).
