import org.assertj.swing.applet.AppletViewer;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.launcher.AppletLauncher;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class CalculatorTest {
    private static FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @Before
    public void setUp() throws InterruptedException {
        AppletViewer viewer = AppletLauncher.applet(Calculator.class).start();
        window = new FrameFixture(viewer);
        window.show();

        /*final JTextField numOneField = (JTextField) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("numOne");
        final JTextField numTwoField = (JTextField) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("numTwo");
        final JTextField result = (JTextField) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("result");
        final JButton addBtn = (JButton) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("addBtn");
        final Checkbox radioBtn1 = (Checkbox) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("radioBtn1");
        final Checkbox radioBtn2 = (Checkbox) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("radioBtn2");
        final Checkbox checkbox1 = (Checkbox) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("checkBox1");
        final Checkbox checkbox2 = (Checkbox) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("checkBox1");
        final Choice dropDown = (Choice) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("randNums");

        Random random = new Random();
        int choice = 0;

        while(true) {
            GuiActionRunner.execute(() -> result.setText(""));
            int numOne = random.nextInt();
            int numTwo = random.nextInt();
            GuiActionRunner.execute(() -> numOneField.setText(String.valueOf(numOne)));
            GuiActionRunner.execute(() -> numTwoField.setText(String.valueOf(numTwo)));
            GuiActionRunner.execute(() -> addBtn.doClick());
            GuiActionRunner.execute(() -> result.setText(String.valueOf(numOne + numTwo)));

            choice = random.nextInt(4) + 1;
            dropDown.select(choice);

            choice = random.nextInt(2);
            System.out.println("choice: " + choice);

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

            Thread.sleep(2000);
        }*/
    }

    @Test
    public void shouldAddTwoNumbersWhenClickingButton() throws InterruptedException {
        final JTextField numOneField = (JTextField) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("numOne");
        final JTextField numTwoField = (JTextField) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("numTwo");
        final JTextField result = (JTextField) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("result");
        final JButton addBtn = (JButton) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("addBtn");
        final Checkbox radioBtn1 = (Checkbox) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("radioBtn1");
        final Checkbox radioBtn2 = (Checkbox) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("radioBtn2");
        final Checkbox checkbox1 = (Checkbox) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("checkBox1");
        final Checkbox checkbox2 = (Checkbox) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("checkBox1");
        final Choice dropDown = (Choice) BasicComponentFinder.finderWithCurrentAwtHierarchy().findByName("randNums");

        Random random = new Random();
        int choice = 0;
        int temp = 2;

        while(temp != 0) {
            GuiActionRunner.execute(() -> result.setText(""));
            int numOne = random.nextInt();
            int numTwo = random.nextInt();
            GuiActionRunner.execute(() -> numOneField.setText(String.valueOf(numOne)));
            GuiActionRunner.execute(() -> numTwoField.setText(String.valueOf(numTwo)));
            GuiActionRunner.execute(() -> addBtn.doClick());
            GuiActionRunner.execute(() -> result.setText(String.valueOf(numOne + numTwo)));

            choice = random.nextInt(4) + 1;
            dropDown.select(choice);

            choice = random.nextInt(2);
            System.out.println("choice: " + choice);

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
    }

    @After
    public void onTearDown() {
        window.cleanUp();
    }
}
