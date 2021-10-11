import java.sql.*;


interface ILibrarianDBHandler {
	void createLibrarian(String name, String password, String email, String address, String city, String contactInfo);
	int deleteLibrarian(int id);
	boolean checkUser (String name, String password);
	Object[][] getLibrarian();
	boolean checkUserName(String name);
}


public class LibrarianDBHandler extends DBHandler implements ILibrarianDBHandler{
	private int rowSize = 0;
	

	public void createLibrarian (String name, String password, String email, String address, String city, String contactInfo) {
		try {
			Connection con = makeConnection();
			String command = "insert into librarian(name, password, email, address, city, contact_info) values(?,?,?,?,?,?)";

			PreparedStatement stmt = con.prepareStatement(command);
			stmt.setString(1, name);
			stmt.setString(2, password);
			stmt.setString(3, email);
			stmt.setString(4, address);
			stmt.setString(5, city);
			stmt.setString(6, contactInfo);

			int affected = stmt.executeUpdate();

			System.out.println(affected + " rows are affected");

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public int deleteLibrarian (int id) {
		try {
			Connection con = makeConnection();
			String command = "delete from librarian where id = " + id;
			Statement stmt = con.createStatement();
			int affected = stmt.executeUpdate(command);
			con.close();
			return affected;
			
		} catch(Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	public Object[][] getLibrarian () {
		Object[][] ret = null;
		try {
			Connection con = makeConnection();
			String command = "select * from librarian";
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet result = stmt.executeQuery(command);
			
			if (result.last()) {
				this.rowSize = result.getRow();
				result.first();
			}
			
			ret = new Object[rowSize][7];
			
			for (int i = 0; i < rowSize; i++) {
				for (int j = 0; j < 7; j++) {
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
	public boolean checkUser (String name, String password) {
		boolean userExist = false;
		try {
			Connection con = makeConnection();
			String query = "select * from librarian where name = '" + name + "' and password = '" + password + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			userExist = rs.next();
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		return userExist;
	}
	@Override
	public boolean checkUserName(String name) {
		boolean userExist = false;
		try {
			Connection con = makeConnection();
			String query = "select * from librarian where name = '" + name + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			userExist = rs.next();
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		return userExist;
	}
}












