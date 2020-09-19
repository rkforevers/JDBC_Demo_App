/*
 * JDBC connectivity example
 */
package jdbcapplicationdemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rkforevers
 */
public class JDBCApplicationDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Program Started....");
        Connection con = null;
        int insertCount = 0;
        int personID=123;
        try {
            System.out.println("Initializing database connection ");
            con = ConnectionHelper.getConnection();

            if (con != null) {
                System.out.println("Database connection created ");
            } else {
                System.err.println("Failed to create Connection");
            }
          
            //Selecting all records
            System.out.println("\nDisplay all records from table");
            JDBCApplicationDemo.selectAllRecord(con);

            //Inserting record in database table
            System.out.println("\nInsert New record in table");
            insertCount = JDBCApplicationDemo.insertRecords(con);
            System.out.println("Total row inserted in table : " + insertCount);

            //Selecting record based on condition from database table
            System.out.println("\nSelect Person whose PersonId :"+personID);
            JDBCApplicationDemo.selectRecordBasedOnCondition(con, personID);

            //Updated record based on condition from database table
            System.out.println("\nUpdate record whose PersonID :"+personID);
            JDBCApplicationDemo.updateRecordBasedOnCondition(con, personID);

            //Delete record based on condition from database table
            System.out.println("\nDeleting record whose PersonID :"+personID);
            JDBCApplicationDemo.deleteRecordBasedOnCondition(con, personID);


        } catch (ClassNotFoundException ex) {
            System.err.println("failed to load jdbc driver exception:" + ex);

        } catch (SQLException ex) {
            System.err.println("Exception occured :" + ex.getMessage());

        } finally {
            if (con != null) {
                try {
                    //Step 6 : Close database connection
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCApplicationDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
        }

        System.out.println("Program Finished....");
         System.exit(0);
    }

    /**
     * This method display all the records from table
     *
     * @param con
     * @throws SQLException
     */
    public static void selectAllRecord(Connection con) throws SQLException {

        PreparedStatement preparedStatment = null;
        //Note: In case of ORACLE database metadata (e.g. table name ,colum )are case insensitive but values inside the table are case sensitive
        String selectQuery = "SELECT * FROM PERSONS";

        try {
            //instantiation preparedStatment
            //Step 4 :Create statement
            preparedStatment = con.prepareStatement(selectQuery);

            //Step 5: Execute query
            ResultSet resultSet = preparedStatment.executeQuery();//This method return ResultSet

            if (resultSet.next()) {
                resultSet.beforeFirst();//setting cursor to before first    
                while (resultSet.next()) {

                    String personID = resultSet.getString(1);///Column index always start with 1
                    String lastName = resultSet.getString(2);
                    String firstName = resultSet.getString(3);
                    String address = resultSet.getString(4);
                    String city = resultSet.getString(5);

                    System.out.println(personID + "\t" + lastName + "\t" + firstName
                            + "\t" + address + "\t" + city);
                }
            } else {
                System.out.println("\nNo record found in database table !!");
            }
            con.commit();

        } catch (SQLException ex) {
            System.err.println("Exception occured while creating preparedStatement " + ex.getMessage());
            throw new SQLException();
        } finally {
            if (preparedStatment != null) {
                preparedStatment.close();
            }
        }

    }

    /**
     * *
     * This method select and display particular person based on provided ID.
     *
     * @param con Database connection Object
     * @param personID ID of the person that want to select
     * @throws SQLException
     */
    public static void selectRecordBasedOnCondition(Connection con, int personID) throws SQLException {

        PreparedStatement preparedStatment = null;
        //Note: In case of ORACLE database metadata (e.g. table name ,colum )are case insensitive but values inside the table are case sensitive
        String selectQuery = "SELECT * FROM PERSONS WHERE PersonID = ?";

        try {
            //instantiation preparedStatment
            //Step 4 :Create statement
            preparedStatment = con.prepareStatement(selectQuery);

            preparedStatment.setObject(1, 123);//You can use setInt as well.

            //Step 5: Execute query
            ResultSet resultSet = preparedStatment.executeQuery();//This method return ResultSet

            while (resultSet.next()) {

                String lastName = resultSet.getString(2);//Column index always start with 1 since we have already person ID hence we are not selecting here directly selecting 2 colum
                String firstName = resultSet.getString(3);
                String address = resultSet.getString(4);
                String city = resultSet.getString(5);

                System.out.println(personID + "\t" + lastName + "\t" + firstName
                        + "\t" + address + "\t" + city);

            }
            con.commit();

        } catch (SQLException ex) {
            System.err.println("Exception occured while creating preparedStatement " + ex.getMessage());
            throw new SQLException();
        } finally {
            if (preparedStatment != null) {
                preparedStatment.close();
            }
        }

    }

    /**
     * This method update single in database table
     *
     * @param con
     * @param personID
     * @return
     * @throws SQLException
     */
    public static int updateRecordBasedOnCondition(Connection con, int personID) throws SQLException {

        int updateRecordCount = 0;
        PreparedStatement preparedStatment = null;
        //Note: In case of ORACLE database metadata (e.g. table name ,colum )are case insensitive but values inside the table are case sensitive
        String updateQuery = "UPDATE PERSONS SET City = ?  WHERE PersonID = ? ";

        try {
            //instantiation preparedStatment
            //Step 4 :Create statement
            preparedStatment = con.prepareStatement(updateQuery);

            preparedStatment.setObject(1, "England");//You can use setString  as well.This method set the value for City = ?
            preparedStatment.setObject(2, personID);//You can use setInt as well. This method set the value for PersonID = ?

            //Step 5: Execute query
            updateRecordCount = preparedStatment.executeUpdate();//This method return count of deleted record

            System.out.println("\nUpdated City of PersonID " + personID);
            con.commit();

        } catch (SQLException ex) {
            System.err.println("Exception occured while creating preparedStatement " + ex.getMessage());
            throw new SQLException();
        } finally {
            if (preparedStatment != null) {
                preparedStatment.close();
            }

        }
        return updateRecordCount;

    }

    /**
     * This method delete a record in database table based on personID provided
     *
     * @param con
     * @param personID
     * @return
     * @throws SQLException
     */
    public static void deleteRecordBasedOnCondition(Connection con, int personID) throws SQLException {

        PreparedStatement preparedStatment = null;
        //Note: In case of ORACLE database metadata (e.g. table name ,colum )are case insensitive but values inside the table are case sensitive
        String deleteQuery = "DELETE PERSONS WHERE PersonID = ?";

        try {
            //instantiation preparedStatment
            //Step 4 :Create statement
            preparedStatment = con.prepareStatement(deleteQuery);

            preparedStatment.setObject(1, 123);//You can use setInt as well.

            //Step 5: Execute query
            preparedStatment.executeUpdate();//This method return count of deleted record

            con.commit();
            System.out.println("Deleted record whose PersonID :" + personID);

        } catch (SQLException ex) {
            System.err.println("Exception occured while creating preparedStatement " + ex.getMessage());
            throw new SQLException();
        } finally {
            if (preparedStatment != null) {
                preparedStatment.close();
            }

        }

    }

    /**
     * This method insert record in database and return the number of records
     * inserted
     *
     * @param con
     * @return
     * @throws SQLException
     */
    public static int insertRecords(Connection con) throws SQLException {
        int insertedRecordCount = 0;
        PreparedStatement preparedStatment = null;
        String insertQuery = "INSERT INTO Persons (PersonID,LastName,FirstName,Address,City) VALUES (?,?,?,?,?)";

        try {
            //instantiation preparedStatment
            //Step 4 :Create statement
            preparedStatment = con.prepareStatement(insertQuery);

            preparedStatment.setObject(1, 123);//You can use setInt as well.
            preparedStatment.setObject(2, "Mac");//You can use setString as well.
            preparedStatment.setObject(3, "John");//You can use setString as well.
            preparedStatment.setObject(4, "MG Gandhi");//You can use setString as well.
            preparedStatment.setObject(5, "Spanish");//You can use setString as well.

            //Step 5: Execute queries
            insertedRecordCount = preparedStatment.executeUpdate();//This method always return 1 because we are inserting only record
            System.out.println("\nInserted record with ID : " + 123);
            con.commit();

        } catch (SQLException ex) {
            System.err.println("Exception occured while creating preparedStatement " + ex.getMessage());
            if (ex.getMessage() != null && ex.getMessage().contains("UK_PERSONS")) {
                System.out.println("Record with same PersonID already exist");
            }
            throw new SQLException();
        } finally {
            if (preparedStatment != null) {
                preparedStatment.close();
            }
        }
        return insertedRecordCount;
    }

}
