package ui.gui;

import exceptions.InvalidInputException;
import model.LogbookEntry;
import model.LogbookRecord;
import persistence.JsonReader;
import persistence.JsonWriter;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

//Represents the add entry Panel
public class AddEntryGUI extends JFrame {
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
    private JLabel dayOrNight;
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
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/logbookRecord.json";

    //constructor for add entry JFrame
    public AddEntryGUI() throws InvalidInputException {
        super("Create a new log entry");
        setLayout(new GridBagLayout());
        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUpAddPanel();
        add(addPanel);
        setUpListener();
    }

    //constructor for add entry JPanel
    private void setUpAddPanel() throws InvalidInputException {
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

    //MODIFIES: this
    //EFFECT: Create Jbutton for different operations
    private void setUpButtons() {
        addEntry = new JButton("Add another entry");
        confirmAdd = new JButton("Confirm add this entry");
        returnToMain = new JButton("Return to main menu");
    }

    //MODIFIES: this
    //EFFECT: create Labels for the text fields to take user input
    private void setUpFieldLabels() {
        entryNumber = new JLabel("Enter entry number: ");
        month = new JLabel("Enter month: ");
        day = new JLabel("Enter day: ");
        airplaneModel = new JLabel("Aircraft model: ");
        airplaneName = new JLabel("Name for the airplane: ");
        pic = new JLabel("Name of the Pilot: ");
        flightTime = new JLabel("Flight time: ");
        dayOrNight = new JLabel("D for day and N for night: ");
        departure = new JLabel("Departure airport:");
        arrival = new JLabel("Destination airport: ");
        note = new JLabel("Remark: ");
    }


    //Constructor
    //EFFECT: create new JTextFields to take user input
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


    //MODIFIES: this
    //EFFECT: create layout for labels of entry number, date, airplane information
    private void displayLabels() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 8, 8, 8);
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

