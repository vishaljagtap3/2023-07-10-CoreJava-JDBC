import java.sql.*;
import java.util.ArrayList;

public class DbUtil {

    private Connection con;
    private static DbUtil dbUtil = null;

    private DbUtil(String connectionString, String username, String password) {
        try {
            con = DriverManager.getConnection(connectionString, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DbUtil getInstance(
            String connectionString,
            String username,
            String password
    ) {
        if(dbUtil == null) {
            dbUtil = new DbUtil(connectionString, username, password);
        }
        return dbUtil;
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Emp> getEmployees() {
        try {
            ArrayList<Emp> emps = new ArrayList<>();

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from emp");

            while(rs.next()) {
                emps.add(
                        new Emp(rs.getInt(1), rs.getString(2), rs.getInt(6), rs.getString(7))
                );
            }
            rs.close();
            statement.close();

            return emps;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addEmployee(int id, String name, String dob, int mgr, int deptno, int sal, String location) {
        try {
            Statement statement = con.createStatement();
            String insertStatement = "insert into emp values(" + id + ",'" + name + "', '" + dob + "'," + mgr + "," + deptno + ","+ sal + ",'" + location + "',null)";
            System.out.println(insertStatement);

            int rowsAffected = statement.executeUpdate(insertStatement);
            return rowsAffected == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addEmployee(Emp newEmp) {
        return false;
    }

    public boolean validateCredentials(String username, String password) {
        String query = "select * from users where username = '" + username + "' and password = '" + password + "'";
        System.out.println(query);

       try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            return rs.next();

            /*if(rs.next()) {
                return true;
            }
            return false*/

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }
}
