package com.pipegame;

import java.awt.Component;
import javax.swing.JPanel;


public class GameOver {

   private final JPanel panel;

   public GameOver(JPanel panel) {
      this.panel = panel;
   }

   public Component getComponent() {
      return panel;
   }

   public void hide() {
      panel.setVisible(false);
   }

   public void show() {
      panel.setVisible(true);
   }
}