        displayFlightInfoLabels();

    }

    //MODIFIES: this
    //EFFECT: create layout for labels of pilot name, flight time, flight type, route info and remark
    private void displayFlightInfoLabels() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 8, 8, 8);
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 5;
        add(pic, gc);

        gc.gridx = 0;
        gc.gridy = 6;
        add(flightTime, gc);

        gc.gridx = 0;
        gc.gridy = 7;
        add(dayOrNight, gc);

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


    //MODIFIES: this
    //EFFECT: create layout for text fields of date, flight time, flight type, route
    private void displayTextFields() throws InvalidInputException {
        GridBagConstraints gc = new GridBagConstraints();
        loadLogbookEntries();
        List<LogbookEntry> allEntry = record.displayAllEntry();
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.gridx = 1;
        gc.gridy = 0;
        add(new JLabel(Integer.toString(allEntry.size() + 1)), gc);
        gc.gridx = 1;
        gc.gridy = 1;
        add(monthText, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        add(dayText, gc);

        displayAirplaneInfoFields();

        gc.gridx = 1;
        gc.gridy = 6;
        add(flightTimeText, gc);
        gc.gridx = 1;
        gc.gridy = 7;
        add(dayOrnightText, gc);
        displayTextFieldsForRoute();
    }

    //MODIFIES: this
    //EFFECT: render the text fields to take aircraft info
    private void displayAirplaneInfoFields() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.gridx = 1;
        gc.gridy = 3;
        add(airplaneModelText, gc);
        gc.gridx = 1;
        gc.gridy = 4;
        add(airplaneNameText, gc);
        gc.gridx = 1;
        gc.gridy = 5;
        add(picText, gc);
    }

    //MODIFIES: this
    //EFFECT: render textFields for flight route information, and construct option buttons
    //reference: https://www.youtube.com/watch?v=g2vDARb7gx8&t=230s
    private void displayTextFieldsForRoute() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(5, 5, 5, 5);

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

        gc.gridx = 1;
        gc.gridy = 12;
        add(addEntry, gc);

        gc.gridx = 1;
        gc.gridy = 13;
        add(returnToMain, gc);
        gc.fill = GridBagConstraints.HORIZONTAL;
    }


    //MODIFIES: this
    //EFFECT: create actionListeners for JButtons
    private void setUpListener() {
        ActionHandle listener = new ActionHandle();

        addEntry.addActionListener(listener);
        confirmAdd.addActionListener(listener);
        returnToMain.addActionListener(listener);
        entryNumberText.addActionListener(listener);
    }

    //EFFECT: read the Json file to load existing entries
    private void loadLogbookEntries() throws InvalidInputException {
        jsonReader = new JsonReader(JSON_STORE);

        try {
            record = jsonReader.read();
            System.out.println("Loaded " + record.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //Represent a class for ActionListener
    public class ActionHandle implements ActionListener {

        //Require: Action Event
        //EFFECT: respond to different Jbutton pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                loadLogbookEntries();
            } catch (InvalidInputException invalidInputException) {
                invalidInputException.printStackTrace();
            }
            if (e.getSource() == addEntry) {
                playMusic("./data/clickButton.wav");
                try {
                    openAddEntry();
                } catch (InvalidInputException invalidInputException) {
                    invalidInputException.printStackTrace();
                }

            } else if (e.getSource() == returnToMain) {
                playMusic("./data/clickButton.wav");
                displayMainMenu();
            } else if (e.getSource() == confirmAdd) {
                playMusic("./data/clickButton.wav");
                recordInputs();
            }
        }


        //MODIFIES: this
        //EFFECT: write the Json file to save new entries
        private void saveLogbook() {
            jsonWriter = new JsonWriter(JSON_STORE);
            try {
                jsonWriter.open();
                jsonWriter.write(record);
                jsonWriter.close();
                System.out.println("Saved " + record.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }

        //MODIFIES: this
        //EFFECT: input information required for a new entry, invalidEntryException is thrown for invalid entry.
        private void recordInputs() {
            entry = new LogbookEntry();
            List<LogbookEntry> allEntry = record.displayAllEntry();

            entry.setEntryNumber(allEntry.size() + 1);
            try {
                entry.setMonth(checkMonth(monthText.getText()));
                entry.setDay(checkDay(Integer.valueOf(dayText.getText())));
                entry.setAirplaneModel(airplaneModelText.getText());
                entry.setAirplaneName(airplaneNameText.getText());
                entry.setPic(picText.getText());
                entry.setFLightTime(Double.valueOf(flightTimeText.getText()));
            } catch (InvalidInputException e) {
                JOptionPane.showMessageDialog(null, "You must enter a valid date (ex:January 1)",
                        "Ooops", JOptionPane.ERROR_MESSAGE);
            }
            inputForRouteInfo();
        }


        //MODIFIES: this
        //EFFECT: input information for route and type of flight
        private void inputForRouteInfo() {
            try {
                entry.setDayOrNight(checkDayOrNight(dayOrnightText.getText()));
            } catch (InvalidDayOrNightException e) {
                JOptionPane.showMessageDialog(null, "You need to specify day or night flight",
                        "Ooops", JOptionPane.ERROR_MESSAGE);
                try {
                    entry.setDepartureAirport(departureText.getText());
                    entry.setArrivalAirport(arrivalText.getText());
                    entry.setRemark(noteText.getText());
                    record.addAnEntry(entry);
                    saveLogbook();
                } catch (InvalidInputException a) {
                    JOptionPane.showMessageDialog(null, "You need to enter 4 digit airport code",
                            "Ooops", JOptionPane.ERROR_MESSAGE);

                }
            }
        }


        //EFFECT: create the mainMenu Frame
        private void displayMainMenu() {
            new MainMenuGUI();
            dispose();
        }

        //EFFECT: create a new add entry frame
        private void openAddEntry() throws InvalidInputException {
            AddEntryGUI addEntryGUI = new AddEntryGUI();
            addEntryGUI.setVisible(true);
            dispose();
        }

        //REQUIRE: string
        //MODIFIES:this
        //EFFECT: check if the string is a valid month, if it's not throw InvalidInputException
        private String checkMonth(String month) throws InvalidInputException {
            if (!month.equals("January") & !month.equals("February") & !month.equals("March")
                    & !month.equals("April") & !month.equals("May") & !month.equals("June") & !month.equals("July")
                    & !month.equals("August") & !month.equals("September") & !month.equals("October")
                    & !month.equals("November") & !month.equals("December")) {

                throw new InvalidInputException();
            }
            return month;
        }

        //REQUIRE: int
        //MODIFIES:this
        //EFFECT: check if the int is a valid day, if it's not throw InvalidInputException
        private int checkDay(int day) throws InvalidInputException {
            if (day < 1 || day > 31) {

                throw new InvalidInputException();
            }
            return day;
        }

        //REQUIRE: int
        //MODIFIES:this
        //EFFECT: check if the int is a valid entry number, if it's not throw InvalidInputException
        private int checkEntryNumber(int num) throws InvalidInputException {
            if (num < 0) {

                throw new InvalidInputException();
            }
            return num;
        }

        //REQUIRE: string
        //MODIFIES:this
        //EFFECT: check if the string is a valid day or night selection, if it's not throw InvalidInputException
        private String checkDayOrNight(String dayOrNight) throws InvalidDayOrNightException {
            if (dayOrNight.equals("D") | dayOrNight.equals("d") | dayOrNight.equals("day") | dayOrNight.equals("Day")) {
                return "Day";
            } else if (dayOrNight.equals("Night") | dayOrNight.equals("N") | dayOrNight.equals("night")
                    | dayOrNight.equals("n")) {
                return "Night";
            }
            throw new InvalidDayOrNightException();
        }


    }

    //REQUIRE: String
    //MODIFIES: this
    //EFFECT: create sound effect for the button clicked
    //reference: https://www.youtube.com/watch?v=3q4f6I5zi2w
    public static void playMusic(String filePath) {
        InputStream music;

        try {
            music = new FileInputStream(new File(filePath));
            AudioStream audio = new AudioStream(music);
            AudioPlayer.player.start(audio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");

        }
    }
}

