package ui;

import javax.swing.*;
import java.awt.*;

// creates the splash screen, will probably later use for loading and saving too
public class DoneWindow {

    // creates the actual splash screen which lasts for 5000 milliseconds
    public DoneWindow() {
        JWindow window = new JWindow();
        window.getContentPane().add(
                new JLabel("", new ImageIcon("src/main/ui/Loading.jpg"), SwingConstants.CENTER));
        window.setBounds(50, 25, 600, 450);
        window.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
        window.dispose();
    }

}
