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

import bll.CustomerBLL;

public class EditClientPage extends JFrame{
    private static final long serialVersionUID = 1L;

    public EditClientPage(){
        setTitle("Edit Customer");
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
        giveId.setText("Give the customer's ID: ");
        giveId.setFont(new Font("Book Antiqua", Font.BOLD, 14));

        JLabel addressLbl = new JLabel();
        addressLbl.setText("Address: ");
        addressLbl.setFont(new Font("Book Antiqua", Font.BOLD, 14));

        JLabel phoneLbl = new JLabel();
        phoneLbl.setText("Phone number: ");
        phoneLbl.setFont(new Font("Book Antiqua", Font.BOLD, 14));

        panelLbl.add(giveId);
        panelLbl.add(addressLbl);
        panelLbl.add(phoneLbl);


        JTextField id = new JTextField();
        id.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        id.setColumns(10);

        JTextField address= new JTextField();
        address.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        address.setColumns(30);

        JTextField phone= new JTextField();
        phone.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        phone.setColumns(30);

        panelText.add(id);
        panelText.add(address);
        panelText.add(phone);

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
        edit.setText("Edit Customer");
        edit.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CustomerBLL c= new CustomerBLL();
                int ret= c.updateCustomer(Integer.parseInt(id.getText()), address.getText(), phone.getText());
                if(ret<0)
                    JOptionPane.showMessageDialog(null, "Error while editing the customer!", "Edit Customer", JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Customer edited!", "Edit Customer", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton reset = new JButton();
        reset.setText("Reset");
        reset.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                id.setText("");
                address.setText("");
                phone.setText("");
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