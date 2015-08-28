/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcj_000
 */
public class EchoClassDemo {

    
    public static final int PORT = 8080;
    public static final String IP = "localhost"; //localhost
    public ServerSocket serverSocket;

    
    private void startServer() throws IOException{
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(IP,PORT));
        System.out.println("Server Started " + new Date());
            
        Thread thread = new Thread(new Handle(serverSocket)); //blocking call, 
        thread.start();
    }
    
    public static void main(String[] args){
        
        try {
            new EchoClassDemo().startServer();
        } catch (IOException ex) {
            Logger.getLogger(EchoClassDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
