package com.pipegame;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.pipegame.SquarePosition.Entry;


public abstract class Pipeline extends JLabel implements Cloneable {

   private static final long serialVersionUID = -6251211421858915308L;

   public Pipeline(ImageIcon image) {
      super(image);
   }

   public abstract List<SquarePosition.Exit> enterPipe(SquarePosition.Entry from);

   public abstract DrawingTask getDrawWaterflowTask(Entry from);

   @Override
   public abstract Pipeline clone();

   @Override
   public String toString() {
      return getClass().getSimpleName();
   }

}
