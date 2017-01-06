package com.Jetbrains;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SigningIn extends JFrame{

    private JTextField name, newUserName, email, age;
    private JLabel namelbl, newUserNamelbl, emaillbl, agelbl, newUserPasslbl, rePasslbl;
    private JPasswordField newUserPass, rePass;
    private JButton submitB;
    private GridBagConstraints gridbc;

    public SigningIn(){

        super("New User");

        setLayout(new GridBagLayout());

        gridbc = new GridBagConstraints();
        gridbc.insets = new Insets(10, 10, 10, 10);

        //PANEL WITH PROMPT LABELS
        JPanel panelLabels = new JPanel();
        panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.PAGE_AXIS));
        panelLabels.setBorder(BorderFactory.createMatteBorder(5, 10, 5, 5, Color.LIGHT_GRAY));

        namelbl = new JLabel("Enter your name");
        gridbc.gridx = 1;
        gridbc.gridy = 1;

        agelbl = new JLabel("Enter your age");
        gridbc.gridx = 1;
        gridbc.gridy = 2;

        newUserNamelbl = new JLabel("Enter a username");
        gridbc.gridx = 1;
        gridbc.gridy = 3;

        newUserPasslbl = new JLabel("Enter a password");
        gridbc.gridx = 1;
        gridbc.gridy = 4;

        rePasslbl = new JLabel("Re-enter your password");
        gridbc.gridx = 1;
        gridbc.gridy = 5;

        emaillbl = new JLabel("Enter your email");
        gridbc.gridx = 1;
        gridbc.gridy = 6;

        panelLabels.add(namelbl);
        panelLabels.add(Box.createRigidArea(new Dimension(10, 41)));
        panelLabels.add(agelbl);
        panelLabels.add(Box.createRigidArea(new Dimension(10, 41)));
        panelLabels.add(newUserNamelbl);
        panelLabels.add(Box.createRigidArea(new Dimension(10, 41)));
        panelLabels.add(newUserPasslbl);
        panelLabels.add(Box.createRigidArea(new Dimension(10, 41)));
        panelLabels.add(rePasslbl);
        panelLabels.add(Box.createRigidArea(new Dimension(10, 41)));
        panelLabels.add(emaillbl);

        add(panelLabels, gridbc);

        //PANEL WITH USER FIELDS
        JPanel panelUsers = new JPanel();
        panelUsers.setLayout(new BoxLayout(panelUsers, BoxLayout.PAGE_AXIS));
        panelUsers.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 10, Color.LIGHT_GRAY));

        name = new JTextField("name...");
        gridbc.gridx = 2;
        gridbc.gridy = 1;
        name.setForeground(Color.lightGray);
        name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                name.selectAll();
            }
        });
        name.setPreferredSize(new Dimension(180, 50));

        age = new JTextField("age... ");
        gridbc.gridx = 2;
        gridbc.gridy = 2;
        age.setForeground(Color.lightGray);
        age.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                age.selectAll();
            }
        });
        age.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                char character = e.getKeyChar();
                if((character < '0') || (character > '9') && (character != '\b')){
                    e.consume();
                }
            }
        });
        age.setPreferredSize(new Dimension(180, 50));

        newUserName = new JTextField("username...");
        gridbc.gridx = 2;
        gridbc.gridy = 3;
        newUserName.setForeground(Color.lightGray);
        newUserName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                newUserName.selectAll();
            }
        });
        newUserName.setPreferredSize(new Dimension(180, 50));

        email = new JTextField("Email@...");
        gridbc.gridx = 2;
        gridbc.gridy = 4;
        email.setForeground(Color.lightGray);
        email.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                email.selectAll();
            }
        });
        email.setPreferredSize(new Dimension(180, 50));

        newUserPass = new JPasswordField("password...");
        gridbc.gridx = 2;
        gridbc.gridy = 5;
        newUserPass.setForeground(Color.lightGray);
        newUserPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                newUserPass.selectAll();
            }
        });
        newUserPass.setPreferredSize(new Dimension(180, 50));

        rePass = new JPasswordField("re-enter password...");
        gridbc.gridx = 2;
        gridbc.gridy = 6;
        rePass.setForeground(Color.lightGray);
        rePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rePass.selectAll();
            }
        });
        rePass.setPreferredSize(new Dimension(180, 50));

        panelUsers.add(name);
        panelUsers.add(Box.createRigidArea(new Dimension(2, 3)));
        panelUsers.add(age);
        panelUsers.add(Box.createRigidArea(new Dimension(2, 3)));
        panelUsers.add(newUserName);
        panelUsers.add(Box.createRigidArea(new Dimension(2, 3)));
        panelUsers.add(newUserPass);
        panelUsers.add(Box.createRigidArea(new Dimension(2, 3)));
        panelUsers.add(rePass);
        panelUsers.add(Box.createRigidArea(new Dimension(2, 3)));
        panelUsers.add(email);

        add(panelUsers, gridbc);

        //PANEL FOR THE SUBMIT BUTTON
        JPanel bPanel = new JPanel();

        submitB = new JButton("SUBMIT");
        gridbc.gridx = 1;
        gridbc.gridy = 7;
        submitB.setPreferredSize(new Dimension(150, 35));
        submitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        bPanel.add(submitB);

        add(bPanel, gridbc);
    }
}
