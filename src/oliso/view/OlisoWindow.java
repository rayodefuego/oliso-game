package oliso.view;
import javax.swing.*;

import oliso.model.Game;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


public class OlisoWindow {
    private JFrame frame;
    private JPanel board;
    private JMenuBar menuBar;
    private JMenu menu, help;
    private JMenuItem menuItem, helpItem;
    private String currentPlayer; 
    private Game game;
    private JLayeredPane layeredPane; // Declare layeredPane here

    public OlisoWindow(){
        game = new Game(4); // Initialize the game with 4 players
        createFrame();
        createBoard(); // Initialize layeredPane before setting the current player
        setCurrentPlayer("Player 1"); // Set the initial player after frame is created
        createMenu();
        createPowerupsPanel();
        frame.pack();
    }

    private void createFrame(){
        frame = new JFrame("Oliso");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridBagLayout()); // Use GridBagLayout for centering
        frame.getContentPane().setBackground(Color.decode("#ccc0b4")); // Set background color
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void createMenu(){
        menuBar = new JMenuBar();
        menu = new JMenu("Game");
        help = new JMenu("Help");
        helpItem = new JMenuItem("How to play?");
        helpItem.addActionListener(e -> {
            HowToPlay();
        });
        menuItem = new JMenuItem("New game");
        menuItem.addActionListener(e -> {
            System.out.println("New game");
        });
        menu.add(menuItem);
        menuBar.add(menu);
        menuBar.add(help);
        help.add(helpItem);
        frame.setJMenuBar(menuBar);
    }

    private void HowToPlay(){
        JFrame frame = new JFrame("How to play");
        JButton rules = new JButton("Rules");
        JButton twoPlayers = new JButton("Two players"); 
        JButton threePlayers = new JButton("Three or more players");
        JPanel helpPanel = new JPanel();
        
        
        rules.addActionListener(e -> {
            HelpWindows helpWindows = new HelpWindows();
            helpWindows.RulesWindow();
        });
        twoPlayers.addActionListener(e -> {
            HelpWindows helpWindows = new HelpWindows();
            helpWindows.TwoPlayersWindow();
        });
        threePlayers.addActionListener(e -> {
            HelpWindows helpWindows = new HelpWindows();
            helpWindows.ThreePlayersWindow();
        });

        helpPanel.setLayout(new GridLayout(2, 1));
        helpPanel.add(twoPlayers);
        helpPanel.add(threePlayers);
        helpPanel.add(rules);
        frame.add(helpPanel, BorderLayout.CENTER);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    
   
    class ColorToggleActionListener implements ActionListener {
        private OlisoWindow olisoWindow;

        public ColorToggleActionListener(OlisoWindow olisoWindow) {
            this.olisoWindow = olisoWindow;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CircularButton button = (CircularButton) e.getSource();
            int x = button.getGridX();
            int y = button.getGridY();
            olisoWindow.placePiece(x, y);
        }
    }

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
                
                CircularButton button1 = new CircularButton();
                button1.setBounds(x, y, buttonSize, buttonSize);
                button1.setGridPosition(j, i);
                button1.addActionListener(new ColorToggleActionListener(this));
                button1.setBorderPainted(false);
                button1.setBorder(BorderFactory.createEmptyBorder());
                layeredPane.add(button1, JLayeredPane.DEFAULT_LAYER);

                
                CircularButton button2 = new CircularButton();
                button2.setBounds(x + offset, y + offset, buttonSize - 2 * offset, buttonSize - 2 * offset);
                button2.setGridPosition(j, i);
                button2.addActionListener(new ColorToggleActionListener(this));
                button2.setBorderPainted(false);
                layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);
                
                CircularButton button3 = new CircularButton();
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
    

    public void showPlayer(String player){
        JLabel playerLabel = new JLabel("Now playing: " + player);
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
        powerupsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        powerupsPanel.add(powerupsLabel);

        JCheckBox option1 = new JCheckBox("Option1");
        option1.setAlignmentX(Component.CENTER_ALIGNMENT);
        powerupsPanel.add(option1);

        JCheckBox option2 = new JCheckBox("Option2");
        option2.setAlignmentX(Component.CENTER_ALIGNMENT);
        powerupsPanel.add(option2);

        JCheckBox option3 = new JCheckBox("Option3");
        option3.setAlignmentX(Component.CENTER_ALIGNMENT);
        powerupsPanel.add(option3);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding
        frame.add(powerupsPanel, gbc);
    }

