package com.pipegame;

import javax.swing.JLabel;
import com.pipegame.images.CallImage;

public class WaterFlow extends JLabel {

   private static final String IMAGE_HORIZONTAL = "horizontal-water.jpg";

   private static final String IMAGE_VERTICAL = "vertical-water.jpg";

   private static final long serialVersionUID = -8578071561767107324L;

   public WaterFlow() {
      super(CallImage.getImage(IMAGE_HORIZONTAL));
   }

   public void setToHorizontal() {
      setIcon(CallImage.getImage(IMAGE_HORIZONTAL));
   }

   public void setToVertical() {
      setIcon(CallImage.getImage(IMAGE_VERTICAL));
   }

   @Override
   public String toString() {
      return "" + getLocation();
   }

}
