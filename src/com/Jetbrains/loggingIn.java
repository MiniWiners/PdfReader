package com.Jetbrains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loggingIn extends JFrame {

    private JTextField userName;
    private JPasswordField passWord;
    private JButton submitB;
    private JLabel userlbl, passlbl;
    private GridBagConstraints gridC;

    public loggingIn(){

        super("Enter your credentials");

        setLayout(new GridBagLayout());

        gridC = new GridBagConstraints();
        gridC.insets = new Insets(5, 5, 5, 5);

        //PANEL WITH PROMPT LABELS
        JPanel panelLabels = new JPanel();
        panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.PAGE_AXIS));
        panelLabels.setBorder(BorderFactory.createMatteBorder(5, 10, 5, 5, Color.LIGHT_GRAY));

        userlbl = new JLabel("Enter Username");
        gridC.gridx = 1;
        gridC.gridy = 1;
        userlbl.setPreferredSize(new Dimension(180, 30));

        passlbl = new JLabel("Enter Password");
        gridC.gridx = 1;
        gridC.gridy = 2;
        passlbl.setPreferredSize(new Dimension(180, 30));

        panelLabels.add(userlbl);
        panelLabels.add(Box.createRigidArea(new Dimension(10, 30)));
        panelLabels.add(passlbl);

        add(panelLabels, gridC);

        //PANEL WITH USER FIELDS
        JPanel panelUser = new JPanel();
        panelUser.setLayout(new BoxLayout(panelUser, BoxLayout.PAGE_AXIS));
        panelUser.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 10, Color.LIGHT_GRAY));

        userName = new JTextField("username...");
        gridC.gridx = 2;
        gridC.gridy = 1;
        userName.setForeground(Color.lightGray);
        userName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                userName.selectAll();
            }
        });
        userName.setPreferredSize(new Dimension(180, 50));

        passWord = new JPasswordField("password...");
        gridC.gridx = 2;
        gridC.gridy = 2;
        passWord.setForeground(Color.lightGray);
        passWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                passWord.selectAll();
            }
        });
        passWord.setPreferredSize(new Dimension(180, 50));

        panelUser.add(userName);
        panelUser.add(Box.createRigidArea(new Dimension(2, 3)));
        panelUser.add(passWord);

        add(panelUser, gridC);

        //PANEL FOR THE SUBMIT BUTTON
        JPanel bPanel = new JPanel();

        submitB = new JButton("SUBMIT");
        gridC.gridx = 1;
        gridC.gridy = 3;
        submitB.setPreferredSize(new Dimension(150, 35));
        submitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = userName.getText().trim();
                String password = passWord.getText().trim();

                if(username.equals("Omar") && password.equals("Berraj")){
                    JOptionPane.showMessageDialog(null, "Welcome "+ username);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Wrong credentials");
                }
            }
        });
        bPanel.add(submitB);

        add(bPanel, gridC);
    }

}
