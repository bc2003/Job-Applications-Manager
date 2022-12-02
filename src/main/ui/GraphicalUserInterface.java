package ui;

import model.CurrentList;
import model.JobApplication;
import model.EventCaller;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.event.*;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ImageIcon;

// represents main window where we do our operations on the job applications manager GUI
public class GraphicalUserInterface implements ActionListener {
    private JList<JobApplication> list = new JList<>();
    private DefaultListModel<JobApplication> model = new DefaultListModel<>();
    private JScrollPane pane = new JScrollPane(list);

    private MainPanel mainPanel;
    private DoneWindow doneWindow;

    private MainButtons addButton;
    private MainButtons removeButton;
    private MainButtons updateButton;
    private MainButtons saveButton;
    private MainButtons loadButton;

    JTextField titleTextField;
    JTextField companyTextField;
    JComboBox appStatDropBox;

    private CurrentList cl;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/myJobs.json";

    private EventCaller eventCaller;

    // EFFECTS: creates the GUI where we can conduct our operations
    public GraphicalUserInterface() {
        mainPanel = new MainPanel();
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        updateButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

        list.setModel(model);
        pane.setBounds(500,0,500,500);

        WorkPanel workPanel = new WorkPanel();
        mainPanel.add(workPanel);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        MainFrame myFrame = new MainFrame();
        myFrame.add(mainPanel);
        myFrame.add(pane);
    }

    // class which creates the left side panel where the actual work occurs
    public class MainPanel extends JPanel {

        // EFFECTS: sets up the left side panel of the GUI
        public MainPanel() {
            this.setBackground(new Color(200, 200, 200));
            this.setBounds(0,0, 500, 500);
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 17));
            this.setBorder(BorderFactory.createRaisedBevelBorder());

