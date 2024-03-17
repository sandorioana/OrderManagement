package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import bll.CustomerBLL;
import bll.OrdersBLL;
import bll.ProductBLL;
import model.Customer;
import model.Orders;
import model.Product;

public class PlaceOrder extends JFrame{
    private static final long serialVersionUID = 1L;

    public PlaceOrder(){
        setTitle("Place Order");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 100, 400, 280);
        init();
    }

    public void init(){
        JPanel bigPanel= new JPanel();
        bigPanel.setBackground(Color.LIGHT_GRAY);
        bigPanel.setLayout(new BoxLayout(bigPanel,BoxLayout.PAGE_AXIS));

        JPanel choicePanel= new JPanel();
        choicePanel.setBackground(Color.LIGHT_GRAY);
        choicePanel.setLayout(new BoxLayout(choicePanel,BoxLayout.PAGE_AXIS));

        JPanel textPanel= new JPanel();
        textPanel.setBackground(Color.LIGHT_GRAY);
        textPanel.setLayout(new BoxLayout(textPanel,BoxLayout.PAGE_AXIS));

        JPanel backPanel= new JPanel();
        backPanel.setBackground(Color.LIGHT_GRAY);

        JPanel panel= new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);

        JPanel imgPanel= new JPanel();
        imgPanel.setBackground(Color.LIGHT_GRAY);

        JLabel idLbl= new JLabel();
        idLbl.setText("Customer ID:");
        idLbl.setFont(new Font("Book Antiqua", Font.BOLD, 15));

        JLabel productLbl = new JLabel();
        productLbl.setText("Product");
        productLbl.setFont(new Font("Book Antiqua", Font.BOLD, 15));

        JLabel quantityLbl = new JLabel();
        quantityLbl.setText("Quantity");
        quantityLbl.setFont(new Font("Book Antiqua", Font.BOLD, 15));

        textPanel.add(idLbl);
        textPanel.add(productLbl);
        textPanel.add(quantityLbl);

        CustomerBLL c= new CustomerBLL();
        ArrayList<Customer> customers= c.selectAllCustomers();
        String[] ids= new String[customers.size()];
        for(int i=0;i<customers.size();i++)
            ids[i]=String.valueOf(customers.get(i).getIdCustomer());
        JComboBox<String> idCombo= new JComboBox<>(ids);
        idCombo.setFont(new Font("Book Antiqua", Font.BOLD, 12));
        choicePanel.add(idCombo);

        ProductBLL p= new ProductBLL();
        ArrayList<Product> products= p.selectAllProducts();
        String[] items= new String[products.size()];
        for(int i=0;i<products.size();i++)
            items[i]=products.get(i).getName();
        JComboBox<String> itemsCombo= new JComboBox<>(items);
        itemsCombo.setFont(new Font("Book Antiqua", Font.BOLD, 12));
        choicePanel.add(itemsCombo);

        JTextField quantity = new JTextField();
        quantity.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        quantity.setColumns(5);
        choicePanel.add(quantity);

        panel.add(textPanel);
        panel.add(choicePanel);

        JButton get= new JButton();
        get.setText("Place Order");
        get.setBackground(new Color(255,131,131));
        get.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        get.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                ProductBLL p= new ProductBLL();
                Product product= p.findProductByName((String) itemsCombo.getSelectedItem());
                if(product.getAvailableQuantity()>=Integer.parseInt(quantity.getText())){
                    Orders order= new Orders(Integer.parseInt(quantity.getText()),Integer.parseInt((String)idCombo.getSelectedItem()),product.getIdProduct());
                    OrdersBLL o = new OrdersBLL();
                    int ret=o.insertOrder(order);
                    if(ret>0)
                        JOptionPane.showMessageDialog(null, "Order added!", "Place Order", JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "Error while adding the order!", "Place Order", JOptionPane.ERROR_MESSAGE);
                    p.updateProduct(product.getIdProduct(), product.getPrice(), product.getAvailableQuantity()-1);

                    CustomerBLL c= new CustomerBLL();
                    Customer customer= c.findCustomerById(Integer.parseInt((String)idCombo.getSelectedItem()));
                    List<String> lines= Arrays.asList("Bill with no."+customer.getIdCustomer()," ","Your order has been registered and it is being processed."," ","Delivery addreess: "+customer.getAddress()," ","Personal data:",customer.getName(),customer.getPhone()," ","Product to deliver:",product.getName() + " Price: "+ product.getPrice()+ " lei","Quantity:"+order.getQuantity(),"Total Price: "+product.getPrice()*order.getQuantity()+ " lei"," ","We thank you for choosing us "+customer.getName()+". Have a nice day!");
                    Path file= Paths.get("file"+customer.getIdCustomer()+".txt");
                    try {
                        Files.write(file, lines,  Charset.forName("UTF-8"));
                    } catch (IOException e) {
                        System.out.println("Can't print the bill!");
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Not enough products on the stock!", "Place Order", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton back = new JButton();
        back.setText("Back");
        back.setBackground(new Color(255,131,131));
        back.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new FirstPage();
                setVisible(false);
                dispose();
            }
        });

        backPanel.add(back);
        backPanel.add(get);

        bigPanel.add(imgPanel);
        bigPanel.add(panel);
        bigPanel.add(backPanel);
        setContentPane(bigPanel);
        setVisible(true);
    }

}