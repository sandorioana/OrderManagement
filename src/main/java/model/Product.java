package model;

public class Product {
    private int idProduct;
    private String name;
    private int price;
    private int availableQuantity;

    public Product(int idProduct,String name,int price, int availableQuantity){
        this.idProduct=idProduct;
        this.name=name;
        this.price=price;
        this.availableQuantity=availableQuantity;
    }

    public Product(String name,int price, int availableQuantity){
        this.name=name;
        this.price=price;
        this.availableQuantity=availableQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

}
