package ui;

import model.JobApplication;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.event.*;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ImageIcon;

// represents main window where we do our operations in the job applications manager
public class GraphicalUserInterface implements ActionListener, ListSelectionListener {
    private JList<JobApplication> list = new JList<>();
    private DefaultListModel<JobApplication> model = new DefaultListModel<>();
    private JScrollPane pane = new JScrollPane(list);

    private MainPanel mainPanel;

    private MainButtons addButton;
    private MainButtons removeButton;
    private MainButtons updateButton;
    private MainButtons saveButton;
    private MainButtons loadButton;

    // EFFECTS: creates main window where we conduct our operations
    public GraphicalUserInterface() {
        /*
        ImageIcon img = new ImageIcon("src/main/ui/Loading.jpg");
        JLabel pic = new JLabel(img);
        pic.setBounds(500, 300, 500, 200);
        */

        mainPanel = new MainPanel();
        addButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

        ListPanel listPanel = new ListPanel();
        list.setModel(model);
        model.addElement(new JobApplication("Cashier", "Costco", 0));
        model.addElement(new JobApplication("Cashier", "Superstore", 1));
        pane.setBounds(500,0,500,300);

        WorkPanel workPanel = new WorkPanel();
        mainPanel.add(workPanel);

        MainFrame myFrame = new MainFrame();
        myFrame.add(mainPanel);
        myFrame.add(pane);
    }

    public class MainPanel extends JPanel {
        MainPanel() {
            BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
            this.setBackground(new Color(200, 200, 200));
            this.setBounds(0,0, 500, 500);
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 20));
            this.setBorder(BorderFactory.createRaisedBevelBorder());

            addButton = new MainButtons();
            addButton.setText("Add Job");
            // addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            removeButton = new MainButtons();
            removeButton.setText("Remove Job");
            saveButton = new MainButtons();
            saveButton.setText("Save to Folder");
            // saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            loadButton = new MainButtons();
            loadButton.setText("Load from Folder");
            // loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(addButton);
            this.add(removeButton);
            this.add(saveButton);
            this.add(loadButton);
        }
    }

    // might not need this at all XD
    public class ListPanel extends JPanel {
        ListPanel() {
            this.setBounds(500, 0, 500, 300);
            this.setLayout(null);
        }
    }

    // maybe get rid of this too
    public class LogoPanel extends JPanel {
        LogoPanel() {
            ImageIcon img = new ImageIcon("src/main/ui/Loading.jpg");
            /*
            BufferedImage myPicture = ImageIO.read(new File("src/main/ui/Loading.jpg"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            this.add(picLabel);
            */
            this.setBackground(new Color(255, 255, 255));
            this.setBounds(500, 300, 500, 200);
            this.setLayout(new FlowLayout());
        }
    }

    public class WorkPanel extends JPanel {
        WorkPanel() {
            //this.setBackground(new Color(50, 50, 50));
            this.setPreferredSize(new Dimension(400, 200));
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
            this.setBorder(BorderFactory.createRaisedBevelBorder());

            VoidLabel title = new VoidLabel();
            title.setText("Job Position: ");
            JTextField titleTextField = new JTextField();
            titleTextField.setPreferredSize(new Dimension(150, 40));

            VoidLabel company = new VoidLabel();
            company.setText("Company: ");
            JTextField companyTextField = new JTextField();
            companyTextField.setPreferredSize(new Dimension(150, 40));

            String[] applicationStatus = { "0: Interested", "1: Applied", "2: Interviewed",
                    "3: Received Offer", "4: Turned Down Offer", "5: Accepted Offer", "6: Rejected" };
            JComboBox appStatDropBox = new JComboBox(applicationStatus);

            this.add(title);
            this.add(titleTextField);
            this.add(company);
            this.add(companyTextField);
            this.add(appStatDropBox);
        }
    }

    public class MainButtons extends JButton {
        MainButtons() {
            this.setBounds(200, 0, 400, 100);
            this.setFont(new Font("Montserrat", Font.BOLD, 20));
            this.setFocusable(false);
            this.setBackground(new Color(200, 255, 255));
            this.setBorder(BorderFactory.createEtchedBorder());
        }
    }

    // maybe get rid of this XD
    public class VoidLabel extends JLabel {
        VoidLabel() {
            this.setForeground(new Color(255, 255, 255)); // sets text color
            //this.setHorizontalAlignment(JLabel.CENTER);
            //this.setVerticalAlignment(JLabel.LEFT);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            System.out.println("hei gui");
        } else if (e.getSource() == saveButton) {
            System.out.println("Wo shi");
        }
    }

    // MUST OVERRIDE THIS ONE
    public void valueChanged(ListSelectionEvent e) {
        // stub
    }

    public static void main(String[] args) {
        GraphicalUserInterface gui = new GraphicalUserInterface();
    }
}
