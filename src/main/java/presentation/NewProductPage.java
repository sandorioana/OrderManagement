package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bll.ProductBLL;
import model.Product;

public class NewProductPage extends JFrame {
    private static final long serialVersionUID = 1L;

    public NewProductPage(){
        setTitle("New Product");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 100, 600, 220);
        init();
    }

    public void init(){
        JPanel bigPanel = new JPanel();
        bigPanel.setBackground(Color.LIGHT_GRAY);

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(Color.LIGHT_GRAY);

        JPanel panelLbl = new JPanel();
        panelLbl.setBackground(Color.LIGHT_GRAY);
        panelLbl.setLayout(new BoxLayout(panelLbl,BoxLayout.PAGE_AXIS));

        JPanel panelText = new JPanel();
        panelText.setBackground(Color.LIGHT_GRAY);
        panelText.setLayout(new BoxLayout(panelText,BoxLayout.PAGE_AXIS));

        JPanel panelImg = new JPanel();
        panelImg.setBackground(Color.LIGHT_GRAY);
        panelImg.setLayout(new BoxLayout(panelImg,BoxLayout.PAGE_AXIS));

        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));

        JLabel nameLbl= new JLabel();
        nameLbl.setText("Name: ");
        nameLbl.setFont(new Font("Book Antiqua", Font.BOLD, 14));

        JLabel priceLbl = new JLabel();
        priceLbl.setText("Price: ");
        priceLbl.setFont(new Font("Book Antiqua", Font.BOLD, 14));

        JLabel quantityLbl = new JLabel();
        quantityLbl.setText("Available quantity: ");
        quantityLbl.setFont(new Font("Book Antiqua", Font.BOLD, 14));

        panelLbl.add(nameLbl);
        panelLbl.add(priceLbl);
        panelLbl.add(quantityLbl);

        JTextField name= new JTextField();
        name.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        name.setColumns(30);

        JTextField price= new JTextField();
        price.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        price.setColumns(30);

        JTextField quantity= new JTextField();
        quantity.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        quantity.setColumns(30);

        panelText.add(name);
        panelText.add(price);
        panelText.add(quantity);

        JButton back = new JButton();
        back.setText("Back");
        back.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new AdminOptPage();
                setVisible(false);
                dispose();
            }
        });

        JButton add = new JButton();
        add.setText("Add Product");
        add.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Product product = new Product(name.getText(),Integer.parseInt(price.getText()),Integer.parseInt(quantity.getText()));
                ProductBLL p= new ProductBLL();
                int ret= p.insertProduct(product);
                if(ret<0)
                    JOptionPane.showMessageDialog(null, "Error while adding the product!", "New Product", JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Product added!", "New Product", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton reset = new JButton();
        reset.setText("Reset");
        reset.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                name.setText("");
                price.setText("");
                quantity.setText("");
            }
        });

        panelBtn.add(back);
        panelBtn.add(reset);
        panelBtn.add(add);

        bigPanel.add(panelLbl);
        bigPanel.add(panelText);
        bigPanel.add(panelBtn);

        panel.add(panelImg);
        panel.add(bigPanel);

        setContentPane(panel);
        setVisible(true);
    }

}
