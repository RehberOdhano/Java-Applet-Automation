import java.applet.Applet;
import java.awt.*;

public class SampleApplet extends Applet {
    public void paint(Graphics graphics) {
        graphics.drawRect(0, 0, 250, 100);
        graphics.setColor(Color.BLUE);
        graphics.drawString("Hello World", 150, 150);
    }
}
