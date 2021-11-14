package web.server.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
    private int port;
    private String webroot;
    private ServerSocket serverSocket;
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerThread.class);

    public ServerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }


    public void run(){
        try {
            while(serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();

                LOGGER.info("Connection accepted: " + socket.getInetAddress());

                ConnectionThread workerThread = new ConnectionThread(socket);
                workerThread.start();

            }

        } catch (IOException e) {
            LOGGER.error("Problem with setting socket", e);
        }finally{
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {}
            }
        }
    }
}
