package com.Jetbrains;


import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;

public class StartInt extends JFrame {

    private String[] elementsToSearch = { "Books", "Groups", "Authors" };
    private static final String search = "Search...";
    private String choice = "";

    private final JTextField searchBar;
    private JPanel together;
    private JMenuBar menuBar;
    private JButton logIn, SignIn;
    private JButton popDownButton;
    private JTextArea top5Books, famousQuotes, bestComments;
    private GridBagConstraints gridLayoutc;

    //CONSTRUCTOR FOR THE ENTRY INTERFACE
    public StartInt(){

        super("Book Reader");

        setLayout(new GridBagLayout());
        gridLayoutc = new GridBagConstraints();
        gridLayoutc.insets = new Insets(5, 5, 5, 5);

        //Search Field
        searchBar = new JTextField(20);
        searchBar.setText(search);
        searchBar.setForeground(Color.GRAY);
        searchBar.setPreferredSize(new Dimension(250, 60));
        searchBar.setVisible(true);
        searchBar.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent event){

                ((AbstractDocument) searchBar.getDocument()).setDocumentFilter(new DocumentFilter() {
                    @Override
                    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {

                        if(offset < search.length()) {
                            return;
                        }
                        super.insertString(fb, offset, string, attr);
                    }

                    @Override
                    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

                        if(offset < search.length()){
                            length = Math.max(0, length - search.length());
                            offset = search.length();
                        }
                        super.replace(fb, offset, length, text, attrs);
                    }

                    @Override
                    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {

                        if(offset < search.length()) {
                            length = Math.max(0, length + offset - search.length());
                            offset = search.length();
                        }
                        if(length > 0){
                            super.remove(fb, offset, length);
                        }
                    }
                });
            }
        });
        searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //Search in the DataBase
            }
        });

        //SEARCH OPTIONS
        popDownButton = new JButton(new ImageIcon(new ImageIcon("downArrow.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        popDownButton.setPreferredSize(new Dimension(25, 60));
        popDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                String choice = (String) JOptionPane.showInputDialog(null,
                        "Choose a search item", "User input", JOptionPane.QUESTION_MESSAGE,
                        null, elementsToSearch, elementsToSearch[0]);
            }
        });

        //PUTTING THE SEARCH FIELD AND SCROLL OPTION SIDE-BY-SIDE
        together = new JPanel(new GridBagLayout());
        gridLayoutc.gridx = 1;
        gridLayoutc.gridy = 2;
        together.add(popDownButton);
        together.add(searchBar);
        together.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.LIGHT_GRAY));
        add(together, gridLayoutc);

        //TEXT AREA CONTAINING UPDATED LIST FROM THE TOP 5 MOST READ BOOKS
        top5Books = new JTextArea();
        gridLayoutc.gridx = 1;
        gridLayoutc.gridy = 3;
        top5Books.setLineWrap(true);
        top5Books.setWrapStyleWord(true);
        top5Books.setPreferredSize(new Dimension(350, 300));
        top5Books.setEditable(false);
        top5Books.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(5, 5, 5, 5, Color.LIGHT_GRAY),
                BorderFactory.createTitledBorder("Top 5 Most Read Books ")));
        top5Books.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //from database
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //from database
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //from database
            }
        });
        add(top5Books, gridLayoutc);

        //PANELS FOR FAMOUS QUOTES AND BEST COMMENTS
        JPanel panelforQC = new JPanel();
        panelforQC.setLayout(new BoxLayout(panelforQC, BoxLayout.PAGE_AXIS));
        panelforQC.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.lightGray));

        famousQuotes = new JTextArea();
        famousQuotes.setBorder(BorderFactory.createTitledBorder("Famous quotes from books"));
        gridLayoutc.gridx = 3;
        gridLayoutc.gridy = 2;
        famousQuotes.setLineWrap(true);
        famousQuotes.setWrapStyleWord(true);
        famousQuotes.setPreferredSize(new Dimension(200, 200));
        famousQuotes.setEditable(false);

        bestComments = new JTextArea();
        bestComments.setBorder(BorderFactory.createTitledBorder("Best comments about books"));
        gridLayoutc.gridx = 3;
        gridLayoutc.gridy = 3;
        bestComments.setLineWrap(true);
        bestComments.setWrapStyleWord(true);
        bestComments.setPreferredSize(new Dimension(200, 200));
        bestComments.setEditable(false);

        panelforQC.add(famousQuotes);
        panelforQC.add(Box.createRigidArea(new Dimension(5, 5)));
        panelforQC.add(bestComments);

        famousQuotes.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //from database
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //from database
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //from databse
            }
        });
        
        bestComments.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //from databse
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //from database
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //from database
            }
        });
        add(panelforQC, gridLayoutc);

        //LOGIN AND SING-IN BUTTONS
        JPanel panelforButtons = new JPanel(new GridBagLayout());

        logIn = new JButton("Login");
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                loggingIn logInInter = new loggingIn();
                logInInter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                logInInter.pack();
                logInInter.setResizable(false);
                logInInter.setLocationRelativeTo(null);
                logInInter.setVisible(true);
                dispose();
            }
        });
        gridLayoutc.gridx = 2;
        gridLayoutc.gridy = 1;

        SignIn = new JButton("Sign In");
        SignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SigningIn signInInter = new SigningIn();
                signInInter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                signInInter.pack();
                signInInter.setResizable(false);
                signInInter.setLocationRelativeTo(null);
                signInInter.setVisible(true);
                dispose();
            }
        });
        gridLayoutc.gridx = 3;
        gridLayoutc.gridy = 1;

        panelforButtons.add(logIn);
        panelforButtons.add(SignIn);
        add(panelforButtons, gridLayoutc);

        //CREATING A MENU-BAR AND SUB-MENUS
        menuBar = new JMenuBar();

        ImageIcon iconSettings = new ImageIcon(new ImageIcon("settings.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconExit = new ImageIcon(new ImageIcon("exit.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconNew = new ImageIcon(new ImageIcon("new.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconOpenRecent = new ImageIcon(new ImageIcon("openrecent.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconFile = new ImageIcon(new ImageIcon("file.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconHelp = new ImageIcon(new ImageIcon("help.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);  //the key is Alt+F
        fileMenu.setIcon(iconFile);

        //Access Settings of the application
        JMenu SettingsMenu = new JMenu("Settings");
        SettingsMenu.setIcon(iconSettings);

        JMenuItem changeAppearance = new JMenuItem("Appearance...");
        JMenuItem Modes = new JMenuItem("Switch Modes...");
        JMenuItem Tools = new JMenuItem("Tools...");
        JMenuItem otherSettings = new JMenuItem("Other Settings...");

        SettingsMenu.add(changeAppearance);
        SettingsMenu.add(Modes);
        SettingsMenu.add(Tools);
        SettingsMenu.add(otherSettings);

        //Help menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_F12);    //The key is Alt+F12
        helpMenu.setIcon(iconHelp);

        //New empty file
        JMenuItem Fnew = new JMenuItem(new MenuItemAction("New File", iconNew, KeyEvent.VK_N));
        Fnew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RegUserNewBlank NewByRegUser = new RegUserNewBlank();
                NewByRegUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                NewByRegUser.setExtendedState(JFrame.MAXIMIZED_VERT);
                NewByRegUser.pack();
                NewByRegUser.setLocationRelativeTo(null);
                NewByRegUser.setResizable(true);
                NewByRegUser.setVisible(true);
                dispose();
            }
        });

        //Open a recent file
        JMenuItem Flread = new JMenuItem(new MenuItemAction("Open Recent", iconOpenRecent, KeyEvent.VK_S));
        Flread.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

        //Exit the application
        JMenuItem Fexit = new JMenuItem("Exit", iconExit);
        Fexit.setMnemonic(KeyEvent.VK_E); //the key is Alt+F+E
        Fexit.setToolTipText("Exit application");
        //by pressing the Ctrl+W combination, we launch the menu item (we close the application in this case)
        Fexit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        Fexit.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        fileMenu.add(Fnew);
        fileMenu.add(Flread);
        fileMenu.addSeparator();
        fileMenu.add(SettingsMenu);
        fileMenu.addSeparator();
        fileMenu.add(Fexit);

        menuBar.add(fileMenu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    //TAKES A LABEL, THE ICON, AND THE KEYBOARD KEY (ACTION OBJECT)
    private class MenuItemAction extends AbstractAction {

        public MenuItemAction(String label, ImageIcon icon, Integer mnemonic){

            super(label);
            putValue(SMALL_ICON, icon);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent event){

            System.out.println(event.getActionCommand());
        }
    }
}
