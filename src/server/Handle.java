/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcj_000
 */
public class Handle implements Runnable {
    
    ServerSocket serverSocket;

    public Handle(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            Socket socket = serverSocket.accept();
            System.out.println("A New Client Connected");
            handleClient(socket);
        } catch (IOException ex) {
            Logger.getLogger(Handle.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("A New Client Tried To Connect, But Failed");
        }
        
    }
    
    private void handleClient(Socket s){
        try {
            Scanner scanner = new Scanner(s.getInputStream());
            PrintWriter printWriter = new PrintWriter(s.getOutputStream(),true);
            TextProcessor textProcessor = new TextProcessor();
            String message = textProcessor.process(scanner.nextLine());
            System.out.println("Message Recieved: " + message);
            
            while(!message.equals("stop")){
                message = textProcessor.process(scanner.nextLine());
                printWriter.println(message);
                printWriter.flush();
                System.out.println("Message Recieved: " + message);
            }
            s.close();
            System.out.println("A Client Has Disconnected");
        } catch (IOException ex) {
            Logger.getLogger(EchoClassDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
