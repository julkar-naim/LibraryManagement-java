import java.sql.*;

public class DatabaseTest {
    DatabaseTest() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "naim", "naimmzn");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

//            stmt.executeUpdate("insert into product values(3, 420)");
//            stmt.executeUpdate("delete from product where id = 2");

            ResultSet rs = stmt.executeQuery("select * from product");

            rs.last();
            System.out.println("Row count " + rs.getRow());
            rs.beforeFirst();

            while (rs.next())
                System.out.println(rs.getString(1) + "  " + rs.getInt(2));

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
