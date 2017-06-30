/**
 * Created by surthi on 30/06/17.
 */
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;


/**
 * This is a test case that connects to sprak's thrift server and asserts that the data is present.
 * In this example, 3 asserts are done: "show tables", "describe table <table-name>" and "select * from <table-name>".
 */
public class TestThriftClient {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws SQLException {

        // Assert that the driver is present in classpath
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Change the connection-url, username and password accordingly
        String connUrl = "jdbc:hive2://172.20.1.190:10000/default";
        String username = "";
        String password = "";

        Connection con = DriverManager.getConnection(connUrl, username, password);
        Statement stmt = con.createStatement();

        // show tables
        String sql = "show tables";
        System.out.println("Running: " + sql);
        ResultSet res = stmt.executeQuery(sql);
        if (res.next()) {
            System.out.println(res.getString(1));
        }

        // describe table
        String tableName = "hiveivnews";
        sql = "describe " + tableName;
        System.out.println("Running: " + sql);
        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1) + "\t" + res.getString(2));
        }

        // select * query
        sql = "select * from " + tableName;
        System.out.println("Running: " + sql);
        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(String.valueOf(res.getString(2)) + "\t" + res.getString(7));
        }
    }
}