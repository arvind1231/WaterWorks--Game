package com.pipegame;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameWon {

   private static final String PLAYER_SCORE = "You WIN ! ";

   private static final String MAX_SCORE = "YOU WIN ! ";

   private final JPanel panel;

   private final JLabel playerScore;

   private final JLabel maxScore;

   public GameWon(JPanel panel, JLabel playerScore, JLabel maxScore) {
      this.panel = panel;
      this.playerScore = playerScore;
      this.maxScore = maxScore;
   }

   public void setPlayerScore(String score) {
      playerScore.setText(PLAYER_SCORE + score);
   }

   public void setMaxScore(String score) {
//      maxScore.setText(MAX_SCORE + score);
      maxScore.setText(MAX_SCORE);
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
