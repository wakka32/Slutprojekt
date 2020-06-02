import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author magnus
 */
public class Client {

    public static void main(String[] args) {
        String ip = (String) JOptionPane.showInputDialog(null,"IP?","Connect to..",JOptionPane.QUESTION_MESSAGE);
        int port = Integer.parseInt(JOptionPane.showInputDialog(null,"Port?","Connect to..",JOptionPane.QUESTION_MESSAGE));       ;
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;


        try {
            socket = new Socket(ip,port);
        } catch (Exception e) {
            System.out.println("Client failed to connect");
            System.exit(0);
        }

        // GO
        try {
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Spelplan s = new Spelplan(10,10, out, in);
            s.frame.add(s);
            s.frame.pack();
            s.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            s.frame.setLocationRelativeTo(null);
            s.frame.setVisible(true);
            s.run();
            // här gjorde jag spelplanen


            in.close();
            out.close();
            socket.close();
            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println("Client failed to communicate");
        }
        // Detta är min nätverkdel tillsammans med server.java
    }
}