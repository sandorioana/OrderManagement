package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bll.CustomerBLL;
import dataAcces.AbstractDAO;

public class ClientTablePage extends JFrame{
    private static final long serialVersionUID = 1L;

    public ClientTablePage(){
        setTitle("Clients Table");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 100, 600, 550);
        init();
    }

    public void init(){
        JPanel panel= new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);

        JButton back = new JButton();
        back.setText("Back");
        back.setBackground(new Color(204,153,255));
        back.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new AdminOptPage();
                setVisible(false);
                dispose();
            }
        });

        JButton print = new JButton();
        print.setText("Show table");
        print.setBackground(new Color(204,153,255));
        print.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CustomerBLL c= new CustomerBLL();
                ArrayList<Object> list= new ArrayList<Object>();
                list.addAll(c.selectAllCustomers());

                JTable table=AbstractDAO.createTable(list);
                table.setFont(new Font("Book Antiqua", Font.BOLD, 14));
                //System.out.println(table.getRowCount());

                JScrollPane scroll = new JScrollPane(table);
                panel.add(table.getTableHeader());
                panel.add(scroll);

                setContentPane(panel);
            }
        });

        panel.add(back);
        panel.add(print);

        setContentPane(panel);
        setVisible(true);
    }

}