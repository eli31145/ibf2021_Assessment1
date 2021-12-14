package ibf2021_assessment;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    
    public static void main(String[] args) throws IOException{
    ServerSocket serverSocket;    
    int port = 3000;
    String[] docRoot = new String[]{"Static"};
    
    if (args.length == 0){
        System.out.println("Server listening at port 3000...");
    
        } else if (args.length == 2) {
            if (args[0] == "--port"){
                port = Integer.parseInt(args[1]); 

            } else if (args[0] == "docRoot"){
                docRoot = args[1].split(":");
            }
        
        } else if (args.length == 4 && args[0] == "--port" && args[2] == "docRoot") {
            port = Integer.parseInt(args[1]);
            docRoot = args[3].split(":");
        }
        
       HttpServer serve = new HttpServer(port, docRoot);
        serve.run();

    } 
    
    


}