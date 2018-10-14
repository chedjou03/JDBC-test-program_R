import java.sql.*;


public class JdbcInsertDemo {

	public static void main(String[] args) throws SQLException {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		String DB_url = "jdbc:mysql://localhost:3306/demo";
		String user = "simplice";
		String password = "password";

		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(DB_url, user, password);
			
			
			// 2. Create a statement
			myStmt = myConn.createStatement();

			// 3. Insert a new employee
			System.out.println("Inserting a new employee to database\n");
			
			int rowsAffected = myStmt.executeUpdate(
				"insert into employees " +
				"(last_name, first_name, email, department, salary) " + 
				"values " + 
				"('Foungoum', 'Herman', 'eric.wright@foo.com', 'HR', 33000.00)");
			
			System.out.println("number of row inserted "+rowsAffected);
			// 4. Verify this by getting a list of employees
			myRs = myStmt.executeQuery("select * from employees order by last_name");
			
			// 5. Process the result set
			while (myRs.next()) {
				System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}
	}

}
