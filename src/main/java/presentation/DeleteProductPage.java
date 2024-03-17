package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bll.ProductBLL;

public class DeleteProductPage extends JFrame {
    private static final long serialVersionUID = 1L;

    public DeleteProductPage(){
        setTitle("Delete Product");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 100, 350, 250);
        init();
    }

    public void init(){
        JPanel bigPanel = new JPanel();
        bigPanel.setBackground(Color.LIGHT_GRAY);

        JPanel panelText= new JPanel();
        panelText.setBackground(Color.LIGHT_GRAY);

        JPanel panelBtn= new JPanel();
        panelBtn.setBackground(Color.LIGHT_GRAY);


        JLabel giveId = new JLabel();
        giveId.setText("Give the product's ID: ");
        giveId.setFont(new Font("Book Antiqua", Font.BOLD, 14));

        JTextField id = new JTextField();
        id.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        id.setColumns(10);

        panelText.add(giveId);
        panelText.add(id);

        JButton back= new JButton();
        back.setText("Back");
        back.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new AdminOptPage();
                setVisible(false);
                dispose();
            }
        });
        panelBtn.add(back);

        JButton delete = new JButton();
        delete.setText("Delete Product");
        delete.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ProductBLL p= new ProductBLL();

                int ret= p.deleteProduct(Integer.parseInt(id.getText()));
                if(ret>0)
                    JOptionPane.showMessageDialog(null, "Product deleted!", "Delete Product", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Error while deleting the product!", "Delete Product", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelBtn.add(delete);

        bigPanel.add(panelText);
        bigPanel.add(panelBtn);
        setContentPane(bigPanel);
        setVisible(true);
    }

}
