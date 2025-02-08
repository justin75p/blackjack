package main.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BlackjackApp {
    private JFrame frame;
    private JPanel gamePanel;
    private JPanel playerPanel;
    private JButton hitButton;
    private JButton standButton;
    private JButton wagerButton;
    private JTextField betAmountField;
    

    public BlackjackApp() {
        // Main frame
        frame = new JFrame();
        initializeMainFrame();

        // Game panel
        gamePanel = new JPanel();
        initializeGamePanel();

        // Player panel containing buttons and bet amount field
        playerPanel = new JPanel();
        initializePlayerPanel();

        frame.add(gamePanel);

        gamePanel.add(playerPanel, BorderLayout.SOUTH);

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

    private void initializePlayerPanel() {
        playerPanel.setBackground(Color.DARK_GRAY);

        hitButton = new JButton("Hit");
        hitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        playerPanel.add(hitButton);
        playerPanel.add(Box.createHorizontalStrut(10));

        standButton = new JButton("Stand");
        standButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        playerPanel.add(standButton);
        playerPanel.add(Box.createHorizontalStrut(10));

        betAmountField = new JTextField("Enter your bet amount:");
        playerPanel.add(betAmountField);

        wagerButton = new JButton("Wager");
        wagerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        playerPanel.add(wagerButton);
        playerPanel.add(Box.createHorizontalStrut(10));
    }
}