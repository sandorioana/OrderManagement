package bll.validators;

import model.Product;

public class QuantityValidator implements Validator<Product>{

    /**
     * Validates the quantity object
     * @param product the product to be validated
     * @return 0 or -1
     */
    @Override
    public int validate(Product product) {
        if(product.getAvailableQuantity() < 0){
            return -1;
        }
        else
            return 0;
    }

}