            addButton = new MainButtons();
            addButton.setText("Add Job");
            addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            removeButton = new MainButtons();
            removeButton.setText("Remove Job");
            removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            updateButton = new MainButtons();
            updateButton.setText("Update Job");
            saveButton = new MainButtons();
            saveButton.setText("Save to Folder");
            saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            loadButton = new MainButtons();
            loadButton.setText("Load from Folder");
            loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(addButton);
            this.add(removeButton);
            this.add(updateButton);
            this.add(saveButton);
            this.add(loadButton);
        }
    }

    // class which creates a panel where you can fill out job information
    public class WorkPanel extends JPanel {
        String[] applicationStatus = { "0: Interested", "1: Applied", "2: Interviewed",
                "3: Received Offer", "4: Turned Down Offer", "5: Accepted Offer", "6: Rejected" };

        // EFFECTS: sets up the work panel where you can input job information and select an application status
        public WorkPanel() {
            this.setBackground(new Color(50, 50, 50));
            this.setPreferredSize(new Dimension(400, 200));
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
            this.setBorder(BorderFactory.createLoweredBevelBorder());

            VoidLabel title = new VoidLabel();
            title.setText("Job Position: ");
            titleTextField = textField();

            VoidLabel company = new VoidLabel();
            company.setText("Company: ");
            companyTextField = textField();

            appStatDropBox = new JComboBox(applicationStatus);

            VoidLabel instructionLabel = addLabel();
            VoidLabel instructionLabel2 = removeLabel();
            VoidLabel instructionLabel3 = updateLabel();

            this.add(title);
            this.add(titleTextField);
            this.add(company);
            this.add(companyTextField);
            this.add(appStatDropBox);
            this.add(instructionLabel);
            this.add(instructionLabel2);
            this.add(instructionLabel3);
        }

        // returns the text fields where the user can put in job information
        public JTextField textField() {
            JTextField textField = new JTextField();
            textField.setPreferredSize(new Dimension(150, 40));
            return textField;
        }

        // creates label which gives instruction for how to add a job
        public VoidLabel addLabel() {
            VoidLabel addLabel = new VoidLabel();
            addLabel.setText("Press 'Add Job' to Add Job");
            return addLabel;
        }

        // creates label which gives instruction for how to remove a job
        public VoidLabel removeLabel() {
            VoidLabel removeLabel = new VoidLabel();
            removeLabel.setText("Press 'Remove Job' to Remove Job");
            return removeLabel;
        }

        // creates label which gives instruction for how to update a job
        public VoidLabel updateLabel() {
            VoidLabel updateLabel = new VoidLabel();
            updateLabel.setText("Press 'Update Job' to Update Job");
            return updateLabel;
        }
    }

    // sets up my preferred formatting for the buttons for main operations
    public class MainButtons extends JButton {

        // EFFECTS: constructs a button with Montserrat text and without the weird box thing around it
        public MainButtons() {
            this.setBounds(200, 0, 400, 100);
            this.setFont(new Font("Montserrat", Font.BOLD, 20));
            this.setFocusable(false);
            this.setBackground(new Color(200, 255, 255));
            this.setBorder(BorderFactory.createEtchedBorder());
        }
    }

    // simply sets up the colour of the text
    public class VoidLabel extends JLabel {

        // EFFECTS: sets up the colour of the text for the work panel
        public VoidLabel() {
            this.setForeground(new Color(255, 255, 255));
        }
    }

    // MODIFIES: this
    // EFFECTS: performs the actions designated by the main buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addButton();
        } else if (e.getSource() == removeButton) {
            removeButton();
        } else if (e.getSource() == updateButton) {
            updateButton();
        } else if (e.getSource() == saveButton) {
            saveButton();
        } else if (e.getSource() == loadButton) {
            loadButton();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a job to the job application manager given that all fields are filled out
    public void addButton() {
        String title = titleTextField.getText();
        String company = companyTextField.getText();
        Integer appStat = appStatDropBox.getSelectedIndex();
        if (!title.isEmpty() & !company.isEmpty()) {
            JobApplication job = new JobApplication(title, company, appStat);
            model.addElement(job);
            eventCaller = new EventCaller(job);
            eventCaller.addEvent();
        } else {
            System.out.println("Finish filling out the fields!");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a job from the job application manager given that all fields are filled out
    public void removeButton() {
        String title = titleTextField.getText();
        String company = companyTextField.getText();
        Integer appStat = appStatDropBox.getSelectedIndex();
        if (!title.isEmpty() & !company.isEmpty()) {
            int ashHadu = model.getSize();
            for (int i = 0; i < ashHadu; i++) {
                JobApplication job = model.getElementAt(i);
                if (title.equals(job.getTitle()) & company.equals(job.getCompany()) & appStat == job.getStatus()) {
                    model.removeElement(job);
                    eventCaller = new EventCaller(job);
                    eventCaller.removeEvent();
                }
            }
        } else {
            System.out.println("Finish filling out the fields!");
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the status for an already existing job
    public void updateButton() {
        String title = titleTextField.getText();
        String company = companyTextField.getText();
        Integer newAppStat = appStatDropBox.getSelectedIndex();
        if (!title.isEmpty() & !company.isEmpty()) {
            int ashHadu = model.getSize();
            boolean truth = false;
            for (int i = 0; i < ashHadu; i++) {
                JobApplication job = model.getElementAt(i);
                if (title.equals(job.getTitle()) & company.equals(job.getCompany())) {
                    job = new JobApplication(title, company, newAppStat);
                    model.removeElementAt(i);
                    model.add(i, job);
                    truth = true;
                    eventCaller = new EventCaller(job);
                    eventCaller.updateEvent();
                }
            }
            if (!truth) {
                System.out.println("Cannot find job!");
            }
        } else {
            System.out.println("Finish filling out the fields!");
        }
    }

    // MODIFIES: this
    // EFFECTS: saves jobs in the job application manager to JSON file
    public void saveButton() {
        try {
            cl = new CurrentList("Your List");
            int ashHadu = model.getSize();
            for (int i = 0; i < ashHadu; i++) {
                JobApplication job = model.getElementAt(i);
                cl.addJob(job);
            }
            jsonWriter.open();
            jsonWriter.write(cl);
            jsonWriter.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads jobs from JSON file to the job applications manager
    public void loadButton() {
        try {
            cl = jsonReader.read();
            cl.toString();
            for (JobApplication job : cl.getJobs()) {
                model.addElement(job);
            }
        } catch (IOException ioException) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
