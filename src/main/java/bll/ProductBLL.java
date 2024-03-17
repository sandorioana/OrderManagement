package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.QuantityValidator;
import bll.validators.Validator;
import dataAcces.ProductDAO;
import model.Product;


public class ProductBLL {

    private List<Validator<Product>> validators;
    private QuantityValidator v1;


    /**
     * Checks before inserting a product if all its fields are valid.
     * @param product product to be checked if valid
     * @return
     */
    public int insProduct(Product product) {
        for(Validator<Product> p : validators){
            if(p.validate(product) < 0)
                return -1;
        }
        return 0;
    }

    public Product findProductByName(String name) {
        Product p = ProductDAO.findByName(name);
        if (p == null) {
            throw new NoSuchElementException("The product: " + name + " was not found!");
        }
        return p;
    }

    public int insertProduct(Product p) {
        return ProductDAO.insert(p);
    }

    public int deleteProduct(int id){
        return ProductDAO.delete(id);
    }

    public ArrayList<Product> selectAllProducts(){
        return ProductDAO.select();
    }

    public int updateProduct(int id, int price, int availableQuantity){
        return ProductDAO.update(id, price, availableQuantity);
    }

}