import org.apache.spark.sql.SparkSession

/**
  * Created by surthi on 28/06/17.
  */
object MainApp {

  def main(args: Array[String]): Unit = {

    println("Hi")

    // change master from local[2] accordingly (change it to "yarn" if we are running it in yarn cluster..)
    val spark = SparkSession.builder()
      .master("local[2]")
      .appName("SparkSqlExample")
      .config("spark.sql.warehouse.dir", "/beeline/spark-sql-warehouse")
      .config("hive.server2.thrift.port", "10000")
      .config("spark.sql.hive.thriftServer.singleSession", true)
      .enableHiveSupport()
      .getOrCreate()

    import spark.implicits._

    // For simplicity, Iam loading data from one file for now..
    // But, in reality, this records dataset can be some real-time continuous streaming data
    val records = spark.read.format("json").load("/Users/surthi/Downloads/delme.json")
    records.show()
   // records.write.saveAsTable("records")

    // This loop will keep the session alive
    while (true) {
      Thread.`yield`()
    }
  }
}

// Cloud-based SQL engine using SPARK made available for access as a JDBC/ODBC data source via the Spark thrift server.