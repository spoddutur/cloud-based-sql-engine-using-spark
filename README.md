# Spark As CLoudBased SQL Engine
This project shows how to use SPARK as Cloud-based SQL Engine and expose your big-data as a JDBC/ODBC data source via the Spark thrift server. 

### Central Idea:
Traditional relational Database engines like SQL had scalability problems and so evolved couple of SQL-on-Hadoop frameworks like Hive, Cloudier Impala, Presto etc. These frameworks are essentially cloud-based solutions and they all come with their own advantages and limitations. This project will demo how SparkSQL comes across as one more SQL-on-Hadoop framework. 
To know more details on this please refer to [this](https://spoddutur.github.io/spark-notes/spark-as-cloud-based-sql-engine-via-thrift-server) blog.

### What is the role of Spark Thrift Server in this?
SparkSQL enables fast, in-memory integration of external data sources with Hadoop for BI access over JDBC/ODBC. Spark ThriftServer makes this data queryable as JDBC/ODBC source.Spark Thrift Server is similar to HiveServer2 Thrift, instead of submitting sql queries as Hive MapReduce job, spark thrift will use Spark SQL engine which inturn uses full spark capabilities. 
Following picture depicts the same:

![Image](https://user-images.githubusercontent.com/22542670/27724829-47acbe3e-5d91-11e7-8461-fe22d87699ba.png)

### How to connect to Spark Thrift Server?
To connect to Spark ThriftServer, use JDBC/ODBC driver just like HiveServer2 and access Hive or Spark temp tables to run the sql queries on ApacheSpark framework. There are couple of ways to connect to it. 
1. Beeline: Perhaps, the simplest is to use beeline command-line tool provided in Spark's bin folder. 
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
2. Java JDBC: Please refer to this project's test folder where I've shared a java example to demo the same.

### Requirements
- Spark 2.1.0, Java 1.8 and Scala 2.11

Guide: 
[Spark as cloud-based SQL Engine exposing data via ThriftServer](https://spoddutur.github.io/spark-notes/spark-as-cloud-based-sql-engine-via-thrift-server)

References:
[MapR Docs on SparkThriftServer](http://maprdocs.mapr.com/home/Spark/SparkSQLThriftServer.html)
