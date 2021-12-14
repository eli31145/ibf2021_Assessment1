package ibf2021_assessment;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpClientConnection implements Runnable{
    Socket socket;
    String[] docRoot;
    HttpWriter writer;

    public HttpClientConnection(Socket socket, String[] docRoot) throws IOException{
        this.socket = socket;
        this.docRoot = docRoot;
        this.writer = new HttpWriter(socket.getOutputStream());
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
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } 
            
            for (String s: docRoot) {
                String fullDirectory = s + array[1];
                Path p1 = Paths.get(fullDirectory);
                File f1 = p1.toFile();

                if (!f1.exists()) {
                    System.out.println("Not Found\r\n\r\n <resource name> not found\r\n");
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (f1.exists()) {
                //case 3: GET correct, resource exists ()
                try {
                writer.writeString("HTTP/1.1 200OK");
                writer.writeString();
                writer.writeString("<resource contents as bytes>");
                socket.close();
                } catch (Exception e)   {
                    e.printStackTrace();
                }
            }
                
                //case 3.1: GET correct, resource exists but a PNG image
                    if (array[0].equals("GET") && f1.exists() && fullDirectory.contains("png")){
                        try {
                        writer.writeString("HTTP/1.1 200OK");
                        writer.writeString("Content-Type: image/png");
                        writer.writeString();
                        writer.writeString("<resource contents as bytes>");
                        socket.close();
                        } catch (Exception e)   {
                            e.printStackTrace();
                        }
                    }
        
        
        }



    }


    
}
