package oliso.view;

import java.security.PublicKey;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Image;

public class HelpWindows {
     public void TwoPlayersWindow() {
    JFrame frame = new JFrame("Two players");
    frame.setSize(500, 500);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
    frame.setVisible(true);

    JLabel label = new JLabel("FOR 2 PLAYERS");
    label.setFont(new Font("Arial", Font.BOLD, 35));
    label.setForeground(Color.BLACK);
    label.setHorizontalAlignment(JLabel.CENTER); // Center the label

    JTextArea info = new JTextArea("Each player selects two colours that are opposite.\nPlayer 1 will play with player 1 and 3 pieces.\nPlayer 2 will play with player 2 and 4 pieces.\nYou can win with either of your colours.\nThe youngest player goes first, and you will take turns placing pieces (one per turn) into the playing area, alternating between your two colours.");
    info.setLineWrap(true);
    info.setWrapStyleWord(true);
    info.setEditable(false);
    info.setOpaque(false);
    info.setFont(new Font("Arial", Font.ITALIC, 20));
    info.setAlignmentX(Component.LEFT_ALIGNMENT);

    // Create a panel to hold the components
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
    panel.setBackground(Color.LIGHT_GRAY); // Set background color

    panel.add(label);
    panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some space between components
    panel.add(info);
    panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some space between components

    // Add the panel to the frame
    frame.add(panel);
}
    
    public void ThreePlayersWindow(){
        JFrame frame = new JFrame("Three or more players");
    frame.setSize(500, 500);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
    frame.setVisible(true);

    JLabel label = new JLabel("For 3 or more players");
    label.setFont(new Font("Arial", Font.BOLD, 35));
    label.setForeground(Color.BLACK);
    label.setHorizontalAlignment(JLabel.CENTER); // Center the label

    JTextArea info = new JTextArea("Each player selects a colour. If only three are playing, one colour sits the game out. The youngest player goes first and play proceeds clockwise. Take turns placing pieces (one per turn) into the playing area.");
    info.setLineWrap(true);
    info.setWrapStyleWord(true);
    info.setEditable(false);
    info.setOpaque(false);
    info.setFont(new Font("Arial", Font.ITALIC, 20));
    info.setAlignmentX(Component.LEFT_ALIGNMENT);

    // Create a panel to hold the components
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
    panel.setBackground(Color.LIGHT_GRAY); // Set background color

    panel.add(label);
    panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some space between components
    panel.add(info);
    panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some space between components

    // Add the panel to the frame
    frame.add(panel);
    }

    public void RulesWindow(){
        JFrame frame = new JFrame("How to play!");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        frame.setVisible(true);

        JLabel label = new JLabel("How to play!");
        label.setFont(new Font("Arial", Font.BOLD, 35));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER); // Center the label

        // Load the image
        ImageIcon imageIcon = new ImageIcon("src/oliso/resources/rules.png");
        Image image = imageIcon.getImage(); // Transform it
        Image newImg = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH); // Scale it the smooth way
        imageIcon = new ImageIcon(newImg);  // Transform it back

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        panel.setBackground(Color.LIGHT_GRAY); // Set background color

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some space between components
        panel.add(imageLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some space between components

        // Add the panel to the frame
        frame.add(panel);
    }
    public void badMoveWindow(){
        JFrame frame = new JFrame("Bad move!");
        frame.setSize(200, 100);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        frame.setVisible(true);
    }
}