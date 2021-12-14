package ibf2021_assessment;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.*;
import java.util.concurrent.*;

public class HttpServer {
    private int port;
    ServerSocket serverSocket;
    String[] docRoot;
    
    // constructor
    public HttpServer(int port, String[] docRoot) {
        this.port = port;
        this.docRoot = docRoot;
        
    }   
    
    public void run() throws IOException{
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //catch error 
        serverSocket = new ServerSocket(port);
        System.out.println("Server listening at port");
    
        try {

            while (true) {
                Socket socket = serverSocket.accept();
                HttpClientConnection worker = new HttpClientConnection(socket, docRoot);
                threadPool.submit(worker);
            }

        } finally {
           serverSocket.close();
        }
    }
        

        // method to check files
        public void checkPath(){
            //check if: path exists, path is a directory, path readable by server
            for (String dir: docRoot){
                Path dirPath = Paths.get(dir);
                if (!Files.isDirectory(dirPath) || !Files.isDirectory(dirPath)){
                    System.out.println("Error with directory");
                    System.exit(1);
                } 
            }
            

        }
    
}
