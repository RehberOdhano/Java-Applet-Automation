import org.assertj.swing.applet.AppletViewer;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.launcher.AppletLauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class Main {
    public static void main(String[] args) {
        FrameFixture window;
        AppletViewer viewer = AppletLauncher.applet(Calculator.class).start();
        window = new FrameFixture(viewer);
        window.show();
    }
}

public class Calculator extends JApplet {
    JLabel labelOne;
    JLabel labelTwo;
    JLabel labelThree;

    JTextField numOne;
    JTextField numTwo;
    JTextField result;
    JButton add;

    CheckboxGroup radioBtns = null;

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
        labelOne.setBounds(50,15,100,20);
        numOne = new JTextField();
        numOne.setName("numOne");
        numOne.setBounds(50,40,100,20);

        labelTwo = new JLabel("Enter a number");
        labelTwo.setBounds(50,70,100,20);
        numTwo = new JTextField();
        numTwo.setName("numTwo");
        numTwo.setBounds(50,100,100,20);

        add = new JButton("Add");
        add.setBounds(50,130,100,20);
        add.setName("addBtn");

        labelThree = new JLabel("Result");
        labelThree.setBounds(50,160,100,20);
        result = new JTextField();
        result.setBounds(50,190,100,20);
        result.setEditable(false);
        result.setName("result");

        // dropdown menu
        Choice randomNumbers = new Choice();
        randomNumbers.add("1.1");
        randomNumbers.add("11");
        randomNumbers.add("6.0");
        randomNumbers.add("23");
        randomNumbers.add("10");
        randomNumbers.setBounds(180, 190, 50, 20);
        randomNumbers.setName("randNums");

        // radio buttons
        radioBtns = new CheckboxGroup();
        Checkbox checkbox1 = new Checkbox("4", radioBtns, true);
        checkbox1.setBounds(50, 220, 50, 20);
        checkbox1.setName("radioBtn1");
        Checkbox checkbox2 = new Checkbox("4.6", radioBtns, false);
        checkbox2.setBounds(100, 220, 50, 20);
        checkbox2.setName("radioBtn2");

        // checkboxes
        Checkbox checkbox3 = new Checkbox("46", true);
        checkbox3.setBounds(50, 250, 50, 20);
        checkbox3.setName("checkBox1");

        Checkbox checkbox4 = new Checkbox("54",false);
        checkbox4.setBounds(100, 250, 50, 20);
        checkbox4.setName("checkBox2");

        // radio buttons
        add(checkbox1);
        add(checkbox2);

        // checkboxes
        add(checkbox3);
        add(checkbox4);

        add(labelOne);
        add(numOne);

        add(labelTwo);
        add(numTwo);

        add(add);

        add(labelThree);
        add(result);

        add(randomNumbers);

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("button is clicked!");
                result.setText("");
                float num1 = numOne.getText().trim().length() == 0 ? 0.0f : Float.parseFloat(numOne.getText());
                float num2 = numTwo.getText().trim().length() == 0 ? 0.0f : Float.parseFloat(numTwo.getText());
                result.setText(String.valueOf(num1 + num2));
            }
        });

        ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                // check if the item (radio button or checkbox) is selected
                if(1 == e.getStateChange()) {
                    JOptionPane.showMessageDialog(null, "Item value: " + e.getItem());
                    System.out.println("item is selected! value: " + e.getItem());
                }
            }
        };

        checkbox1.addItemListener(itemListener);
        checkbox2.addItemListener(itemListener);
        checkbox3.addItemListener(itemListener);
        checkbox4.addItemListener(itemListener);
        randomNumbers.addItemListener(itemListener);
    }
}

/*
<applet code="Calculator.class" width="300" height="300">
</applet>
*/
