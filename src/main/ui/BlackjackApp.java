    package main.ui;

    import java.awt.BorderLayout;
    import java.awt.Color;
    import java.awt.Image;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.IOException;

    import javax.imageio.ImageIO;
    import javax.swing.BorderFactory;
    import javax.swing.Box;
    import javax.swing.ImageIcon;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JOptionPane;
    import javax.swing.JPanel;
    import javax.swing.JTextArea;
    import javax.swing.JTextField;
    import javax.swing.border.Border;

import main.model.Card;
    import main.model.Dealer;
    import main.model.Player;

    public class BlackjackApp {
        private JFrame frame;
        private JPanel gamePanel;
        private JPanel dealerPanel;
        private JPanel playerPanel;
        private JPanel playerCardsPanel;
        private JButton hitButton;
        private JButton standButton;
        private JButton wagerButton;
        private JTextField betAmountField;

        private static final String BACK_CARD_PATH = "data/cards/BACK.png";
        private static final int CARD_HEIGHT = 150;
        private static final int CARD_WIDTH = 125;

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
            dealerPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

            // Panel containing the player's cards
            playerCardsPanel = new JPanel();
            playerCardsPanel.setBackground(new Color(53, 101, 77));
            playerCardsPanel.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));

            // Player panel containing buttons and bet amount field
            playerPanel = new JPanel();
            initializePlayerPanel();
            
            frame.add(gamePanel);

            gamePanel.add(playerPanel, BorderLayout.SOUTH);
            gamePanel.add(playerCardsPanel, BorderLayout.CENTER);
            gamePanel.add(dealerPanel, BorderLayout.NORTH);

            initializeDealerCards();
            initializePlayerCards();


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
            hitButton.setBorder(BorderFactory.createEmptyBorder(10, 55, 10, 55));
            hitButton.addActionListener(new HitButtonHandler());
            playerPanel.add(hitButton);
            playerPanel.add(Box.createHorizontalStrut(10));

            standButton = new JButton("Stand");
            standButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
            standButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
            standButton.addActionListener(new StandButtonHandler());
            playerPanel.add(standButton);
            playerPanel.add(Box.createHorizontalStrut(10));

            wagerButton = new JButton("Wager");
            wagerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
            wagerButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
            wagerButton.addActionListener(new WagerButtonHandler());
            playerPanel.add(wagerButton);
        }

        private void initializeDealerCards() {
        
            try {
                // Draw the shown card
                dealerDrawCard();

                // Draw the hidden card
                BufferedImage image = ImageIO.read(new File(BACK_CARD_PATH));
                Image scaledImage = image.getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
                ImageIcon hiddenCardIcon = new ImageIcon(scaledImage);
                JLabel hiddenCardLabel = new JLabel(hiddenCardIcon);
        
                // Add hidden card to dealer panel
                dealerPanel.add(hiddenCardLabel);
        
                // Refresh GUI
                gamePanel.revalidate();
                gamePanel.repaint();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error: Unable to load image from " + BACK_CARD_PATH);
            }
        }

        // Helper method for when the dealer draws cards
        private void dealerDrawCard() {
            dealerPanel.removeAll();
            dealer.drawCard();
            for (int i = 0; i < dealer.getCards().size(); i++) {
                BufferedImage image;
                try {
                    image = ImageIO.read(new File(dealer.getCards().get(i).getImageFileName()));
                    Image scaledImage = image.getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
                    ImageIcon cardIcon = new ImageIcon(scaledImage);
                    JLabel cardLabel = new JLabel(cardIcon);
                    dealerPanel.add(cardLabel);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Error: Unable to load image from " + dealer.getCards().get(i).getImageFileName());
                }
            }
            gamePanel.revalidate();
            gamePanel.repaint();
        }

        private void initializePlayerCards() {
            dealCard(player);
            dealCard(player);
        }

        // Helper method for dealing and drawing cards
        private void dealCard(Player player) {
            if (player.getCards().size() < 5 && player.getHandValue() < 21) {
                playerCardsPanel.removeAll();
                dealer.deal(player);
                for (Card card : player.getCards()) {
                    BufferedImage image;
                    try {
                        image = ImageIO.read(new File(card.getImageFileName()));
                        Image scaledImage = image.getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
                        ImageIcon cardIcon = new ImageIcon(scaledImage);
                        JLabel cardLabel = new JLabel(cardIcon);
                        playerCardsPanel.add(cardLabel);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error: Unable to load image from " + card.getImageFileName());
                    }
                }
                // Refresh
                gamePanel.revalidate();
                gamePanel.repaint();
            } else {
                // Player will automatically win if has 5 cards in hand and overall value is < 21
            }
        }

        public class HitButtonHandler implements ActionListener {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!player.isTurnOver()) {
                    dealCard(player);
                }
            }
            
        }

        public class StandButtonHandler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getHandValue() <= 21) {
                    player.setTurnOver();
                    while (dealer.getHandValue() < 17) {
                        dealerDrawCard();
                    }
                }
                
            }
            
        }

        public class WagerButtonHandler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = JOptionPane.showInputDialog("Enter your wager amount:");
                try {
                    Double.parseDouble(amount);
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Try again.");
                }

                if (player.wager(Double.parseDouble(amount))) {
                    // stub
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient balance.");
                }
            }
            
        }
    }