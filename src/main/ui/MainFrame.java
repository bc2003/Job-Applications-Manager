package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    MainFrame() {
        this.setTitle("Job Applications Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1000, 500);
        this.getContentPane().setBackground(new Color(0, 0, 0)); // sets background color
        this.setVisible(true);

        ImageIcon icon = new ImageIcon("src/main/ui/TopIcon.jpg");
        this.setIconImage(icon.getImage());
    }
}
