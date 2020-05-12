import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Spelplan extends Canvas{
    private int [] spelplan;
    private int width;
    private int height;



    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
    }
    private void draw() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        update();

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        //g.setRGB(0, 0, width, height, pixels,0,width);
        g.dispose();
        bs.show();
    }
    private void update() {
        for (int y = 0 ; y < w.getHeight() ; y++) {
            for (int x = 0; x < w.getWidth(); x++) {
                pixels[y*w.getWidth()+x] = ((w.Spelplan(x,y)?0x000000:0xFFFFFF));
            }
        }
    }
    
    public void run() {
        long startTime = System.currentTimeMillis();
        long lastUpdate = startTime;
        long i = 0;
        while (true) {
            if (System.currentTimeMillis()-lastUpdate > 40) {
                draw();
                frame.setTitle("Spelplan s " + (System.currentTimeMillis()-startTime)/1000 + "s, " + i + " iterations");
                i++;
                w.update();
                lastUpdate = System.currentTimeMillis();
            }
        }
    }


    public static void main (String[] args) {
                Spelplan s = new Spelplan(10,10);
                s.frame.add(s);
                s.frame.pack();
                s.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                s.frame.setLocationRelativeTo(null);
                s.frame.setVisible(true);
                s.run();


}
}