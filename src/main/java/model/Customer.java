package model;

public class Customer {
    private int idCustomer;
    private String name;
    private String address;
    private String phone;

    public Customer(int idCustomer,String name, String address,String phone){
        this.idCustomer=idCustomer;
        this.name=name;
        this.address=address;
        this.phone=phone;
    }

    public Customer(String name, String address,String phone){
        this.name=name;
        this.address=address;
        this.phone=phone;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}