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
import java.awt.*;
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
        /*JCheckBoxFixture radioBtn1 = applet.checkBox("radioBtn1");
        JCheckBoxFixture radioBtn2 = applet.checkBox("radioBtn2");

        // checkboxes
        JCheckBoxFixture checkbox1 = applet.checkBox("checkbox1");
        JCheckBoxFixture checkbox2 = applet.checkBox("checkbox2");

        // dropdown menu of random numbers
        JComboBoxFixture dropdown = applet.comboBox("randNums");*/

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

            // assertion
            resultTextField.requireText(String.valueOf(numOne + numTwo));

            // generating a random number to select a single random value from the dropdown menu
            /*int choice = random.nextInt(4) + 1;
            dropdown.selectItem(choice);

            // generating a random number to select one of the radio buttons
            choice = random.nextInt(2);

            switch (choice) {
                case 0:
                    GuiActionRunner.execute(() -> radioBtn1.check());
                    break;
                case 1:
                    GuiActionRunner.execute(() -> radioBtn2.check());
                    break;
                default:
                    break;
            }

            // generating a random number to select one of the checkboxes
            choice = random.nextInt(2);

            switch (choice) {
                case 0:
                    GuiActionRunner.execute(() -> checkbox1.check());
                    break;
                case 1:
                    GuiActionRunner.execute(() -> checkbox2.check());
                    break;
                default:
                    break;
            }*/

            temp--;
            Thread.sleep(2000);
        }
    }

    /*@Test
    public void shouldAddTwoNumbersWhenClickingButton() throws InterruptedException {
        final JTextField numOneField = (JTextField) componentFinder.findByName("numOne");
        final JTextField numTwoField = (JTextField) componentFinder.findByName("numTwo");
        final JTextField result = (JTextField) componentFinder.findByName("result");
        final JButton addBtn = (JButton) componentFinder.findByName("addBtn");
        final Checkbox radioBtn1 = (Checkbox) componentFinder.findByName("radioBtn1");
        final Checkbox radioBtn2 = (Checkbox) componentFinder.findByName("radioBtn2");
        final Checkbox checkbox1 = (Checkbox) componentFinder.findByName("checkBox1");
        final Checkbox checkbox2 = (Checkbox) componentFinder.findByName("checkBox1");
        final Choice dropDown = (Choice) componentFinder.findByName("randNums");

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

            int choice = random.nextInt(4) + 1;
            dropDown.select(choice);

            choice = random.nextInt(2);

            switch (choice) {
                case 0:
                    GuiActionRunner.execute(() -> radioBtn2.getCheckboxGroup().setSelectedCheckbox(radioBtn1));
                    break;
                case 1:
                    GuiActionRunner.execute(() -> radioBtn1.getCheckboxGroup().setSelectedCheckbox(radioBtn2));
                    break;
                default:
                    break;
            }

            choice = random.nextInt(2);

            switch (choice) {
                case 0:
                    GuiActionRunner.execute(() -> checkbox1);
                    GuiActionRunner.execute(() -> checkbox2.setState(false));
                    break;
                case 1:
                    GuiActionRunner.execute(() -> checkbox2.setState(true));
                    GuiActionRunner.execute(() -> checkbox1.setState(false));
                    break;
                default:
                    break;
            }

            temp--;
            Thread.sleep(2000);
        }
    }*/

    @After
    public void onTearDown() {
        viewer.unloadApplet();
        applet.cleanUp();
    }
}
