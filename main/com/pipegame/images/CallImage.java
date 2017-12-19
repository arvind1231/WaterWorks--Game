package com.pipegame.images;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import com.pipegame.Main;


public class CallImage {

   private static final Class<Main> CLASSPATH = Main.class;

   private static final Map<String, ImageIcon> IMAGES = new HashMap<String, ImageIcon>();

   private static void loadImage(String fileName) {
      ImageIcon image = new ImageIcon(
            CLASSPATH.getResource("/com/pipegame/images/" + fileName));
      IMAGES.put(fileName, image);
   }


   public static ImageIcon getImage(String fileName) {
      if (!IMAGES.containsKey(fileName)) {
         loadImage(fileName);
      }
      ImageIcon imageIcon = IMAGES.get(fileName);
      return imageIcon;
   }

}
