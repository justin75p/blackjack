package main.ui;

import javax.swing.JButton;
import javax.swing.JFrame;

public class WelcomeScreen {
    private JFrame frame;
    private JButton singleplayerButton;
    private JButton multiplayerButton;
    

    public WelcomeScreen() {
        frame = new JFrame();
        frame.setTitle("Blackjack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
