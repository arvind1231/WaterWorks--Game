package com.pipegame;

import javax.swing.JLabel;

public class PipesRemaining {

   private static final String HEAD = "Remaining: ";

   private final JLabel remainingPipesLabel;

   public PipesRemaining(JLabel remainingPipesLabel) {
      this.remainingPipesLabel = remainingPipesLabel;
   }

   /**
    * Removes the text from the label.
    */
   public void clear() {
      remainingPipesLabel.setText(null);
   }

   public void setRemaining(int count) {
      remainingPipesLabel.setText(HEAD + count);
   }
}
