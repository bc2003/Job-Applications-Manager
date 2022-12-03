package ui;

public class Main {
    public static void main(String[] args) {
        try {
            new NewWindow();
            new GraphicalUserInterface();
        } catch (Exception e) {
            System.out.println("Unable to run application: file not found :(");
        }
    }
}
