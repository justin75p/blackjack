package main.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BlackjackApp {
    private JFrame frame;
    private JPanel gamePanel;

    public BlackjackApp() {
        // Main frame
        frame = new JFrame();
        initializeMainFrame();

        // Game panel
        gamePanel = new JPanel();
        initializeGamePanel();


        frame.add(gamePanel);

        frame.setVisible(true);
    }

    private void initializeMainFrame() {
        frame.setTitle("Blackjack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    private void initializeGamePanel() {
        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(53, 101, 77));
    }
}