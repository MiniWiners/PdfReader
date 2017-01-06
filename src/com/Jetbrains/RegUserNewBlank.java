package com.Jetbrains;

import com.lowagie.text.*;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.*;

import static java.io.FileDescriptor.out;

public class RegUserNewBlank extends JFrame {

    int inch = Toolkit.getDefaultToolkit().getScreenResolution();
    float pixelToPoint = (float) 72 / (float) inch;

    private JTextPane newBlanktoEdit;
    private JMenuBar menuBar;
    private GridBagConstraints gridbagc;
    private StyledDocument doc;

    public RegUserNewBlank(){

        super("New file");

        setLayout(new GridBagLayout());

        gridbagc = new GridBagConstraints();
        gridbagc.insets = new Insets(5, 5, 5, 5);

        //PANEL FOR THE TEXTPANE
        JPanel panelforText = new JPanel();
        panelforText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(8, 8, 8, 8, Color.LIGHT_GRAY),
                BorderFactory.createLoweredBevelBorder()));

        newBlanktoEdit = new JTextPane();
        newBlanktoEdit.setEditable(true);
        newBlanktoEdit.setFont(new Font("Arial", Font.PLAIN, 26));
        doc = newBlanktoEdit.getStyledDocument();

        JScrollPane toEditScrollPane = new JScrollPane(newBlanktoEdit);
        toEditScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        toEditScrollPane.setMinimumSize(new Dimension(1300, 800));
        toEditScrollPane.setPreferredSize(new Dimension(1300, 800));

        panelforText.add(toEditScrollPane);
        add(panelforText, gridbagc);

        //CURRENT FRAME MENU BAR
        menuBar = new JMenuBar();

        ImageIcon iconFile= new ImageIcon(new ImageIcon("file.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconSaveAs= new ImageIcon(new ImageIcon("SaveAsPDF.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconReadMode= new ImageIcon(new ImageIcon("ReadMode.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconSwitchModes= new ImageIcon(new ImageIcon("SwitchModes.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconHelp= new ImageIcon(new ImageIcon("help.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconLoadFile= new ImageIcon(new ImageIcon("LoadFile.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconExit= new ImageIcon(new ImageIcon("exit.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconEditMode= new ImageIcon(new ImageIcon("editMode.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);   //The key is Alt+F
        fileMenu.setIcon(iconFile);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_F12);  //The Key is Alt+F12
        helpMenu.setIcon(iconHelp);

        JMenu SwitchModes = new JMenu("Switch modes");
        SwitchModes.setIcon(iconSwitchModes);

        JMenuItem readMode = new JMenuItem("Access reading mode");
        JMenuItem editMode = new JMenuItem("Access editing mode");
        readMode.setIcon(iconReadMode);
        editMode.setIcon(iconEditMode);

        SwitchModes.add(readMode);
        SwitchModes.add(editMode);

        JMenuItem saveAs = new JMenuItem(new MenuItemAction("Save as PDF", iconSaveAs, KeyEvent.VK_N));
        JMenuItem loadFile = new JMenuItem(new MenuItemAction("Load pdf to read", iconLoadFile, KeyEvent.VK_O));

        saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String title = JOptionPane.showInputDialog(null, "Please name the file...");
                try{
                    paintToPDF(newBlanktoEdit, title);
                }catch (Exception exc){
                    exc.printStackTrace();
                }
            }
        });

        loadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final JFileChooser pdfChoice = new JFileChooser();
                pdfChoice.setFileFilter(new FileNameExtensionFilter("pdf", "PDF File"));
                pdfChoice.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int returnVal = pdfChoice.showDialog(null, "Please Select a PDF file for reading");

                if(returnVal == JFileChooser.APPROVE_OPTION){
                    File filenm = pdfChoice.getSelectedFile();
                    try{
                        BufferedReader input = new BufferedReader(new FileReader(filenm));
                        newBlanktoEdit.setText("");
                        String line = input.readLine();
                        while(line != null){
                            doc.insertString(0, line, null);
                        }
                        input.close();
                    }catch (Exception exc){
                        exc.printStackTrace();
                    }
                }
            }
        });

        //exit application
        JMenuItem exit = new JMenuItem("Exit", iconExit);
        exit.setMnemonic(KeyEvent.VK_E); //key is Alt+F+E
        exit.setToolTipText("Exit Application");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        exit.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        fileMenu.add(saveAs);
        fileMenu.add(loadFile);
        fileMenu.addSeparator();
        fileMenu.add(SwitchModes);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        menuBar.add(fileMenu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

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

    private void paintToPDF(JTextPane newPane, String title) throws Exception{

        newPane.setBounds(0, 0, (int) convertToPixels(612 - 58), (int) convertToPixels(792 - 60));

        Document doc = new Document();
        FileOutputStream out = new FileOutputStream(title + ".pdf");
        PdfWriter writer = PdfWriter.getInstance(doc, out);

        doc.setPageSize(new com.lowagie.text.Rectangle(612, 792));
        doc.open();
        PdfContentByte cb = writer.getDirectContent();
        cb.saveState();
        cb.concatCTM(1, 0, 0, 1, 0, 0);

        DefaultFontMapper mapper = new DefaultFontMapper();
        mapper.insertDirectory("c:/windows/fonts");

        Graphics2D g = cb.createGraphics(612, 792, mapper, true, .92f);

        AffineTransform at = new AffineTransform();
        at.translate(convertToPixels(20), convertToPixels(20));
        at.scale(pixelToPoint, pixelToPoint);

        g.transform(at);
        g.setColor(Color.WHITE);
        g.fill(newPane.getBounds());

        Rectangle alloc = getVisivleEditorRect(newPane);
        newPane.getUI().getRootView(newPane).paint(g, alloc);

        g.setColor(Color.BLACK);
        g.draw(newPane.getBounds());

        g.dispose();
        cb.restoreState();
        doc.close();
        out.flush();
        out.close();
    }

    private float convertToPoints(int pixels){

        return (float) (pixels * pixelToPoint);
    }

    private float convertToPixels(int points){

        return (float) (points / pixelToPoint);
    }

    protected Rectangle getVisivleEditorRect(JEditorPane newPane){

        Rectangle alloc = newPane.getBounds();
        if((alloc.width > 0) && (alloc.height > 0)){
            alloc.x = alloc.y = 0;
            Insets insets = newPane.getInsets();
            alloc.x += insets.left;
            alloc.y += insets.top;
            alloc.width -= insets.left + insets.right;
            alloc.height -= insets.top + insets.bottom;
            return alloc;
        }
        return null;
    }
}
