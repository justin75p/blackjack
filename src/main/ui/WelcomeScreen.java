package main.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeScreen {
    private JFrame frame;
    private JLabel titleLabel;
    private JPanel welcomePanel;
    private JPanel buttonPanel;
    private JButton singleplayerButton;
    private JButton multiplayerButton;
    

    public WelcomeScreen() {
        frame = new JFrame();
        initializeMainFrame();

        welcomePanel = new JPanel(new BorderLayout());
        initializeWelcomePanel();

        buttonPanel = new JPanel();
        initializeButtonPanel();

        frame.add(welcomePanel);
        frame.setVisible(true);
    }

    private void initializeMainFrame() {
        frame.setTitle("Blackjack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    private void initializeWelcomePanel() {
        titleLabel = new JLabel("Blackjack");

        welcomePanel.add(titleLabel, BorderLayout.NORTH);
    }

    private void initializeButtonPanel() {
        singleplayerButton = new JButton("Singleplayer");
        multiplayerButton = new JButton("Multiplayer");

        buttonPanel.add(singleplayerButton);
        buttonPanel.add(multiplayerButton);
        welcomePanel.add(buttonPanel, BorderLayout.CENTER);
    }
}
