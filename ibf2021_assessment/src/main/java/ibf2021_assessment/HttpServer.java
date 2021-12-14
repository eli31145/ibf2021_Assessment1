package ibf2021_assessment;

import java.net.ServerSocket;
import java.util.concurrent.*;

public class HttpServer {
    
    // constructor
    public HttpServer() {
        
        int port;
        Socket socket;
        ServerSocket serverSocket;
        
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        serverSocket = new ServerSocket(3000);
        System.out.println("Server listening at port");
    
        try {

            while (true) {
                socket = serverSocket.accept();
                int id = (int) (Math.random()*100);
                HttpClientConnection worker = new HttpClientConnection(socket, id, inputFile);
                threadPool.submit(worker);
            }

        } finally {
           serverSocket.close();
        }
    }
    
    
    
    //serversocket
    //fixedthreadpool

    // check the docs
}
