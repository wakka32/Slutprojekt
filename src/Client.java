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
            Spelplan s = new Spelplan(10,10);
            s.frame.add(s);
            s.frame.pack();
            s.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            s.frame.setLocationRelativeTo(null);
            s.frame.setVisible(true);
            s.run();
            // här gjorde jag spelplanen

        try {
            socket = new Socket(ip,port);
        } catch (Exception e) {
            System.out.println("Client failed to connect");
            System.exit(0);
        }

        // GO
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(JOptionPane.showInputDialog(null,"Välj koordinat",""));

            String msg = in.readLine();
            JOptionPane.showMessageDialog(null,msg,"Server said",JOptionPane.INFORMATION_MESSAGE);

            in.close();
            out.close();
            socket.close();
            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println("Client failed to communicate");
        }
    }
}