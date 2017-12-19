package com.pipegame;

import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;

import com.pipegame.images.CallImage;


public class GameEnvironment {

   private final JLabel jLabel;

   private static final List<String> STARTING_BACKGROUNDS = Arrays
         .asList(new String[] { "pipes_logo.jpg",
               "sewer_art.jpg",
               "nuclear_reactor_work.jpg",
               "sewer_reactor.jpg",
         });

   private static final String STARTING_BACKGROUND = GenerateRandomUtil
         .getRandomFromList(STARTING_BACKGROUNDS);

   private static final String GAME_BACKGROUND = "";

   public GameEnvironment(JLabel jLabel) {
      this.jLabel = jLabel;
   }

   public void setToStartingBackground() {
      jLabel.setIcon(CallImage.getImage(STARTING_BACKGROUND));
   }

   public void setToGameBackground() {
      jLabel.setIcon(CallImage.getImage(GAME_BACKGROUND));
   }

}
