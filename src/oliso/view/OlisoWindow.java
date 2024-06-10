package oliso.view;
import javax.swing.*;

import oliso.model.Game;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.ImageIO;

public class OlisoWindow {
    private JFrame frame;
    private JPanel board;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private String currentPlayer; 

    public OlisoWindow(){
        createFrame();
        setCurrentPlayer("Player 1"); // Set the initial player after frame is created
        createBoard();
        createMenu();
        createPowerupsPanel();
        frame.pack();
    }

    private void createFrame(){
        frame = new JFrame("Oliso");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void createMenu(){
        menuBar = new JMenuBar();
        menu = new JMenu("Game");
        menuItem = new JMenuItem("New game");
        menuItem.addActionListener(e -> {
            System.out.println("New game");
        });
        menu.add(menuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }
   
    class ColorToggleActionListener implements ActionListener {
        private OlisoWindow olisoWindow;

        public ColorToggleActionListener(OlisoWindow olisoWindow) {
            this.olisoWindow = olisoWindow;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            button.setBackground(olisoWindow.getColorForPlayer(olisoWindow.getCurrentPlayer()));
            button.repaint(); // Force the button to repaint
        }
    }

    private Color getColorForPlayer(String playerName) {
        switch (playerName) {
            case "Player 1":
                return Color.BLUE;
            case "Player 2":
                return Color.RED;
            case "Player 3":
                return Color.GREEN;
            case "Player 4":
                return Color.YELLOW;
            default:
                return Color.GRAY; // Default color if player name doesn't match
        }
    }

    private void createBoard(){
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        layeredPane.setLocation(100, 100);
        layeredPane.setSize(800, 600);
        int buttonSize = 100;
        int offset = 10;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int x = j * buttonSize;
                int y = i * buttonSize;
                
                CircularButton button1 = new CircularButton();
                button1.setBounds(x, y, buttonSize, buttonSize);
                button1.addActionListener(new ColorToggleActionListener(this));
                layeredPane.add(button1, JLayeredPane.DEFAULT_LAYER);
                
                CircularButton button2 = new CircularButton();
                button2.setBounds(x + offset, y + offset, buttonSize - 2 * offset, buttonSize - 2 * offset);
                button2.addActionListener(new ColorToggleActionListener(this));
                layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);
                
                CircularButton button3 = new CircularButton();
                button3.setBounds(x + 2 * offset, y + 2 * offset, buttonSize - 4 * offset, buttonSize - 4 * offset);
                button3.addActionListener(new ColorToggleActionListener(this));
                layeredPane.add(button3, JLayeredPane.MODAL_LAYER);
            }
        }        
        frame.add(layeredPane);
        frame.pack();
        frame.setVisible(true);
    }
    

    public void showPlayer(String player){
        JLabel playerLabel = new JLabel("Now playing: " + player);
        playerLabel.setSize(100, 20);
        playerLabel.setLocation(90,90);
        frame.add(playerLabel, BorderLayout.SOUTH);
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

        frame.add(powerupsPanel, BorderLayout.EAST);
    }

    class CircularButton extends JButton {
        public CircularButton() {
            super();
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(Color.LIGHT_GRAY);
            } else {
                g.setColor(getBackground());
            }
            g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
            super.paintComponent(g);
        }
    
        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(getForeground());
            g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
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
