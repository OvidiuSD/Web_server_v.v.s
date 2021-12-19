package web.server.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionThread extends Thread{

    private Socket socket;
    private final static Logger LOGGER = LoggerFactory.getLogger(ConnectionThread.class);

    public ConnectionThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            String html = "<html><head><title>Web Server Ovidiu</title></head><body>" +
                    "<h1><center>Hello! I am Ovidiu!</center></h1>" +
                    "<p>This is my WebServer for VVS.</p>" +
                    "<p>This is the link of my GitHub: <a>https://github.com/OvidiuSD/Web_server_v.v.s</a></p>" +
                    "<h2>Development of project: </h2>" +
                    "<p> The project was developed in 3 phases:</p>" +
                    "<ol>"+
                    "<li><em> server implementation + unit tests -> <strong> week 6 </strong></em></li>" +
                    "<li><em> static + dynamic analyses (profiling) reports -> <strong> week 9 </strong></em></li>" +
                    "<li><em> graphical interface implementation + graphical user interface testing + web testing -> <strong>week 13 </strong></em></li>"+
                    "</ol>"+
                    "<menu type=\"toolbar\">" +
                    "  <li>" +
                    "    <menu label=\"File\">" +
                    "      <button type=\"button\" onclick=\"new()\">New</button>" +
                    "      <button type=\"button\" onclick=\"save()\">Save</button>" +
                    "    </menu>" +
                    "  </li>" +
                    "  <li>" +
                    "    <menu label=\"Edit\">\n" +
                    "      <button type=\"button\" onclick=\"copy()\">Copy</button>" +
                    "      <button type=\"button\" onclick=\"paste()\">Paste</button>" +
                    "    </menu>" +
                    "  </li>" +
                    "</menu>"+
                    "<body>" +
                    "<h3>Graphics Example</h3>" +
                    "<form>\n" +
                    "  <fieldset>\n" +
                    "    <legend>Guest Information</legend>" +
                    "    Name: <input type=\"text\"><br>" +
                    "    Address: <input type=\"text\"><br>" +
                    "  </fieldset>\n" +
                    "</form>"+
                    "</body>"+

                    "  <p><label for=\"Feedback\">How was your experience with website? : </label>\n" +
                    "\n" +
                    "  <select id=\"Feedback\">\n" +
                    "    <option value=\"html\">Good</option>\n" +
                    "    <option value=\"css\">Acceptable</option>\n" +
                    "    <option value=\"sql\">Bad</option>\n" +
                    "  </select></p>" +
                    "<footer> Thank you for visiting my WebSite!</footer>"+
                    "</body></html>";

            final String CRLF = "\r\n"; //13,10
            String response =
                    "HTTP/1.1 200 OK" + CRLF +
                            "Content-Length: " + html.getBytes().length + CRLF + //header
                            CRLF +
                            html +
                            CRLF + CRLF;

            outputStream.write(response.getBytes());

            LOGGER.info("Connection Processing Ended");
        } catch (IOException e) {
            LOGGER.error("Problem with communication", e);
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {}
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {}
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {}
            }
        }
    }
}
