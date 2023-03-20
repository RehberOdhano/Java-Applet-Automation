
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Calculator extends JApplet {
    JLabel labelOne;
    JLabel labelTwo;
    JLabel labelThree;
    static JTextField numOne;
    static JTextField numTwo;
    static JTextField result;
    JButton addBtn;
    CheckboxGroup radioBtns = null;
    ButtonGroup radioButtons = null;

    // initialize the applet
    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    displayCalculator();
                }
            });
        } catch (Exception e) {
            System.out.println("ERROR: something went wrong!\n" + e.getMessage());
        }
    }

    // initialize the GUI
    private void displayCalculator() {
        setName("Calculator");
        setLayout(null);

        labelOne = new JLabel("Enter a number");
        labelOne.setBounds(50, 15, 100, 20);
        numOne = new JTextField();
        numOne.setName("numOne");
        numOne.setBounds(50, 40, 100, 20);

        labelTwo = new JLabel("Enter a number");
        labelTwo.setBounds(50, 70, 100, 20);
        numTwo = new JTextField();
        numTwo.setName("numTwo");
        numTwo.setBounds(50, 100, 100, 20);

        addBtn = new JButton("Add");
        addBtn.setBounds(50, 130, 100, 20);
        addBtn.setName("addBtn");

        labelThree = new JLabel("Result");
        labelThree.setBounds(50, 160, 100, 20);
        result = new JTextField();
        result.setBounds(50, 190, 100, 20);
        result.setEditable(false);
        result.setName("result");

        // dropdown menu
        String[] numbers = {"1.1", "11", "6.0", "23", "10"};
        JComboBox randomNumbers = new JComboBox(numbers);
        randomNumbers.setBounds(180, 190, 50, 20);
        randomNumbers.setName("randNums");

        // radio buttons
        radioButtons = new ButtonGroup();
        JRadioButton radioBtn1 = new JRadioButton("4", false);
        radioBtn1.setBounds(50, 220, 50, 20);
        radioBtn1.setName("radioBtn1");

        JRadioButton radioBtn2 = new JRadioButton("4.6", false);
        radioBtn2.setBounds(100, 220, 50, 20);
        radioBtn2.setName("radioBtn2");

        radioButtons.add(radioBtn1);
        radioButtons.add(radioBtn2);

        // checkboxes
        JCheckBox checkbox3 = new JCheckBox("46", true);
        checkbox3.setBounds(50, 250, 50, 20);
        checkbox3.setName("checkBox1");

        JCheckBox checkbox4 = new JCheckBox("54", false);
        checkbox4.setBounds(100, 250, 50, 20);
        checkbox4.setName("checkBox2");

        // radio buttons
        add(radioBtn1);
        add(radioBtn2);

        // checkboxes
        add(checkbox3);
        add(checkbox4);

        add(labelOne);
        add(numOne);

        add(labelTwo);
        add(numTwo);

        add(addBtn);

        add(labelThree);
        add(result);

        add(randomNumbers);

        ButtonsActionListener buttonsActionListener = new ButtonsActionListener();
        addBtn.addActionListener(buttonsActionListener);

        RadioButtonActionListener radioButtonActionListener = new RadioButtonActionListener();
        radioBtn1.addActionListener(radioButtonActionListener);
        radioBtn2.addActionListener(radioButtonActionListener);

        CheckBoxActionListener checkBoxActionListener = new CheckBoxActionListener();
        checkbox3.addItemListener(checkBoxActionListener);
        checkbox4.addItemListener(checkBoxActionListener);

        DropDownMenusActionListener dropDownMenusActionListener = new DropDownMenusActionListener();
        randomNumbers.addItemListener(dropDownMenusActionListener);
    }
}

class ButtonsActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        switch (button.getName()) {
            case "addBtn":
                addButton();
                break;
            default:
                break;
        }
    }

    private void addButton() {

        System.out.println("add button is clicked!");
        JTextField result = new Calculator().result;
        result.setText("");

        JTextField numOne = new Calculator().numOne;
        JTextField numTwo = new Calculator().numTwo;

        float num1 = numOne.getText().trim().length() == 0 ? 0.0f : Float.parseFloat(numOne.getText());
        float num2 = numTwo.getText().trim().length() == 0 ? 0.0f : Float.parseFloat(numTwo.getText());

        result.setText(String.valueOf(num1 + num2));
    }
}

class CheckBoxActionListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        JCheckBox checkbox = (JCheckBox) e.getSource();
        if(1 == e.getStateChange()) {
            System.out.println("stateChange: " + e.getStateChange());
            switch (checkbox.getName()) {
                case "checkBox1":
                    System.out.println("checkbox1 is checked: " + checkbox.getLabel());
                    break;
                case "checkBox2":
                    System.out.println("checkbox2 is checked: " + checkbox.getLabel());
                    break;
                default:
                    break;
            }
        }
    }
}

class DropDownMenusActionListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox choice = (JComboBox) e.getSource();
        switch (choice.getName()) {
            case "randNums":
                System.out.println("value from random numbers' dropdown is selected: " + choice.getSelectedItem());
                break;
            default:
                break;
        }
    }
}

class RadioButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton radioButton = (JRadioButton) e.getSource();
        switch (radioButton.getName()) {
            case "radioBtn1":
                System.out.println("radio button 1 is clicked: " + radioButton.getText());
                break;
            case "radioBtn2":
                System.out.println("radio button is clicked: " + radioButton.getText());
                break;
            default:
                break;
        }
    }
}

/*
<applet code="Calculator.class" width="300" height="300">
</applet>
*/
