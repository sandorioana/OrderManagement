package bll;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import dataAcces.CustomerDAO;
import model.Customer;

public class CustomerBLL {

    /**
     * Find customer by id.
     * @param idCustomer the id for customer
     * @return the index
     */
    public Customer findCustomerById(int idCustomer) {
        Customer c = CustomerDAO.findById(idCustomer);
        if (c == null) {
            throw new NoSuchElementException("The customer with id =" + idCustomer + " was not found!");
        }
        return c;
    }

    /**
     * Insert an customer in the 'customer' table from database.
     * @param c the customer to be added in the database
     * @return the index in which it was inserted
     */
    public int insertCustomer(Customer c) {
        return CustomerDAO.insert(c);
    }

    /**
     * Delete an customer in the 'customer' table from database.
     * @param id the order to be deleted in the database
     * @return the index in which it was deleted
     */
    public int deleteCustomer(int id){
        return CustomerDAO.delete(id);
    }

    public ArrayList<Customer> selectAllCustomers(){
        return CustomerDAO.select();
    }

    /**
     * Select an customer from database.
     * @param id is the id customer to be updated in the database
     * @return the index in which it was updated
     */
    public int updateCustomer(int id, String address, String phone){
        return CustomerDAO.update(id, address, phone);
    }

}