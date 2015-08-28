package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoClient {
    
    Socket socket;
    Scanner scanner;
    PrintWriter printWriter;
    String ip;
    int port;
    
    public void connect(String ip, int port) throws IOException{
        this.ip = ip;
        this.port = port;
        socket = new Socket(ip, port);
        scanner = new Scanner(socket.getInputStream());
        printWriter = new PrintWriter(socket.getOutputStream(),true);
        System.out.println("Connected to Server " + new Date());
    }
    
    public void send(String message){
        printWriter.println(message);
        printWriter.flush();
    }
    
    //move to separate thread
    
    public String recieve(){
        return scanner.nextLine(); //blocking call
    }
    
    public static void main(String[] args) {
        EchoClient client = new EchoClient();
        try {
            client.connect("localhost", 8080);
            System.out.println("sending hello world");
            client.send("Hello World");
            System.out.println("recieved: "+client.recieve());
            client.send("stop");
            
        } catch (IOException ex) {
            Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("stop");
    }
    
}
