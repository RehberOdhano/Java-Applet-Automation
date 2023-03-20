import org.assertj.swing.applet.AppletViewer;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.launcher.AppletLauncher;
public class Main {
    public static void main(String[] args) {
        AppletViewer viewer = AppletLauncher.applet(Calculator.class).start();
        FrameFixture applet = new FrameFixture(viewer);
        applet.show();
    }
}