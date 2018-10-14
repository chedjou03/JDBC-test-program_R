import java.sql.*;

public class CallStoreProcGetEmployeesForDepartment {

	public static void main(String[] args) throws Exception {

		Connection myConn = null;
		CallableStatement myStmt = null;
		ResultSet myRs = null;

		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "simplice";		
		String password = "password";

		try {
			// Get a connection to database
			myConn = DriverManager.getConnection(dbUrl, user, password);

			String theDepartment = "Engineering";

			// Prepare the stored procedure call
			myStmt = myConn
					.prepareCall("{call get_employees_for_department(?)}");

			// Set the parameter
			myStmt.setString(1, theDepartment);

			// Call stored procedure
			System.out.println("Calling stored procedure.  get_employees_for_department('" + theDepartment + "')");
			myStmt.execute();
			System.out.println("Finished calling stored procedure.\n");

			// Get the result set
			myRs = myStmt.getResultSet();

			// Display the result set
			display(myRs);
			
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(myConn, myStmt, myRs);
		}
	}

	private static void display(ResultSet myRs) throws SQLException {
		// Process result set
		while (myRs.next()) {
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			double salary = myRs.getDouble("salary");
			String department = myRs.getString("department");

			System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
		}

	}

	private static void close(Connection myConn, Statement myStmt,
			ResultSet myRs) throws SQLException {
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

	private static void close(Statement myStmt, ResultSet myRs)
			throws SQLException {

		close(null, myStmt, myRs);
	}
}
