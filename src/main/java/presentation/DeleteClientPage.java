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

import bll.CustomerBLL;

public class DeleteClientPage extends JFrame{
    private static final long serialVersionUID = 1L;

    public DeleteClientPage(){
        setTitle("Delete Customer");
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
        giveId.setText("Give the customer's ID: ");
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
        delete.setText("Delete Customer");
        delete.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CustomerBLL c = new CustomerBLL();
                int ret= c.deleteCustomer(Integer.parseInt(id.getText()));
                if(ret>0)
                    JOptionPane.showMessageDialog(null, "Customer deleted!", "Delete customer", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Error while deleting the customer!", "Delete Customer", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelBtn.add(delete);

        bigPanel.add(panelText);
        bigPanel.add(panelBtn);
        setContentPane(bigPanel);
        setVisible(true);
    }

}
