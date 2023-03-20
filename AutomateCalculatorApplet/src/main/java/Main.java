import org.assertj.swing.applet.AppletViewer;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.launcher.AppletLauncher;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        AppletViewer viewer = AppletLauncher.applet(Calculator.class).start();
        FrameFixture applet = new FrameFixture(viewer);
        ComponentFinder componentFinder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
        applet.show();

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
        final JCheckBox checkbox2 = (JCheckBox) componentFinder.findByName("checkBox2");

        // dropdown menu
        final JComboBox dropDown = (JComboBox) componentFinder.findByName("randNums");

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

            final int dropdownItemIndex = choice;
            GuiActionRunner.execute(() -> dropDown.setSelectedIndex(dropdownItemIndex));

            choice = random.nextInt(2);

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

            Thread.sleep(2000);
        }
    }
}
