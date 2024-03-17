package dataAcces;

import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AbstractMethods<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractMethods.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractMethods() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    public List<T> findAll() {
        ResultSet resultSet = null;
        String query = createSelectQuery(null);
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, type.getName() + "DAO: findAll" + e.getMessage());
        }finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    public T findById(int id) throws SQLException {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        }
        catch (IndexOutOfBoundsException e) {
            //JOptionPane.showMessageDialog(null, "The key Not Found", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return null;
    }


    public List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = null;


                    Method method=null;
                    try{
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                        method = propertyDescriptor.getWriteMethod();
                    }
                    catch (IntrospectionException e){}


                    try{
                        method.invoke(instance, value);
                    }
                    catch (IllegalArgumentException e){
                    }
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public T insert(T t,java.sql.Connection myConn,ResultSet resultSet) throws IntrospectionException {
        //System.out.println(this.type.getSimpleName());

        try {
            Statement insertStmt = myConn.createStatement();

            String s; //formam stringul pentru execute
            s= "INSERT INTO " + this.type.getSimpleName() + " (";
            for (Field field : type.getDeclaredFields()) {
                s = s + " " + field.getName() + " ,";
            }
            s = s.substring(0,s.length()-1) + ") VALUES (";


            for (Field field : type.getDeclaredFields()) {

                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getReadMethod();

                try{
                    //method.invoke(t, value)
                    s = s + " '" + method.invoke(t) + "' ,";
                }
                catch (IllegalArgumentException e){
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                };
            }

            s = s.substring(0,s.length()-1) + " );";
            System.out.println(s);
            insertStmt.execute(s);
            //insertStmt.execute("INSERT INTO Product (ID, Name, Price) VALUES " + "("
            //		+ product.getId() + ", '" + product.getName() + "', " + product.getPrice() + ")");


        } catch (SQLException e) {
            return null;
        }
        return t;

    }

    public T delete(int id,T t,java.sql.Connection myConn,ResultSet resultSet) throws IntrospectionException, SQLException {

        Statement insertStmt = myConn.createStatement();

        String s; //formam stringul pentru execute
        s= "DELETE FROM " + this.type.getSimpleName() + " WHERE ID= "+ id+ ";";

        try{
            insertStmt.execute(s);
        }
        catch (SQLException e) {
            return null;
        }
        return t;

    }

    public T update(int id,T t,java.sql.Connection myConn,ResultSet resultSet) throws IntrospectionException {
        //System.out.println(this.type.getSimpleName());

        try {
            Statement insertStmt = myConn.createStatement();

            String s; //formam stringul pentru execute
            s= "UPDATE " + this.type.getSimpleName() + " SET";


            for (Field field : type.getDeclaredFields()) {
                s = s + " " + field.getName() + " = ";

                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getReadMethod();

                try{
                    s = s + " '" + method.invoke(t) + "' ,";
                }
                catch (IllegalArgumentException e){
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                };
            }
            s = s.substring(0,s.length()-1) + " WHERE id = "+id+";";
            insertStmt.execute(s);
        } catch (SQLException e) {
            return null;
        }
        return t;

    }

}