    class CircularButton extends JButton {
        private int gridX;
        private int gridY;
        private Color bigPieceColor = Color.GRAY;
        private Color mediumPieceColor = Color.GRAY;
        private Color smallPieceColor = Color.GRAY;

        public CircularButton() {
            super();
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }

        public void setGridPosition(int x, int y) {
            this.gridX = x;
            this.gridY = y;
        }

        public int getGridX() {
            return gridX;
        }

        public int getGridY() {
            return gridY;
        }

        public void setColors(Color big, Color medium, Color small) {
            this.bigPieceColor = big;
            this.mediumPieceColor = medium;
            this.smallPieceColor = small;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            int width = getWidth();
            int height = getHeight();

            // Draw big piece
            g2d.setColor(bigPieceColor);
            g2d.fillOval(0, 0, width, height);

            // Draw medium piece
            int mediumSize = (int) (width * 0.67);
            int mediumOffset = (width - mediumSize) / 2;
            g2d.setColor(mediumPieceColor);
            g2d.fillOval(mediumOffset, mediumOffset, mediumSize, mediumSize);

            // Draw small piece
            int smallSize = (int) (width * 0.33);
            int smallOffset = (width - smallSize) / 2;
            g2d.setColor(smallPieceColor);
            g2d.fillOval(smallOffset, smallOffset, smallSize, smallSize);

            g2d.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(getForeground());
            int width = getWidth();
            int height = getHeight();

            // Draw border for big piece
             g.drawOval(0, 0, width, height);

            // Draw border for medium piece
            int mediumSize = (int) (width * 0.67);
            int mediumOffset = (width - mediumSize) / 2;
            g.drawOval(mediumOffset, mediumOffset, mediumSize, mediumSize);

            // Draw border for small piece
            int smallSize = (int) (width * 0.33);
            int smallOffset = (width - smallSize) / 2;
            g.drawOval(smallOffset, smallOffset, smallSize, smallSize);
        }

        @Override
        public boolean contains(int x, int y) {
            Ellipse2D shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
            return shape.contains(x, y);
        }
    }
    
    private void winDOW() {
        JFrame frame = new JFrame("Win");
        JLabel label = new JLabel("You win!", SwingConstants.CENTER);
        label.setForeground(Color.GREEN);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(label, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 100);
        frame.setLocationRelativeTo(null);
        
        // Set the background color of the content pane
        frame.getContentPane().setBackground(Color.BLACK);
        
        frame.setVisible(true);
    }

    public void placePiece(int x, int y) {
        int size = selectPieceSize();
        if (game.addPiece(size, y, x)) { // Note the swapped x and y here
            System.out.println("Piece placed at (" + x + ", " + y + ") by " + currentPlayer);
            updateBoard();
        }
        else {
            JOptionPane.showMessageDialog(frame, "Invalid move. Try again.");
        }
    }
    public void removePiece(int x, int y, int size) {
        game.removePieceToPlayer(size);
    }

    private int selectPieceSize() {
        String[] options = {"Small", "Medium", "Big"};
        int choice = JOptionPane.showOptionDialog(frame, "Select piece size", "Piece Size",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return choice; // 0 for Small, 1 for Medium, 2 for Big
    }

    private void updateBoard() {
        int[][] boardState = game.getBoard();
        System.out.println("Board state:");
        for (int[] row : boardState) {
            System.out.println(Arrays.toString(row));
        }
        Component[] components = layeredPane.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof CircularButton) {
                CircularButton button = (CircularButton) components[i];
                int x = button.getGridX();
                int y = button.getGridY();
                int value = boardState[y][x]; // Note the swapped x and y here
                button.setColors(getColorForValue(value, 2), getColorForValue(value, 1), getColorForValue(value, 0));
                button.repaint();
            }
        }
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
    
}
