import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Spelplan {
    private int [] spelplan;
    private int width;
    private int height;



    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static void main (String[] args) {
    }

    private int[] pixels;
    private int scale = 4;
    private JFrame frame;
    private BufferedImage image;

    private Spelplan w;

    public Spelplan(int w, int h) {
        this.width = w;
        this.height = h;
        spelplan = new int [w*h];
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        Dimension size = new Dimension(scale*w, scale*h);
        setPreferredSize(size);
        frame = new JFrame();
        this.w = new Spelplan(w,h);
    }
    private void draw() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
    }


}
