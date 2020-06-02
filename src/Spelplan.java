import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Spelplan extends Canvas{
    private int [] spelplan;
    private int width;
    private int height;
    PrintWriter out;
    BufferedReader in;



    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    private int[] pixels;
    private int scale = 4;
    JFrame frame;
    private BufferedImage image;


    public Spelplan(int w, int h, PrintWriter out, BufferedReader in) {
        this.width = w;
        this.height = h;
        this.out = out;
        this.in = in;
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
        out.println(JOptionPane.showInputDialog(null,"Välj koordinat",""));

        String msg = null;
        try {
            msg = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,msg,"Server said",JOptionPane.INFORMATION_MESSAGE);
    }
    private void update() {
        for (int y = 0 ; y < getHeight() ; y++) {
            for (int x = 0; x < getWidth(); x++) {
                pixels[y*getWidth()+x] = ((spelplan[y*getWidth()+x]==0 ?0x000000:0xFFFFFF));
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
                update();
                lastUpdate = System.currentTimeMillis();
            }
        }
    }


    /*public static void main (String[] args) {
                Spelplan s = new Spelplan(10,10, out, in);
                s.frame.add(s);
                s.frame.pack();
                s.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                s.frame.setLocationRelativeTo(null);
                s.frame.setVisible(true);
                s.run();
                // här gjorde jag spelplanen


}*/


}