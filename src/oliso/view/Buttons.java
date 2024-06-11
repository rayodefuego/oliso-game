package oliso.view;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Buttons {
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
    
}
