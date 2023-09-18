import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        /*Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/emp_dept?useSSL=false",
                "bitcode",
                "Bitcode@123"
        );

        System.out.println(con.getClass().getName());

        Statement statement = con.createStatement();
        System.out.println(statement.getClass().getName());

        ResultSet r = statement.executeQuery("select * from emp where sal >= 10000");
        System.out.println(r.getClass().getName());
        while(r.next()) {
            System.out.println(r.getInt(1) + " " + r.getString(2) + " " + r.getInt(6) + " " + r.getString(7));
        }


        con.close();*/

        DbUtil dbUtil = DbUtil.getInstance("jdbc:mysql://localhost:3306/emp_dept?useSSL=false", "bitcode", "Bitcode@123");

       /* ArrayList<Emp> emps = dbUtil.getEmployees();
        for(Emp e : emps) {
            System.out.println(e);
        }*/

        /*boolean isAdded = dbUtil.addEmployee(
                4590,
                "Aditi S",
                "2000-11-11",
                103,
                40,
                3400,
                "Nashik"
        );
        System.out.println(isAdded);*/

        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        String password = scanner.nextLine();

        if(dbUtil.validateCredentials(username, password)) {
            System.out.println("Welcome " + username);
        }
        else {
            System.out.println("Error: " + username);
        }

        dbUtil.close();

    }
}
