/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.Date;

/**
 *
 * @author marcj_000
 */
public class TextProcessor {
    
    public String process(String message){   
        
        
        if(message.equals("time")) return new String ("Time: "+new Date());
        if(message.contains("REVERSE#")){
            System.out.println("Char at (7):" + message.charAt(7));
            String reversedText = "";
            for (int i = message.length()-1; i > 7; i--) {
                
                reversedText = reversedText.concat(Character.toString(message.charAt(i)));
                
            }
            return reversedText;
        }
        
        if(message.contains("UPPER#")) {
            
            String a = message.toUpperCase();
            String[] split = a.split("#");
            return split[split.length-1];
        }
        if(message.contains("LOWER#")) {
            
            String a = message.toLowerCase();
            String[] split = a.split("#");
            return split[split.length-1];
        }
        if(message.equals("TRANSELATE#hund")) return new String("dog");
        
        return message; 
    }
    
}
