import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author magnus
 */
public class Server {
    public static void main(String[] args) {
        int port = 1234;
        ServerSocket serverSocket;
        Socket socket = null;
        System.out.println("Server started.");
        PrintWriter out = null;
        BufferedReader in = null;


        try {
            serverSocket = new ServerSocket(port);

                System.out.println("Waiting for connections!");
                socket = serverSocket.accept();
                // Go
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (Exception e){}
            Spelplan s = new Spelplan(10, 10, out, in);
            s.frame.add(s);
            s.frame.pack();
            s.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            s.frame.setLocationRelativeTo(null);
            s.frame.setVisible(true);


            // här gjorde jag spelplanen

            try {

                System.out.println("Client connected!");

                //Protocol
                String name = in.readLine();
                if (name.equals("Shutdown")) {
                    out.println("SERVER: Oh no, you terminated me...");
                    in.close();
                    out.close();
                    socket.close();
                    System.out.println("server shutting down...");
                    System.exit(0);
                }
                System.out.println("Client name is \"" + name + "\"");
                System.out.println("Sending feedback");
                out.println("SERVER: Welcome " + name + "! Keep up the good work");

                s.run();

                System.out.println("Closing down " + name);

            } catch (Exception e) {
                System.out.println("Server fail");
                // Detta är min nätverkdel tillsammans med client.java

            }
        }
    }
