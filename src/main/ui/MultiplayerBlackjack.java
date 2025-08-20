    package main.ui;

    import java.awt.BorderLayout;
    import java.awt.Color;
    import java.awt.Font;
    import java.awt.Image;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;

    import javax.imageio.ImageIO;
    import javax.swing.BorderFactory;
    import javax.swing.Box;
    import javax.swing.ImageIcon;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JOptionPane;
    import javax.swing.JPanel;
    import javax.swing.border.Border;

    import main.model.Card;
    import main.model.Dealer;
    import main.model.Player;

    public class MultiplayerBlackjack {
        private JFrame frame;

        private JPanel gamePanel;
        private JPanel dealerPanel;
        private JPanel playerPanel;
        private JPanel allPlayerCardsPanel;
        private JPanel buttonPanel;
        private JPanel infoPanel;
        private List<JPanel> playerCardsPanels;

        private JButton hitButton;
        private JButton standButton;
        private JButton nextGameButton;

        private JLabel balanceLabel;
        private JLabel currentPlayerTurnLabel;

        private static final String BACK_CARD_PATH = "data/cards/BACK.png";
        private static final int CARD_HEIGHT = 175;
        private static final int CARD_WIDTH = 125;

        private int numPlayers;
        private int currentPlayerTurn = 0;

        private Dealer dealer;
        private List<Player> players;

        public MultiplayerBlackjack(int numPlayers) {

            this.numPlayers = numPlayers;
            players = new ArrayList<>();
            playerCardsPanels = new ArrayList<>();

            for (int i = 0; i < numPlayers; i++) {
                players.add(new Player());
                // Panels containing each player's cards
                JPanel playerCards = new JPanel();
                playerCards.setLayout(null);
                playerCards.setBackground(new Color(53, 101, 77));
                playerCardsPanels.add(playerCards);
            }

            dealer = new Dealer();

            // Main frame
            frame = new JFrame();
            initializeMainFrame();

            // Game panel
            gamePanel = new JPanel();
            initializeGamePanel();

            // Info panel containing the balance of all players
            infoPanel = new JPanel();
            balanceLabel = new JLabel(formatAllPlayerBalances(), JLabel.CENTER);
            initializeInfoPanel();

            gamePanel.add(infoPanel);

            // Dealer panel containing the dealer's cards
            dealerPanel = new JPanel();
            dealerPanel.setBackground(new Color(53, 101, 77));
            dealerPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

            // Player panel containing buttons and balance field
            playerPanel = new JPanel(new BorderLayout());
            currentPlayerTurnLabel = new JLabel("Your turn, Player " + (currentPlayerTurn + 1), JLabel.CENTER);
            initializePlayerPanel();

            gamePanel.add(playerPanel, BorderLayout.SOUTH);

            // Combine all player card panels into one, then add to the main game panel
            allPlayerCardsPanel = new JPanel();
            initalizePlayerCardPanels();
            
            gamePanel.add(allPlayerCardsPanel, BorderLayout.CENTER);

            gamePanel.add(dealerPanel, BorderLayout.NORTH);

            frame.add(gamePanel);
            frame.setVisible(true);
            initializeDealerCards();
            for (int i = 0; i < numPlayers; i++) {
                askWager(players.get(i), i + 1);
            }
            initializePlayerCards();
        }

        // Helper method to initialize the main frame
        private void initializeMainFrame() {
            frame.setTitle("Blackjack");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setSize(1200, 800);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
        }

        // Helper method to initialize the game panel
        private void initializeGamePanel() {
            gamePanel.setLayout(new BorderLayout());
            gamePanel.setBackground(new Color(53, 101, 77));
        }

        // Helper method to initialize the info panel
        private void initializeInfoPanel() {
            infoPanel.setBackground(new Color(53, 101, 77));
            infoPanel.setLayout(null);
            infoPanel.setBounds(1000, 0, 200, 100);

            balanceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            balanceLabel.setForeground(Color.WHITE);
            balanceLabel.setOpaque(true);   
            balanceLabel.setBackground(new Color(53, 101, 77));
            balanceLabel.setBounds(0, 0, 200, 100);

            infoPanel.add(balanceLabel);
        }

        // Helper method to initialize the player panel containing the buttons
        private void initializePlayerPanel() {

            currentPlayerTurnLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            currentPlayerTurnLabel.setForeground(Color.WHITE);
            currentPlayerTurnLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            currentPlayerTurnLabel.setOpaque(true);   
            currentPlayerTurnLabel.setBackground(new Color(53, 101, 77));

            buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.DARK_GRAY);

            hitButton = new JButton("Hit");
            hitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
            hitButton.setBorder(BorderFactory.createEmptyBorder(10, 55, 10, 55));
            hitButton.addActionListener(new HitButtonHandler());
            buttonPanel.add(hitButton);
            buttonPanel.add(Box.createHorizontalStrut(10));

            standButton = new JButton("Stand");
            standButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
            standButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
            standButton.addActionListener(new StandButtonHandler());
            buttonPanel.add(standButton);
            buttonPanel.add(Box.createHorizontalStrut(10));

            nextGameButton = new JButton("Next Game");
            nextGameButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
            nextGameButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
            nextGameButton.addActionListener(new NextGameButtonHandler());
            nextGameButton.setEnabled(false);
            buttonPanel.add(nextGameButton);

            playerPanel.add(buttonPanel, BorderLayout.CENTER);
            playerPanel.add(currentPlayerTurnLabel, BorderLayout.NORTH);
        }

        // Helper method to initialize the dealer's cards
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

        // Helper method to initialize each player's initial two cards
        private void initializePlayerCards() {
            for (Player currentPlayer : players) {
                dealCard(currentPlayer);
                dealCard(currentPlayer);
            }
        }

        private void initalizePlayerCardPanels() {
            allPlayerCardsPanel.setBackground(new Color(53, 101, 77));
            allPlayerCardsPanel.setLayout(null);

            int playerWidth = 200;
            int playerHeight = 300;
            int startX = (1200 - (numPlayers * playerWidth)) / 2;
            
            for (int i = 0; i < playerCardsPanels.size(); i++) {
                JPanel playerCards = playerCardsPanels.get(i);
                int x = startX + (i * playerWidth);
                playerCards.setBounds(x, 0, playerWidth, playerHeight);
                allPlayerCardsPanel.add(playerCards);
            }
        }

        // Helper method for dealing and drawing cards
        private void dealCard(Player player) {
            int playerIndex = players.indexOf(player);
            playerCardsPanels.get(playerIndex).removeAll();
            dealer.deal(player);
            for (int i = 0; i < player.getCards().size(); i++) {
                Card card = player.getCards().get(i);
                BufferedImage image;
                try {
                    image = ImageIO.read(new File(card.getImageFileName()));
                    Image scaledImage = image.getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
                    ImageIcon cardIcon = new ImageIcon(scaledImage);
                    JLabel cardLabel = new JLabel(cardIcon);
                    cardLabel.setBounds(50, 50 + (i * 50), CARD_WIDTH, CARD_HEIGHT);
                    playerCardsPanels.get(playerIndex).add(cardLabel, 0);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Error: Unable to load image from " + card.getImageFileName());
                }
            }
            // Refresh
            gamePanel.revalidate();
            gamePanel.repaint();

            // Player gets 21
            if (player.getHandValue() == 21) {
                endPlayerTurn(player, null);
            }
            // Player busts
            else if (player.getHandValue() > 21) {
                endPlayerTurn(player, "Player " + (playerIndex + 1) + " busts! $" + player.getAmountWagered() + " lost!");
            }
            // Five-Card Charlie Rule
            else if (player.getHandValue() < 21 && player.getCards().size() == 5) {
                endPlayerTurn(player, "Five Card Charlie! Player " + (playerIndex + 1) + " wins $" + player.getAmountWagered() + "!");
            }
        }

        // Method that is called when a player's turn ends
        private void endPlayerTurn(Player player, String message) {
            if (message != null) {
                JOptionPane.showMessageDialog(null, message);
            }

            currentPlayerTurn++;
            if (currentPlayerTurn == numPlayers) {
                hitButton.setEnabled(false);
                standButton.setEnabled(false);
                playDealerTurn();
            } else {
                currentPlayerTurnLabel.setText("Your turn, Player " + (currentPlayerTurn + 1));
            }
        }

        // Helper method that shows the results of all players
        private void determineGameOutcome() {

            StringBuilder results = new StringBuilder("Game Results:\n");

            for (int i = 0; i < numPlayers; i++) {
                Player player = players.get(i);
                results.append(determinePlayerOutcome(player)).append("\n");
            }

            balanceLabel.setText(formatAllPlayerBalances());
            JOptionPane.showMessageDialog(null, results.toString());
            nextGameButton.setEnabled(true);
        }

        // Helper method that determines the outcome of a singular player
        private String determinePlayerOutcome(Player player) {
            String outcome = "";
            int playerIndex = players.indexOf(player);

            if (player.getHandValue() > 21) {
                outcome = "Player " + (playerIndex + 1) + ": Busts, lost " + player.getAmountWagered();
            }
            // Five-Card Charlie Rule: player wins if they have 5 cards that have value < 21
            else if (player.getHandValue() < 21 && player.getCards().size() == 5) {
                player.addWinnings(player.getAmountWagered() * 2);
                outcome = "Player " + (playerIndex + 1) + ": Five Card Charlie, won " + player.getAmountWagered() * 2;
            }
            // Player's hand is > dealer's hand OR dealer busts
            else if ((player.getHandValue() <= 21 && (player.getHandValue() > dealer.getHandValue()))
                    || dealer.getHandValue() > 21) {
                player.addWinnings(player.getAmountWagered() * 2);
                outcome = "Player " + (playerIndex + 1) + ": WON, won " + player.getAmountWagered() * 2;
            }
            // Dealer's hand is > player's hand 
            else if ((dealer.getHandValue() <= 21 && (dealer.getHandValue() > player.getHandValue()))) {
                outcome = "Player " + (playerIndex + 1) + ": LOST, lost " + player.getAmountWagered();
            }
            // Player's hand is = dealer's hand
            else if (dealer.getHandValue() == player.getHandValue()) {
                player.addWinnings(player.getAmountWagered());
                outcome = "Player " + (playerIndex + 1) + ": TIE, made back " + player.getAmountWagered();
            }
            return outcome;
        }

        // Helper method that prompts the player how much they would like to wager
        private void askWager(Player player, int playerNumber) {
            String amount;
            boolean validInput = false;
            while (!validInput) {
                balanceLabel.setText(formatAllPlayerBalances());
                amount = JOptionPane.showInputDialog("Enter your wager amount, Player " + playerNumber + ":");
                try {
                    Double.parseDouble(amount);
                    if (player.wager(Double.parseDouble(amount))) {
                        validInput = true;
                        balanceLabel.setText(formatAllPlayerBalances());
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient balance.");
                    }
                } catch (Exception n) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Try again.");
                }

            }

        }

        // Helper method that updates the text containing the balance of all the players
        private String formatAllPlayerBalances() {
            // JLabel doesn't understand "\n"
            StringBuilder balances = new StringBuilder("<html>");

            for (int i = 0; i < numPlayers; i++) {
                balances.append("Player ").append(i + 1 ).append(" Balance: ").append(players.get(i).getBalance());
                balances.append("<br>");
            }
            balances.append("</html>");
            return balances.toString();
        }

        // Method that is called whenever every player's turn is exhausted
        private void playDealerTurn() {
            while (dealer.getHandValue() < 17) {
                dealerDrawCard();
            }

            determineGameOutcome();
        }

        public class HitButtonHandler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                Player currentPlayer = players.get(currentPlayerTurn);
                dealCard(currentPlayer);
            }

        }

        public class StandButtonHandler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                currentPlayerTurn++;
                // If all players have played their turns, now it's the dealer's turn
                if (currentPlayerTurn == numPlayers) {
                    playDealerTurn();
                } else {
                    currentPlayerTurnLabel.setText("Your turn, Player " + (currentPlayerTurn + 1));
                }
            }
        }

        public class NextGameButtonHandler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Player currentPlayer = players.get(currentPlayerIndex);

                // currentPlayer.resetHand();
                // dealer.resetHand();
                // dealer.resetDeck();

                // dealerPanel.removeAll();
                // playerCardsPanels.get(currentPlayerIndex).removeAll();
                // initializeDealerCards();

                // gamePanel.revalidate();
                // gamePanel.repaint();

                // hitButton.setEnabled(true);
                // standButton.setEnabled(true);

                // nextGameButton.setEnabled(false);

                // askWager(currentPlayer);
                // initializePlayerCards();
            }

        }

    }