package com.treasuresconquests.playground;

import javax.swing.*;
import java.awt.*;



    public  class FinalScreen extends JWindow {
        Image splashScreen;
        ImageIcon imageIcon;
        private String pathname
                = "resources/assets/images/japanLanding.jpg";
        public FinalScreen() {
            splashScreen = Toolkit.getDefaultToolkit().getImage(pathname);
            // Create ImageIcon from Image
            imageIcon = new ImageIcon(splashScreen);
            // Set JWindow size from image size
            setSize(imageIcon.getIconWidth(),imageIcon.getIconHeight());
            setSize(400,500);
            // Get current screen size
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            // Get x coordinate on screen for make JWindow locate at center
            int x = (screenSize.width-getSize().width)/2;
            // Get y coordinate on screen for make JWindow locate at center
            int y = (screenSize.height-getSize().height)/2;
            // Set new location for JWindow
            setLocation(x,y);
            getContentPane().add(new JLabel("Congratulations, you won!!"));
            // Make JWindow visible
            setVisible(true);
        }
//        // Paint image onto JWindow
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(splashScreen, 0, 0, this);
        }

        public static void main(String[]args) {
            FinalScreen splash = new FinalScreen();
            try {
                // Make JWindow appear for 10 seconds before disappear
                Thread.sleep(10000);
                splash.dispose();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }