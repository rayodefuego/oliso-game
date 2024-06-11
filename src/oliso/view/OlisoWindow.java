package oliso.view;
import javax.swing.*;

import oliso.model.Game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import oliso.resources.Fonts;
import oliso.model.BoardChecker;

public class OlisoWindow {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menu, help;
    private JMenuItem menuItem, helpItem;
    private String currentPlayer; 
    private Game game;
    private JLayeredPane layeredPane; // Declare layeredPane here
    private Font robotoFont;
    private JButton powerup1, powerup2;

    /**
     * Constructor for the OlisoWindow class.
     * Initializes the game with 4 players, loads the Roboto font, and sets up the main window frame.
     * Creates the game board, sets the initial player, creates the menu, and the power-ups panel.
     * Finally, packs the frame to fit the preferred sizes of its components.
     * @param none
     * @return none
     */
    
    public OlisoWindow(){
        Fonts fonts = new Fonts();
        game = new Game(4); // Initialize the game with 4 players
        fonts.loadRobotoFont();
        createFrame();
        createBoard(); // Initialize layeredPane before setting the current player
        setCurrentPlayer("Player 1"); // Set the initial player after frame is created
        createMenu();
        createPowerupsPanel();
        frame.pack();
    }   


    /**
     * Creates and sets up the main application frame.
     * The frame is titled "Oliso", has a default close operation to exit the application,
     * and is sized to 800x600 pixels. It uses a GridBagLayout for centering components,
     * sets a background color, and makes the frame visible. The frame is also centered
     * on the screen.
     */
    private void createFrame(){
        frame = new JFrame("Oliso");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridBagLayout()); // Use GridBagLayout for centering
        frame.getContentPane().setBackground(Color.decode("#ccc0b4")); // Set background color
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
      /**
     * Creates and sets up the menu bar.
     * The menu bar is titled "Game" and "Help".
     * The menu items in game are "New game".
     * The menu items in help are "How to play?"
     * They are created using the HelpWindows class
     */
    private void createMenu(){
        menuBar = new JMenuBar();
        menu = new JMenu("Game");
        help = new JMenu("Help");
        helpItem = new JMenuItem("How to play?");
        helpItem.setFont(robotoFont);
        helpItem.addActionListener(e -> {
            HelpWindows HTP = new HelpWindows();
            HTP.HowToPlayWindow();
        });
        menuItem = new JMenuItem("New game");
        menuItem.setFont(robotoFont);
        menuItem.addActionListener(e -> {
            newGame();
        });
        menu.add(menuItem);
        menuBar.add(menu);
        menuBar.add(help);
        help.add(helpItem);
        frame.setJMenuBar(menuBar);
    }
  
/*  
 * This class is responsible for handling the color toggle action listener.
 * It is used to toggle the color of the circular button when the user clicks on it.
 * 
 */
    private Color getColorForValue(int value, int size) {
        int player;
        switch (size) {
            case 2:
                player = (value / 100) % 10;
                break;
            case 1:
                player = (value / 10) % 10;
                break;
            case 0:
                player = value % 10;
                break;
            default:
                return Color.GRAY;
        }
        return getColorForPlayer(player);
    }
    /*
     * This class is responsible for handling the color toggle action listener.
     * It is used to toggle the color of the circular button when the user clicks on it.
     * 
     */
    private Color getColorForPlayer(int player) {
        switch (player) {
            case 2:
                return Color.BLUE;
            case 3:
                return Color.RED;
            case 5:
                return Color.GREEN;
            case 7:
                return Color.YELLOW;
            default:
                return Color.GRAY; // Default color for invalid values
        }
    }
/*  
 * This class is responsible for creating the board.
 * It is used to create the board and creating the circular buttons.
 * 
 */
    public void createBoard(){
        layeredPane = new JLayeredPane(); // Initialize layeredPane here
        layeredPane.setPreferredSize(new Dimension(300, 300)); // Adjust size for better centering
        layeredPane.setBackground(Color.decode("#ccc0b4")); // Set background color
        layeredPane.setOpaque(true); // Make sure the background color is visible
        int buttonSize = 100;
        int offset = 10;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int x = j * buttonSize;
                int y = i * buttonSize;
                
                Buttons buttons = new Buttons(); // Create an instance of Buttons
                Buttons.CircularButton button1 = buttons.new CircularButton(); // Use the instance to create CircularButton
                button1.setBounds(x, y, buttonSize, buttonSize);
                button1.setGridPosition(j, i);
                button1.addActionListener(new ColorToggleActionListener(this));
                button1.setBorderPainted(false);
                button1.setBorder(BorderFactory.createEmptyBorder());
                layeredPane.add(button1, JLayeredPane.DEFAULT_LAYER);

                
                Buttons buttons2 = new Buttons(); // Create an instance of Buttons
                Buttons.CircularButton button2 = buttons2.new CircularButton(); // Use the instance to create CircularButton
                button2.setBounds(x + offset, y + offset, buttonSize - 2 * offset, buttonSize - 2 * offset);
                button2.setGridPosition(j, i);
                button2.addActionListener(new ColorToggleActionListener(this));
                button2.setBorderPainted(false);
                layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);
                
