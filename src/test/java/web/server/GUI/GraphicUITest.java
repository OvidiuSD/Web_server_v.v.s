package web.server.GUI;

import org.junit.jupiter.api.*;
import web.server.configuration.ConfigManager;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)
 class GraphicUITest {


        private GraphicUI gui;

        @BeforeAll
        public void setup(){
            gui = new GraphicUI();
            gui.port = new JLabel();
            gui.portButton = new JButton();
            gui.srvlink = new JTextField("http://localhost:3355");
            ConfigManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        }

        @Test
        void startTest(){
            assertNotNull(gui.port);
            assertNotNull(gui.portButton);
            assertNotNull(gui.srvlink);
            int height = gui.getHeight();
            int width = gui.getWidth();
            assertEquals(400, height);
            assertEquals(800, width);
            assertEquals("MyLocalServer", gui.getTitle());
        }


        @Test
        void portButtonPressedTest(){
            gui.portButtonPressed();
            assertEquals( "Port: 3355", gui.port.getText());
        }

        @Test
        void linkValueTest(){
            assertEquals("http://localhost:3355", gui.srvlink.getText());
        }


    }

