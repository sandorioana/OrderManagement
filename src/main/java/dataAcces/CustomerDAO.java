package dataAcces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;
import model.Customer;

public class CustomerDAO {

    protected static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO customer (name,phone,address)"
            + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM customer WHERE idCustomer = ?";
    private final static String deleteStatementString ="DELETE FROM customer WHERE idCustomer = ?";
    private final static String selectStatementString ="SELECT * FROM customer";
    private final static String updateStatementString ="UPDATE customer SET address=?,phone=? WHERE idCustomer=?";

    /**
     * Find by id a customer.
     * @param customerId the id for customer
     * @return the index
     */
    public static Customer findById(int customerId){
        Customer toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, customerId);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            String phone = rs.getString("phone");
            String address = rs.getString("address");

            toReturn = new Customer(customerId, name, address, phone);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"CustomerDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        System.out.println(toReturn.getIdCustomer()+" " +toReturn.getName());
        return toReturn;
    }

    public static int delete(int idCustomer){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int toReturn = 0;

        try{
            deleteStatement = dbConnection.prepareStatement(deleteStatementString);
            deleteStatement.setInt(1, idCustomer);

            toReturn=deleteStatement.executeUpdate();
        }
        catch(SQLException e) {
            LOGGER.log(Level.WARNING, "CustomerDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    /**
     * Insert an customer the 'customer' table from database.
     * @param customer the customer to be added in the database
     * @return the index in which it was inserted
     */
    public static int insert(Customer customer) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;

        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, customer.getName());
            insertStatement.setString(2, customer.getPhone());
            insertStatement.setString(3, customer.getAddress());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CustomerDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     * Update an customer in the 'customer' table from database.
     * @param idCustomer the customer id  that needs to be updated in the database
     * @return the index in which it was updated
     */
    public static int update(int idCustomer, String address, String phone){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int toReturn=-1;

        try{
            updateStatement= dbConnection.prepareStatement(updateStatementString);
            updateStatement.setString(1,address);
            updateStatement.setString(2,phone);
            updateStatement.setInt(3, idCustomer);

            toReturn=updateStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CustomerDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return toReturn;
    }

    public static ArrayList<Customer> select(){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement selectStatement = null;
        ResultSet rs = null;
        ArrayList<Customer> customers= new ArrayList<Customer>();

        try{
            selectStatement = dbConnection.prepareStatement(selectStatementString);
            rs = selectStatement.executeQuery();

            while(rs.next()){
                int idCustomer= rs.getInt("idCustomer");
                String name= rs.getString("name");
                String address= rs.getString("address");
                String phone= rs.getString("phone");
                System.out.print(idCustomer + ", "+ name + ", " + address+ ", ");
                System.out.println(phone);

                Customer c= new Customer(idCustomer,name,address,phone);
                customers.add(c);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CustomerDAO:select " + e.getMessage());
        } finally {
            ConnectionFactory.close(selectStatement);
            ConnectionFactory.close(dbConnection);
        }
        System.out.println("in met:"+customers.size());
        return customers;
    }


}
