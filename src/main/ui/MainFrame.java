package ui;

import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// creates the frame for the GUI
public class MainFrame extends JFrame {

    // EFFECTS: sets up the frame for the GUI, as well as the icon to replace the Java logo
    public MainFrame() {
        this.setTitle("Job Applications Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowOpened(e);
                new ConsolePrinter(EventLog.getInstance()).printLog();
            }
        });
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1000, 500);
        this.getContentPane().setBackground(new Color(0, 0, 0)); // sets background color
        this.setVisible(true);

        ImageIcon icon = new ImageIcon("src/main/ui/TopIcon.jpg");
        this.setIconImage(icon.getImage());
    }
}
