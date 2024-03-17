package dataAcces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Orders;

public class OrdersDAO {
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO orders (quantity,customer_idCustomer,product_idProduct)"
            + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM orders WHERE idOrder = ?";
    private final static String selectStatementString ="SELECT * FROM orders";
    private final static String listAllStatementString ="SELECT idOrder,customer.name, product.name, product.price, orders.quantity FROM customer, product, orders WHERE customer.idCustomer= orders.customer_idCustomer and product.idProduct=orders.product_idProduct";

    /**
     * Find by id an order.
     * @param orderId is the id order
     * @return the order
     */
    public static Orders findById(int orderId){
        Orders toReturn= null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;

        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, orderId);
            rs = findStatement.executeQuery();
            rs.next();

            int quantity = rs.getInt("quantity");
            int customer_idCustomer = rs.getInt("customer_idCustomer");
            int product_idProduct = rs.getInt("product_idProduct");

            toReturn = new Orders(orderId, quantity,customer_idCustomer,product_idProduct);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrdersDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        System.out.println(toReturn.getIdOrder()+" " +toReturn.getQuantity());
        return toReturn;
    }
    /**
     * Insert an order the 'order' table from database.
     * @param order the order to be added in the database
     * @return the index in which it was inserted
     */
    public static int insert(Orders order) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;

        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, order.getQuantity());
            insertStatement.setInt(2, order.getCustomer_idCustomer());
            insertStatement.setInt(3, order.getProduct_idProduct());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();

            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrdersDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

}