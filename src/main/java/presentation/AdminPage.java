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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminPage extends JFrame{
    private static final long serialVersionUID = -5778326523936472702L;

    public AdminPage(){
        setTitle("Connect as Admin");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 100, 400, 250);
        init();
    }

    public void init(){
        JPanel panelLabel = new JPanel();
        panelLabel.setBackground(Color.GRAY);
        panelLabel.setLayout(new BoxLayout(panelLabel,BoxLayout.PAGE_AXIS));
        JPanel panelText = new JPanel();
        panelText.setBackground(Color.GRAY);
        panelText.setLayout(new BoxLayout(panelText,BoxLayout.PAGE_AXIS));
        JPanel bigPanel = new JPanel();
        bigPanel.setBackground(Color.GRAY);

        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.GRAY);
        imagePanel.setLayout(new BoxLayout(imagePanel,BoxLayout.PAGE_AXIS));

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));


        JLabel userLbl= new JLabel();
        userLbl.setText("User:     ");
        userLbl.setFont(new Font("Book Antiqua", Font.BOLD, 14));

        JLabel passLbl= new JLabel();
        passLbl.setText("Password:   ");
        passLbl.setFont(new Font("Book Antiqua", Font.BOLD, 14));

        JTextField user= new JTextField();
        user.setText("admin");
        user.setEditable(false);
        user.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        user.setColumns(20);

        JPasswordField pass= new JPasswordField();
        pass.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        pass.setColumns(20);

        panelLabel.add(userLbl);
        panelText.add(user);
        panelLabel.add(passLbl);
        panelText.add(pass);

        JButton back= new JButton();
        back.setText("Back");
        back.setBackground(new Color(154,199,245));
        back.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new FirstPage();
                setVisible(false);
                dispose();
            }
        });
        JButton connect= new JButton();
        connect.setText("Connect");
        connect.setBackground(new Color(154,199,245));
        connect.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                char[] p= pass.getPassword();
                if(p.length!=0){
                    String s= "";
                    for(int i=0;i<p.length;i++)
                        s=s+p[i];

                    if(s.equals("admin")){
                        new AdminOptPage();
                        setVisible(false);
                        dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Wrong password!", "Connect as Admin", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        bigPanel.add(panelLabel);
        bigPanel.add(panelText);
        bigPanel.add(back);
        bigPanel.add(connect);

        panel.add(imagePanel);
        panel.add(bigPanel);

        setContentPane(panel);
        setVisible(true);
    }

}