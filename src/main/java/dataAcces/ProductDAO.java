package dataAcces;

import java.beans.IntrospectionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Product;

public class ProductDAO extends AbstractMethods<Product> {
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO product (name,price,availableQuantity)"
            + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM product where name = ?";
    private final static String deleteStatementString ="DELETE FROM product WHERE idProduct = ?";
    private final static String selectStatementString ="SELECT * FROM product";
    private final static String updateStatementString ="UPDATE product SET price=?,availableQuantity=? WHERE idProduct=?";

    public static Product findByName(String productName){
        Product toReturn= null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;

        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setString(1, productName);
            rs = findStatement.executeQuery();
            rs.next();

            int idProduct= rs.getInt("idProduct");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            int availableQuantity = rs.getInt("availableQuantity");

            toReturn = new Product(idProduct, name,price,availableQuantity);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        System.out.println(toReturn.getIdProduct()+" " +toReturn.getName()+ " "+ toReturn.getAvailableQuantity() );
        return toReturn;
    }

    public static int insert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;

        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getName());
            insertStatement.setInt(2, product.getPrice());
            insertStatement.setInt(3, product.getAvailableQuantity());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static int delete(int idProduct){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int toReturn = 0;

        try{
            deleteStatement = dbConnection.prepareStatement(deleteStatementString);
            deleteStatement.setInt(1, idProduct);

            toReturn=deleteStatement.executeUpdate(); // returns 1 for success and 0 for fail
        }
        catch(SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static int update(int idProduct, int price, int availableQuantity){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int toReturn=-1;

        try{
            updateStatement= dbConnection.prepareStatement(updateStatementString);
            updateStatement.setInt(1,price);
            updateStatement.setInt(2,availableQuantity);
            updateStatement.setInt(3,idProduct);

            toReturn=updateStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return toReturn;
    }

    public static ArrayList<Product> select(){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement selectStatement = null;
        ResultSet rs = null;
        ArrayList<Product> products = new ArrayList<Product>();

        try{
            selectStatement = dbConnection.prepareStatement(selectStatementString);
            rs = selectStatement.executeQuery();

            while(rs.next()){
                int idProduct= rs.getInt("idProduct");
                String name= rs.getString("name");
                int price= rs.getInt("price");
                int availableQuantity= rs.getInt("availableQuantity");
                System.out.print( name + ", " + price+ ", ");
                System.out.println(availableQuantity);
                Product p= new Product(idProduct,name,price,availableQuantity);
                products.add(p);
            }
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:select " + e.getMessage());
        } finally {
            ConnectionFactory.close(selectStatement);
            ConnectionFactory.close(dbConnection);
        }

        return products;
    }

    AbstractMethods<Product> p1;

    @Override
    public List<Product> findAll() {
        return super.findAll();
    }

    @Override
    public Product findById(int id) throws SQLException {
        return super.findById(id);
    }

    @Override
    public Product insert(Product product, Connection myConn, ResultSet resultSet) throws IntrospectionException {
        return super.insert(product, myConn, resultSet);
    }

    @Override
    public Product delete(int id, Product product, Connection myConn, ResultSet resultSet) throws IntrospectionException, SQLException {
        return super.delete(id, product, myConn, resultSet);
    }

    @Override
    public Product update(int id, Product product, Connection myConn, ResultSet resultSet) throws IntrospectionException {
        return super.update(id, product, myConn, resultSet);
    }

}