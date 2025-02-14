package main.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.model.Dealer;
import main.model.Player;

public class BlackjackApp {
    private JFrame frame;
    private JPanel gamePanel;
    private JPanel dealerPanel;
    private JPanel playerPanel;
    private JButton hitButton;
    private JButton standButton;
    private JButton wagerButton;
    private JTextField betAmountField;

    private static final String BACK_CARD_PATH = "data/cards/BACK.png";
    private static final int CARD_HEIGHT = 125;
    private static final int CARD_WIDTH = 100;

    private Dealer dealer;
    private Player player;

    public BlackjackApp() {

        player = new Player();
        dealer = new Dealer();

        // Main frame
        frame = new JFrame();
        initializeMainFrame();

        // Game panel
        gamePanel = new JPanel();
        initializeGamePanel();

        // Dealer panel containing the dealer's cards
        dealerPanel = new JPanel();
        dealerPanel.setBackground(new Color(53, 101, 77));


        // Player panel containing buttons and bet amount field
        playerPanel = new JPanel();
        initializePlayerPanel();
        


        frame.add(gamePanel);

        gamePanel.add(playerPanel, BorderLayout.SOUTH);
        gamePanel.add(dealerPanel, BorderLayout.NORTH);

        initializeDealerCards();

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

    private void initializeDealerCards() {
    
        try {
            BufferedImage image = ImageIO.read(new File(BACK_CARD_PATH));
        
            Image scaledImage = image.getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
            ImageIcon hiddenCardIcon = new ImageIcon(scaledImage);
            JLabel hiddenCardLabel = new JLabel(hiddenCardIcon);

            image = ImageIO.read(new File(dealer.getCards().get(1).getImageFileName()));
            scaledImage = image.getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
            ImageIcon shownCardIcon = new ImageIcon(scaledImage);
            JLabel shownCardLabel = new JLabel(shownCardIcon);
    
            // Add it to game panel
            dealerPanel.add(hiddenCardLabel);
            dealerPanel.add(shownCardLabel);
    
            // Refresh GUI
            gamePanel.revalidate();
            gamePanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: Unable to load image from " + BACK_CARD_PATH);
        }
    }

    private void initializePlayerCards() {

    }
}