package web.server.GUI;

import web.server.configuration.Config;
import web.server.configuration.ConfigManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class GraphicUI extends JFrame {
     JPanel panel;
     JLabel port;
     JButton portButton, startButton;
    JTextField srvlink;


        public GraphicUI(){

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("MyLocalServer");
            this.setSize(800, 400);
            this.setVisible(true);

            panel = new JPanel();
            portButton = new JButton("Find Port");
            portButton.addActionListener(e -> portButtonPressed());
            startButton = new JButton("Start Webpage");
            startButton.addActionListener(e -> {
                try {
                    startButtonPressed();
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            });

            port = new JLabel();
           srvlink = new JTextField("http://localhost:3355");
            panel.add(portButton);
            panel.add(port);
            panel.add(srvlink);
            panel.add(startButton);

            this.add(panel);
//
        }
    void portButtonPressed(){
        Config conf = ConfigManager.getInstance().getCurrentConfiguration();
        port.setText("Port: " + conf.getPort());
    }

    void startButtonPressed() throws URISyntaxException, IOException, InterruptedException {
        String s = srvlink.getText();
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI(s));
        TimeUnit.SECONDS.sleep(3);

    }


    }


