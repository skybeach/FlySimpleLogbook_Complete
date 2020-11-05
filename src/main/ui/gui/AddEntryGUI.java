package ui.gui;

import exceptions.InvalidDayException;
import exceptions.InvalidInputException;
import exceptions.InvalidMonthException;
import model.LogbookEntry;
import model.LogbookRecord;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class AddEntryGUI extends JFrame {
    private JFrame addFrame;
    private int frameWidth = 400;
    private int frameHeight = 800;
    private JPanel addPanel;
    private JButton addEntry;
    private JButton confirmAdd;
    private JButton returnToMain;
    private JLabel entryNumber;
    private JLabel month;
    private JLabel day;
    private JLabel airplaneModel;
    private JLabel airplaneName;
    private JLabel pic;
    private JLabel flightTime;
    private JLabel dayOrnight;
    private JLabel departure;
    private JLabel arrival;
    private JLabel note;
    private LogbookEntry entry;
    private LogbookRecord record;

    JTextField entryNumberText;
    JTextField monthText;
    JTextField dayText;
    JTextField airplaneModelText;
    JTextField airplaneNameText;
    JTextField picText;
    JTextField flightTimeText;
    JTextField dayOrnightText;
    JTextField departureText;
    JTextField arrivalText;
    JTextField noteText;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/logbookRecord.json";



    public AddEntryGUI() {
        super("Create a new log entry");
        setLayout(new GridBagLayout());
        setSize(frameWidth,frameHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUpAddPanel();
        add(addPanel);
        setUpListener();
    }


    private void setUpAddPanel() {
        addPanel = new JPanel();
        addPanel.setLayout(new GridBagLayout());
        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);
        setUpButtons();
        setUpFieldLabels();
        setUpFields();
        displayLabels();
        displayTextFields();
    }

    private void setUpButtons() {
        addEntry = new JButton("Add an Entry");
        confirmAdd = new JButton("Confirm add this entry");
        returnToMain = new JButton("Return to main menu");
    }

    private void setUpFieldLabels() {
        entryNumber = new JLabel("Enter entry number: ");
        month = new JLabel("Enter month: ");
        day = new JLabel("Enter day: ");
        airplaneModel = new JLabel("Aircraft model: ");
        airplaneName = new JLabel("Name for the airplane: ");
        pic = new JLabel("Name of the Pilot: ");
        flightTime = new JLabel("Flight time: ");
        dayOrnight = new JLabel("D for day and N for night: ");
        departure = new JLabel("Departure airport:");
        arrival = new JLabel("Destination airport: ");
        note = new JLabel("Remark: ");
    }

    private void setUpFields() {
        entryNumberText = new JTextField(10);
        monthText = new JTextField(10);
        dayText = new JTextField(10);
        airplaneModelText = new JTextField(10);
        airplaneNameText = new JTextField(10);
        picText = new JTextField(10);
        flightTimeText = new JTextField(10);
        dayOrnightText = new JTextField(10);
        departureText = new JTextField(10);
        arrivalText = new JTextField(10);
        noteText = new JTextField(10);
    }

    private void displayLabels() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5,8,8,8);
        gc.anchor = GridBagConstraints.LINE_END;

        gc.gridx = 0;
        gc.gridy = 0;
        add(entryNumber, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(month, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        add(day, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        add(airplaneModel, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        add(airplaneName, gc);
        gc.gridx = 0;
        gc.gridy = 5;
        add(pic, gc);
        gc.gridx = 0;
        gc.gridy = 6;
        add(flightTime, gc);
        gc.gridx = 0;
        gc.gridy = 7;
        add(dayOrnight, gc);
        gc.gridx = 0;
        gc.gridy = 8;
        add(departure, gc);
        gc.gridx = 0;
        gc.gridy = 9;
        add(arrival, gc);
        gc.gridx = 0;
        gc.gridy = 10;
        add(note, gc);
    }

    private void displayTextFields() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(5,5,5,5);
        gc.gridx = 1;
        gc.gridy = 0;
        add(entryNumberText, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        add(monthText, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        add(dayText, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        add(airplaneModelText, gc);
        gc.gridx = 1;
        gc.gridy = 4;
        add(airplaneNameText, gc);
        gc.gridx = 1;
        gc.gridy = 5;
        add(picText, gc);
        gc.gridx = 1;
        gc.gridy = 6;
        add(flightTimeText, gc);
        gc.gridx = 1;
        gc.gridy = 7;
        add(dayOrnightText, gc);
        gc.gridx = 1;
        gc.gridy = 8;
        add(departureText, gc);
        gc.gridx = 1;
        gc.gridy = 9;
        add(arrivalText, gc);
        gc.gridx = 1;
        gc.gridy = 10;
        add(noteText, gc);
        gc.gridx = 1;
        gc.gridy = 11;
        add(confirmAdd, gc);
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 1;
        gc.gridy = 12;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(addEntry, gc);
        gc.gridx = 1;
        gc.gridy = 13;
        add(returnToMain, gc);
        gc.fill = GridBagConstraints.HORIZONTAL;

    }

    private void setUpListener() {
        ActionHandle listener = new ActionHandle();

        addEntry.addActionListener(listener);
        confirmAdd.addActionListener(listener);
        returnToMain.addActionListener(listener);
        entryNumberText.addActionListener(listener);
    }

    private class ActionHandle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addEntry) {
                openAddEntry();
            } else if (e.getSource() == returnToMain) {
                displayMainMenu();
            } else if (e.getSource() == confirmAdd) {
                recordInputs();
            }
        }

        private void saveLogbook() {
            jsonWriter = new JsonWriter(JSON_STORE);

            try {
                jsonWriter.open();
                jsonWriter.write(record);
                jsonWriter.close();
                System.out.println("Saved " + record.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException | InvalidMonthException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }

        private void recordInputs() {
            entry = new LogbookEntry();
            record = new LogbookRecord("record");

            try {
                entry.setEntryNumber(checkEntryNumber(Integer.valueOf(entryNumberText.getText())));
                entry.setMonth(checkMonth(monthText.getText()));
                entry.setDay(checkDay(Integer.valueOf(dayText.getText())));
                entry.setAirplaneModel(airplaneModelText.getText());
                entry.setAirplaneName(airplaneNameText.getText());
                entry.setPic(picText.getText());
                entry.setFLightTime(Integer.valueOf(flightTimeText.getText()));
                entry.setDayOrnight(dayOrnightText.getText());
                entry.setDepartureAirport(departureText.getText());
                entry.setArrivalAirport(arrivalText.getText());
                entry.setRemark(noteText.getText());
                record.addAnEntry(entry);
                saveLogbook();
            } catch (InvalidInputException e) {
                JOptionPane.showConfirmDialog(null, "You must enter a valid Date and/or entry "
                                + "number.",
                        "Ooops", JOptionPane.DEFAULT_OPTION);
            }
        }

        private void displayMainMenu() {
            MainMenuGUI main = new MainMenuGUI();
            main.setVisible(true);
            dispose();
        }

        private void openAddEntry() {
            AddEntryGUI addEntryGUI = new AddEntryGUI();
            addEntryGUI.setVisible(true);
            dispose();
        }

        private String checkMonth(String month) throws InvalidInputException {
            if (!month.equals("January") & !month.equals("February") & !month.equals("March")
                    & !month.equals("April") & !month.equals("May") & !month.equals("June") & !month.equals("July")
                    & !month.equals("August") & !month.equals("September") & !month.equals("October")
                    & !month.equals("November") & !month.equals("December")) {

                throw new InvalidMonthException();
            }
            return month;
        }

        private int checkDay(int day) throws InvalidInputException {
            if (day < 1 || day > 31) {

                throw new InvalidDayException();
            }
            return day;
        }

        private int checkEntryNumber(int num) throws InvalidInputException {
            if (num < 0) {

                throw new InvalidDayException();
            }
            return num;
        }




    }


}