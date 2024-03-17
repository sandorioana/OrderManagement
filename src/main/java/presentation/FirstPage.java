package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FirstPage extends JFrame {
    private static final long serialVersionUID = 1L;

    public FirstPage(){
        setTitle("Application");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 100, 400, 270);
        init();
    }

    public void init(){
        JPanel panel = new JPanel();
        JButton placeOrder = new JButton();
        JButton connAdmin = new JButton();

        panel.setBackground(Color.GRAY);

        placeOrder.setBackground(Color.ORANGE);
        placeOrder.setText("Place Order");
        placeOrder.setFont(new Font("Book Antiqua", Font.BOLD, 30));
        placeOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new PlaceOrder();
                setVisible(false);
                dispose();
            }
        });

        connAdmin.setBackground(Color.ORANGE);
        connAdmin.setText("Connect as Admin");
        connAdmin.setFont(new Font("Book Antiqua", Font.BOLD, 30));
        connAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new AdminPage();
                dispose();
                setVisible(false);
            }
        });

        panel.add(placeOrder);
        panel.add(connAdmin);

        setContentPane(panel);
        setVisible(true);
    }

    public static void main(String[] args){

        new FirstPage();
    }

}
