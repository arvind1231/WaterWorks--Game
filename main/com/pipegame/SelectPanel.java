package com.pipegame;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JPanel;


public class SelectPanel {

   
	 private static final Color WHITE = new Color(245, 245, 245);
	   
	   private static final Color GOLD = new Color(184, 134, 11);

   private final JPanel jPanel;

   private final boolean enabled = true;

   private Color currentColor;

   public SelectPanel(JPanel jPanel) {
      this.jPanel = jPanel;
      currentColor = WHITE;
   }

   public void setPosition(Point point) {
      jPanel.setLocation(point);
   }

   public void setPosition(int x, int y) {
      jPanel.setLocation(x, y);
   }

   public void setToGreen() {
      if (currentColor != WHITE) {
         currentColor = WHITE;
         jPanel.setBackground(WHITE);
         jPanel.repaint();
      }
   }

   public void setToRed() {
      if (currentColor != GOLD) {
         currentColor = GOLD;
         jPanel.setBackground(GOLD);
         jPanel.repaint();
      }
   }

   public boolean isEnabled() {
      return enabled;
   }

   /**
    * Shows the panel.
    */
   public void show() {
      jPanel.setVisible(true);
   }

   /**
    * Hides the panel.
    */
   public void hide() {
      jPanel.setVisible(false);
   }

   public Point getPosition() {
      return jPanel.getLocation();
   }

}
