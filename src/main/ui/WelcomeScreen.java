package main.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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

        multiplayerButton = new JButton("MULTIPLAYER");
        multiplayerButton.setFont(new Font("Serif", Font.BOLD, 18));
        multiplayerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        multiplayerButton.setMargin(new Insets(10, 50, 10, 50));

        buttonPanel.add(singleplayerButton);
        buttonPanel.add(multiplayerButton);
        welcomePanel.add(buttonPanel, BorderLayout.SOUTH);
    }
}
