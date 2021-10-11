import java.sql.*;
import java.time.LocalDateTime;


interface IBookDBHandler {
	void addBook(String colNo, String name, String author, 
			String publisher, int quantity);
	Object[][] getBook();
	Integer bookQuantity (int bookId);
}


public class BookDBHandler extends DBHandler implements IBookDBHandler {

	@Override
	public Integer bookQuantity (int bookId) {
		Integer quantity = null;
		try {
			Connection con = makeConnection();
			Statement stmt = con.createStatement();
			String command = "select quantity from book where id = " + bookId;
			ResultSet rs = stmt.executeQuery(command);
			if (!rs.next()) return null;
			quantity = rs.getInt(1);
			
		} catch(Exception e) {
			System.out.println(e);
		}
		return quantity;
	}

	@Override
	public void addBook(String colNo, String name, String author, String publisher, int quantity) {
		try {
			Connection con = makeConnection();
			String command = "insert into book (col_no, name, author, publisher, quantity, issued, add_date) "
					+ "values (?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(command);
			
			LocalDateTime dateTime = java.time.LocalDateTime.now();
			java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			
			ps.setString(1, colNo);
			ps.setString(2, name);
			ps.setString(3, author);
			ps.setString(4, publisher);
			ps.setInt(5,  quantity);
			ps.setInt(6, 0);
			ps.setString(7, formatter.format(dateTime));
			
			ps.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	@Override
	public Object[][] getBook () {
		Object[][] ret = null;
		try {
			Connection con = makeConnection();
			String command = "select * from book";
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet result = stmt.executeQuery(command);
			
			int rowSize = 0;
			
			if (result.last()) {
				rowSize = result.getRow();
				result.first();
			}
			
			ret = new Object[rowSize][8];
			
			for (int i = 0; i < rowSize; i++) {
				for (int j = 0; j < 8; j++) {
					ret[i][j] = result.getString(j + 1);
				}
				result.next();
			}
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return ret;
	}

}
