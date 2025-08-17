package main.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class WelcomeScreen {
    private JFrame frame;
    private JLabel titleLabel;
    private JPanel welcomePanel;
    private JPanel buttonPanel;
    private JButton singleplayerButton;
    private JButton multiplayerButton;

    private static final String BACKGROUND_IMAGE_PATH = "data/backgrounds/welcome-screen-background.jpg";

    public WelcomeScreen() {
        frame = new JFrame();
        initializeMainFrame();

        welcomePanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage bgImage = ImageIO.read(new File(BACKGROUND_IMAGE_PATH));
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
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
        frame.setSize(750, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    private void initializeWelcomePanel() {
        titleLabel = new JLabel("BLACKJACK");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        titleLabel.setForeground(new Color(255, 255, 50));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(0, 0, 0, 100));

        welcomePanel.add(titleLabel, BorderLayout.NORTH);
    }

    private void initializeButtonPanel() {
        singleplayerButton = new JButton("SINGLEPLAYER");
        singleplayerButton.setFont(new Font("Serif", Font.BOLD, 18));
        singleplayerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        singleplayerButton.setMargin(new Insets(10, 50, 10, 50));
        singleplayerButton.addActionListener(new SingleplayerButtonHandler());

        multiplayerButton = new JButton("MULTIPLAYER");
        multiplayerButton.setFont(new Font("Serif", Font.BOLD, 18));
        multiplayerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        multiplayerButton.setMargin(new Insets(10, 50, 10, 50));
        multiplayerButton.addActionListener(new MultiplayerButtonHandler());
        
        buttonPanel.add(singleplayerButton);
        buttonPanel.add(multiplayerButton);
        welcomePanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public class MultiplayerButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("How many players would you like to play with? (2-5)");
            try {
                int numPlayers = Integer.parseInt(input);
                if (numPlayers >= 2 && numPlayers <= 5) {
                    frame.dispose();
                    MultiplayerBlackjack multiplayerBlackjack = new MultiplayerBlackjack(numPlayers);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter 2-5 players.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input.");
            }
        }
        
    }
    public class SingleplayerButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            BlackjackApp blackjack = new BlackjackApp();
        }
        
    }
}
