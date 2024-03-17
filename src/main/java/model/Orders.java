package model;

public class Orders {
    private int idOrder;
    private int quantity;
    private int customer_idCustomer;
    private int product_idProduct;

    public Orders(int idOrder, int quantity,int customer_idCustomer, int product_idProduct){
        this.idOrder=idOrder;
        this.quantity=quantity;
        this.customer_idCustomer=customer_idCustomer;
        this.product_idProduct=product_idProduct;
    }

    public Orders( int quantity,int customer_idCustomer, int product_idProduct){
        this.quantity=quantity;
        this.customer_idCustomer=customer_idCustomer;
        this.product_idProduct=product_idProduct;
    }

    public Orders(int quantity){
        this.quantity=quantity;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCustomer_idCustomer() {
        return customer_idCustomer;
    }

    public void setCustomer_idCustomer(int customer_idCustomer) {
        this.customer_idCustomer = customer_idCustomer;
    }

    public int getProduct_idProduct() {
        return product_idProduct;
    }

    public void setProduct_idProduct(int product_idProduct) {
        this.product_idProduct = product_idProduct;
    }


}