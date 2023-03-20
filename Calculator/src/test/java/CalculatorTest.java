import org.assertj.swing.applet.AppletViewer;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.*;
import org.assertj.swing.launcher.AppletLauncher;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {
    private static FrameFixture applet;
    private ComponentFinder componentFinder;
    private AppletViewer viewer;
    private Random random;
    private int temp = 0;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @Before
    public void setUp() throws InterruptedException {
        viewer = AppletLauncher.applet(Calculator.class).start();
        applet = new FrameFixture(viewer);
        componentFinder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        random = new Random();
        temp = 2;
        applet.show();
    }

    @Test
    public void shouldAddTwoNumbersWhenClickingButtonAssertJ() throws InterruptedException {
        int numOne = 0, numTwo = 0;

        // text fields and button
        JTextComponentFixture resultTextField = applet.textBox("result");
        JTextComponentFixture numOneTextField = applet.textBox("numOne");
        JTextComponentFixture numTwoTextField = applet.textBox("numTwo");
        JButtonFixture addBtn = applet.button("addBtn");

        // radio buttons
        JRadioButtonFixture radioBtn1 = applet.radioButton("radioBtn1");
        JRadioButtonFixture radioBtn2 = applet.radioButton("radioBtn2");

        // checkboxes
        JCheckBoxFixture checkbox1 = applet.checkBox("checkBox1");
        JCheckBoxFixture checkbox2 = applet.checkBox("checkBox2");

        // dropdown menu of random numbers
        JComboBoxFixture dropdown = applet.comboBox("randNums");

        while (temp != 0) {
            // clearing the result field...
            resultTextField.setText("");

            // generating two random numbers...
            numOne = random.nextInt();
            numTwo = random.nextInt();

            // setting the text fields with random numbers respectively...
            numOneTextField.setText(String.valueOf(numOne));
            numTwoTextField.setText(String.valueOf(numTwo));
            resultTextField.setText(String.valueOf(numOne + numTwo));

            // clicking the add button
            addBtn.click();
            resultTextField.setText(String.valueOf(numOne + numTwo));

            // assertion
            resultTextField.requireText(String.valueOf(numOne + numTwo));

            // generating a random number to select a single random value from the dropdown menu
            int choice = random.nextInt(4) + 1;
            dropdown.selectItem(choice);

            // generating a random number to select one of the radio buttons
            choice = random.nextInt(2);

            switch (choice) {
                case 0:
                    radioBtn1.check();
                    break;
                case 1:
                    radioBtn2.check();
                    break;
                default:
                    break;
            }

            // generating a random number to select one of the checkboxes
            choice = random.nextInt(2);

            switch (choice) {
                case 0:
                    checkbox1.check();
                    break;
                case 1:
                    checkbox2.check();
                    break;
                default:
                    break;
            }

            temp--;
            Thread.sleep(2000);
        }
    }

    @Test
    public void shouldAddTwoNumbersWhenClickingButton() throws InterruptedException {
        // text fields
        final JTextField numOneField = (JTextField) componentFinder.findByName("numOne");
        final JTextField numTwoField = (JTextField) componentFinder.findByName("numTwo");
        final JTextField result = (JTextField) componentFinder.findByName("result");

        // add button
        final JButton addBtn = (JButton) componentFinder.findByName("addBtn");

        // radio buttons
        final JRadioButton radioBtn1 = (JRadioButton) componentFinder.findByName("radioBtn1");
        final JRadioButton radioBtn2 = (JRadioButton) componentFinder.findByName("radioBtn2");

        // checkboxes
        final JCheckBox checkbox1 = (JCheckBox) componentFinder.findByName("checkBox1");
        final JCheckBox checkbox2 = (JCheckBox) componentFinder.findByName("checkBox1");

        // dropdown menu
        final JComboBox dropDown = (JComboBox) componentFinder.findByName("randNums");

        while(temp != 0) {
            GuiActionRunner.execute(() -> result.setText(""));
            int numOne = random.nextInt();
            int numTwo = random.nextInt();
            GuiActionRunner.execute(() -> numOneField.setText(String.valueOf(numOne)));
            GuiActionRunner.execute(() -> numTwoField.setText(String.valueOf(numTwo)));
            GuiActionRunner.execute(() -> addBtn.doClick());
            GuiActionRunner.execute(() -> result.setText(String.valueOf(numOne + numTwo)));

            // tests/assertions
            applet.textBox("result").requireText(String.valueOf(numOne + numTwo));
            assertThat(result.getText()).isEqualTo(String.valueOf(numOne + numTwo));

            int dropdownItemIndex = random.nextInt(4) + 1;
            GuiActionRunner.execute(() -> dropDown.setSelectedIndex(dropdownItemIndex));

            int choice = random.nextInt(2);

            switch (choice) {
                case 0:
                    GuiActionRunner.execute(() -> radioBtn1);
                    break;
                case 1:
                    GuiActionRunner.execute(() -> radioBtn2);
                    break;
                default:
                    break;
            }

            choice = random.nextInt(2);

            switch (choice) {
                case 0:
                    GuiActionRunner.execute(() -> checkbox1);
                    break;
                case 1:
                    GuiActionRunner.execute(() -> checkbox2);
                    break;
                default:
                    break;
            }

            temp--;
            Thread.sleep(2000);
        }
    }

    @After
    public void onTearDown() {
        viewer.unloadApplet();
        applet.cleanUp();
    }
}
