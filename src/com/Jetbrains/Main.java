package com.Jetbrains;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        StartInt lead = new StartInt();
        lead.setTitle("PDF Reader");
        lead.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lead.pack();
        lead.setResizable(false);
        lead.setLocationRelativeTo(null);
        lead.setVisible(true);
    }
}