                Buttons buttons3 = new Buttons(); // Create an instance of Buttons
                Buttons.CircularButton button3 = buttons3.new CircularButton(); // Use the instance to create CircularButton
                button3.setBounds(x + 2 * offset, y + 2 * offset, buttonSize - 4 * offset, buttonSize - 4 * offset);
                button3.setGridPosition(j, i);
                button3.addActionListener(new ColorToggleActionListener(this));
                button3.setBorderPainted(false);
                layeredPane.add(button3, JLayeredPane.MODAL_LAYER);
            }
        }        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding
        frame.add(layeredPane, gbc);
    }
    
    /*
     * This class is responsible for showing the player.
     * It is used to show the player and the current player.
     * 
     */
    public void showPlayer(String player){
        JLabel playerLabel = new JLabel("Now playing: " + player);
        playerLabel.setFont(robotoFont);
        playerLabel.setSize(200, 20);
        playerLabel.setLocation(400, 20); // Set x and y coordinates as needed
        playerLabel.setForeground(Color.BLACK); // Optional: Set text color for better visibility
        layeredPane.add(playerLabel, JLayeredPane.POPUP_LAYER);
         // Add to a higher layer
    }


    private void createPowerupsPanel() {
        JPanel powerupsPanel = new JPanel();
        powerupsPanel.setLayout(new BoxLayout(powerupsPanel, BoxLayout.Y_AXIS));
        powerupsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel powerupsLabel = new JLabel("Powerups");
        powerupsLabel.setFont(robotoFont);
        powerupsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        powerupsPanel.add(powerupsLabel);

        powerup1 = new JButton("Remove Piece");
        powerup1.setFont(robotoFont);
        powerup1.setAlignmentX(Component.CENTER_ALIGNMENT);
        powerup1.addActionListener(e -> useRemovePiecePowerup());
        powerupsPanel.add(powerup1);

        powerup2 = new JButton("Change Piece");
        powerup2.setFont(robotoFont);
        powerup2.setAlignmentX(Component.CENTER_ALIGNMENT);
        powerup2.addActionListener(e -> useChangePiecePowerup());
        powerupsPanel.add(powerup2);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding
        frame.add(powerupsPanel, gbc);
    }

    /*  
     * This class is responsible for showing the win window.
     * It is used to show the win window and the player who won.
     * 
     */
    
    private void winDOW(String playerName) {
        JFrame frame = new JFrame("Win");
        JLabel label = new JLabel(playerName + " wins!", SwingConstants.CENTER);
        label.setFont(robotoFont);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(label, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 100);
        frame.setLocationRelativeTo(null);
        
        // Set the background color of the content pane
        frame.getContentPane().setBackground(Color.decode("#ccc0b4"));
        
        frame.setVisible(true);
    }

    /**
     * Places a piece on the game board at the specified coordinates.
     * Prompts the current player to select the size of the piece to place.
     * If the selection is canceled, the turn is left waiting.
     * If the piece is successfully placed, the board is updated and the win condition is checked.
     * If a player wins, a win dialog is shown. Otherwise, the turn is passed to the next player.
     * If the move is invalid, an error message is shown.
     *
     * @param x the x-coordinate on the board where the piece is to be placed
     * @param y the y-coordinate on the board where the piece is to be placed
     */

    public void placePiece(int x, int y) {
        int size = selectPieceSize();
        if (size == -1) {
            System.out.println("Selection canceled. Turn is waiting.");
            return; // Exit the method to leave the turn waiting
        }
        
        if (game.addPiece(size, y, x)) { // Note the swapped x and y here
            System.out.println("Piece placed at (" + x + ", " + y + ") by " + currentPlayer);
            updateBoard();
            
            // Check for win condition
            if (BoardChecker.checkForWin(game.getBoard())) {
                winDOW(currentPlayer);
            } else {
                game.nextTurn();
                setCurrentPlayer(game.getCurrentPlayer().getName());
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid move. Try again.");
        }
    }

/*  
 * This class is responsible for selecting the size of the piece to place.
 * It is used to select the size of the piece to place and the player to remove the piece to.
 * 
 */
    private int selectPieceSize() {
        String[] options = {"Small", "Medium", "Big", "Cancel"};
        int choice = JOptionPane.showOptionDialog(frame, 
                currentPlayer + ", select piece size", // Include the current player's name in the message
                "Piece Size",
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                options, 
                options[0]);
        
        if (choice == 3) { // 3 corresponds to "Cancel"
            return -1; // Indicate that the selection was canceled
        }
        
        return choice; // 0 for Small, 1 for Medium, 2 for Big
    }
    
/*  
 * This class is responsible for updating the board.
 * It is used to update the board and the buttons.
 * 
 */
    private void updateBoard() {
        int[][] boardState = game.getBoard();
        System.out.println("Board state:");
        for (int[] row : boardState) {
            System.out.println(Arrays.toString(row));
        }
        Component[] components = layeredPane.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof Buttons.CircularButton) {
                Buttons.CircularButton button = (Buttons.CircularButton) components[i];
                int x = button.getGridX();
                int y = button.getGridY();
                int value = boardState[y][x]; // Note the swapped x and y here
                button.setColors(getColorForValue(value, 2), getColorForValue(value, 1), getColorForValue(value, 0));
                button.repaint();
            }
        }
    }

    public void newGame(){
            game = new Game(4);
            frame.remove(layeredPane); // Remove the old layeredPane
            createBoard();
            setCurrentPlayer("Player 1");
            updateBoard();
            frame.revalidate(); // Revalidate the frame to update the layout
            frame.repaint();
            updatePowerupButtons(); // Update powerup buttons
    }

    private void useRemovePiecePowerup() {
        if (game.canUsePowerup(0)) {
            try {
                int x = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter x-coordinate to remove piece (0-2):"));
                int y = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter y-coordinate to remove piece (0-2):"));
                int size = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter size of piece to remove (0 for Small, 1 for Medium, 2 for Big):"));

                if (x < 0 || x > 2 || y < 0 || y > 2 || size < 0 || size > 2) {
                    JOptionPane.showMessageDialog(frame, "Invalid coordinates or size. Please enter values between 0 and 2.");
                    return;
                }

                if (game.removePiece(size, x, y)) {
                    game.usePowerup(0);
                    updateBoard();
                    updatePowerupButtons();
                    game.nextTurn();
                    setCurrentPlayer(game.getCurrentPlayer().getName());
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid move. Try again.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter numeric values.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Powerup already used.");
        }
    }

    private void useChangePiecePowerup() {
        if (game.canUsePowerup(1)) {
            if (game.changePiece(game.getPlayerTurn())) {
                game.usePowerup(1);
                updateBoard();
                updatePowerupButtons();
                game.nextTurn();
                setCurrentPlayer(game.getCurrentPlayer().getName());
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid move. Try again.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Powerup already used.");
        }
    }

    private void updatePowerupButtons() {
        powerup1.setEnabled(game.canUsePowerup(0));
        powerup2.setEnabled(game.canUsePowerup(1));
    }

    //getters and setters
    public void setCurrentPlayer(String playerName) {
        this.currentPlayer = playerName;
        showPlayer(playerName);
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }
    public static void main (String [] args){
        new OlisoWindow();
    }

    // ACTION LISTENERS 
     /*
    * This class is responsible for handling the color toggle action listener.
    * It is used to toggle the color of the circular button when the user clicks on it.
    * 
    */
    class ColorToggleActionListener implements ActionListener {
        private OlisoWindow olisoWindow;

        public ColorToggleActionListener(OlisoWindow olisoWindow) {
            this.olisoWindow = olisoWindow;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Buttons.CircularButton button = (Buttons.CircularButton) e.getSource();
            int x = button.getGridX();
            int y = button.getGridY();
            olisoWindow.placePiece(x, y);
        }
    }
    
}
