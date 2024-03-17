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

public class EditProductPage extends JFrame {
    private static final long serialVersionUID = 1L;

    public EditProductPage(){
        setTitle("Edit Product");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 100, 600, 250);
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


        JLabel giveId = new JLabel();
        giveId.setText("Give the product's ID: ");
        giveId.setFont(new Font("Book Antiqua", Font.BOLD, 14));

        JLabel priceLbl = new JLabel();
        priceLbl.setText("Price: ");
        priceLbl.setFont(new Font("Book Antiqua", Font.BOLD, 14));

        JLabel quantityLbl = new JLabel();
        quantityLbl.setText("Available quantity: ");
        quantityLbl.setFont(new Font("Book Antiqua", Font.BOLD, 14));

        panelLbl.add(giveId);
        panelLbl.add(priceLbl);
        panelLbl.add(quantityLbl);


        JTextField id = new JTextField();
        id.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        id.setColumns(10);

        JTextField price= new JTextField();
        price.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        price.setColumns(30);

        JTextField quantity= new JTextField();
        quantity.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        quantity.setColumns(30);

        panelText.add(id);
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

        JButton edit = new JButton();
        edit.setText("Edit Product");
        edit.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ProductBLL p= new ProductBLL();
                int ret= p.updateProduct(Integer.parseInt(id.getText()),Integer.parseInt( price.getText()), Integer.parseInt(quantity.getText()));
                if(ret<0)
                    JOptionPane.showMessageDialog(null, "Error while editing the product!", "Edit Product", JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Product edited!", "Edit Product", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton reset = new JButton();
        reset.setText("Reset");
        reset.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                id.setText("");
                price.setText("");
                quantity.setText("");
            }
        });

        panelBtn.add(back);
        panelBtn.add(reset);
        panelBtn.add(edit);

        bigPanel.add(panelLbl);
        bigPanel.add(panelText);
        bigPanel.add(panelBtn);
        panel.add(panelImg);
        panel.add(bigPanel);

        setContentPane(panel);
        setVisible(true);
    }

}