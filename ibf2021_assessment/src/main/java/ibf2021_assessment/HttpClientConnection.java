package ibf2021_assessment;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Path;

public class HttpClientConnection implements Runnable{
    
    public static void main(String[] args){
    Socket socket;
    ServerSocket serverSocket;
    
   //implement runnable, read the request(template), pass the request
   //if not get, give error

    }

    @Override
    public void run() {
            PrintWriter out = null;
            BufferedReader in = null;
            String line = "";
     
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                line = in.readLine();
             } catch (IOException ioe) {
                System.out.println("Something went wrong");
            }
     //Split by space, check for GET & /about.html
            String[] array = line.split(" ");
            //case 1: GET not correct
            if (!array[0].equals("GET")){
                System.out.println("Method Not Allowed\r\n\r\n <method name> not supported\r\n");
                //close thread
                Socket.close();
                } 
            
            for (String s: docRoot) {
                String fullDirectory = 's' + array[1];
                Path p1 = Paths.get(fullDirectory);
                File f1 = p1.toFile();
            }
            
            if (array[0].equals("GET"){
            //case 2: GET correct, resource not found    
                if (!f1.exists())
                    System.out.println("Not Found\r\n\r\n <resource name> not found\r\n");
                Socket.close();
                    }
            //case 3: GET correct, resource exists ()
                f1.exists()
                
                
                } 


            //case 3.1: GET correct, resource exists but a PNG image

            // found
            // send page or image
        
        
    }






    
}
