import java.sql.*;
import java.util.Date;


interface IIssueBookDBHandler {
	void createIssue(int bookId, int studentId, String studentName, String studentContact, int quantity);
	Object[][] getIssues();
	void returnBook(int issueId);
}

public class IssueBookDBHandler extends DBHandler implements IIssueBookDBHandler {

	@Override
	public void createIssue(int bookId, int studentId, String studentName, String studentContact, int quantity) {
		try {
			Connection con = makeConnection();
			String command = "insert into issue_book(book_id, student_id, student_name, student_contact"
					+ ", quantity, issue_date, return_date) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(command);

			
			int bookCount = new BookDBHandler().bookQuantity(bookId);
			int remainingBooks = bookCount - quantity;
			
			String updateQuantity = "update book set quantity = " + remainingBooks + " where id = " + bookId;
			Statement stmt = con.createStatement();
			stmt.executeUpdate(updateQuantity);
			
			java.time.LocalDateTime dateTime = java.time.LocalDateTime.now();
			java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

			String issueDate = formatter.format(dateTime);
			
			ps.setInt(1, bookId);
			ps.setInt(2,  studentId);
			ps.setString(3, studentName);;
			ps.setString(4, studentContact);;
			ps.setInt(5, quantity);;
			ps.setString(6, issueDate);
			ps.setString(7, null);

			ps.executeUpdate();
			
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public Object[][] getIssues () {
		Object[][] ret = null;
		try {
			Connection con = makeConnection();
			String command = "select * from issue_book";
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


	@Override
	public void returnBook(int issueId) {
		try {
			Connection con = makeConnection();
			Statement stmt = con.createStatement();
			
			String getIssueDetails = "select book_id, quantity from issue_book where issue_id = " + issueId;
			
			ResultSet issue = stmt.executeQuery(getIssueDetails);
			issue.next();
			int bookId = issue.getInt(1);
			int quantity = issue.getInt(2);
//			
			//update the issue quantity and return date
			String returnDate = new Date().toLocaleString();
			
			String updateIssueCommand = "update issue_book set quantity = 0, return_date = '" + returnDate + 
					"' where issue_id = " + issueId;
			stmt.executeUpdate(updateIssueCommand);
			
			/// now update the book table
			
			Integer hasBooks = new BookDBHandler().bookQuantity(bookId);
			
			String updateBook = "update book set quantity = " + (hasBooks + quantity) + " where id = " + bookId;
			stmt.executeUpdate(updateBook);
			
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
