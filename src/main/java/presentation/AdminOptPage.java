package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminOptPage extends JFrame{
    private static final long serialVersionUID = 1194513242167797971L;

    public AdminOptPage(){
        setTitle("Admin Options");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 100, 600, 270);
        init();
    }

    public void init(){
        JPanel bigPanel = new JPanel();
        bigPanel.setBackground(Color.LIGHT_GRAY);

        JPanel customerPanel = new JPanel();
        customerPanel.setBackground(Color.LIGHT_GRAY);

        JPanel productPanel = new JPanel();
        productPanel.setBackground(Color.LIGHT_GRAY);


        JButton addC = new JButton();
        addC.setText("Add Customer");
        addC.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        addC.setBackground(new Color(255,204,229));
        addC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new NewClientPage();
                setVisible(false);
                dispose();
            }
        });
        customerPanel.add(addC);

        JButton deleteC = new JButton();
        deleteC.setText("Delete Customer");
        deleteC.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        deleteC.setBackground(new Color(255,204,229));
        deleteC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new DeleteClientPage();
                setVisible(false);
                dispose();
            }
        });
        customerPanel.add(deleteC);

        JButton editC = new JButton();
        editC.setText("Edit Customer");
        editC.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        editC.setBackground(new Color(255,204,229));
        editC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new EditClientPage();
                setVisible(false);
                dispose();
            }
        });
        customerPanel.add(editC);

        JButton viewC = new JButton();
        viewC.setText("List Customers");
        viewC.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        viewC.setBackground(new Color(255,204,229));
        viewC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new ClientTablePage();
                setVisible(false);
                dispose();
            }
        });
        customerPanel.add(viewC);

        JButton addP = new JButton();
        addP.setText("Add Product");
        addP.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        addP.setBackground(new Color(153,255,153));
        addP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new NewProductPage();
                setVisible(false);
                dispose();
            }
        });
        productPanel.add(addP);

        JButton deleteP = new JButton();
        deleteP.setText("Delete Product");
        deleteP.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        deleteP.setBackground(new Color(153,255,153));
        deleteP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new DeleteProductPage();
                setVisible(false);
                dispose();
            }
        });
        productPanel.add(deleteP);

        JButton editP = new JButton();
        editP.setText("Edit Product");
        editP.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        editP.setBackground(new Color(153,255,153));
        editP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new EditProductPage();
                setVisible(false);
                dispose();
            }
        });
        productPanel.add(editP);

        JButton viewP= new JButton();
        viewP.setText("List Products");
        viewP.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        viewP.setBackground(new Color(153,255,153));
        viewP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new ProductTablePage();
                setVisible(false);
                dispose();
            }
        });
        productPanel.add(viewP);

        JButton back = new JButton();
        back.setText("Back");
        back.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        back.setBackground(new Color(153,204,255));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new FirstPage();
                setVisible(false);
                dispose();
            }
        });

        JPanel backPanel= new JPanel();
        backPanel.setBackground(Color.LIGHT_GRAY);
        backPanel.add(back);

        bigPanel.add(customerPanel);
        bigPanel.add(productPanel);
        bigPanel.add(backPanel);
        setContentPane(bigPanel);
        setVisible(true);
    }

}