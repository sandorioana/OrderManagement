package bll;

import java.util.NoSuchElementException;

import dataAcces.OrdersDAO;
import model.Orders;

public class OrdersBLL {
    public Orders findProductById(int idOrder) {
        Orders o = OrdersDAO.findById(idOrder);
        if (o == null) {
            throw new NoSuchElementException("The order with id =" + idOrder + " was not found!");
        }
        return o;
    }

    /**
     * Insert an order the 'order' table from database.
     * @param o the order to be added in the database
     * @return the index in which it was inserted
     */
    public int insertOrder(Orders o) {
        return OrdersDAO.insert(o);
    }


}