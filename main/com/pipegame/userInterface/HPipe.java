package com.pipegame.userInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.pipegame.DrawingTask;
import com.pipegame.HorizontalDrawing;
import com.pipegame.PipeDirection;
import com.pipegame.Pipeline;
import com.pipegame.SquarePosition.Entry;
import com.pipegame.SquarePosition.Exit;
import com.pipegame.images.CallImage;

public class HPipe extends Pipeline {

   private static final String IMAGE = "hp.png";

   private enum Drawings {
      FROM_LEFT {

         @Override
         public DrawingTask getDrawingTask(HPipe thisPipe) {
            HorizontalDrawing task = new HorizontalDrawing(
                  PipeDirection.LEFT_TO_RIGHT, thisPipe);
            return task;
         }

      }
      ,
      FROM_RIGHT {

         @Override
         public DrawingTask getDrawingTask(HPipe thisPipe) {
            HorizontalDrawing task = new HorizontalDrawing(
                  PipeDirection.RIGHT_TO_LEFT, thisPipe);
            return task;
         }

      };

      public abstract DrawingTask getDrawingTask(HPipe thisPipe);
   }

   private static final Map<Entry, Exit> EXITS = new TreeMap<Entry, Exit>();
   static {
      EXITS.put(Entry.FROM_LEFT, Exit.TO_RIGHT);
      EXITS.put(Entry.FROM_RIGHT, Exit.TO_LEFT);
   }

   /**
    *
    */
   private static final long serialVersionUID = 6191642309024085652L;

   public HPipe() {
      super(CallImage.getImage(IMAGE));
   }

   @Override
   public List<Exit> enterPipe(Entry from) {
      Exit exit = EXITS.get(from);
      List<Exit> exits = new LinkedList<Exit>();
      if (exit != null) {
         exits.add(exit);
      } else {
         exits.add(Exit.FAIL);
      }
      return exits;
   }

   @Override
   public Pipeline clone() {
      return new HPipe();
   }

   @Override
   public DrawingTask getDrawWaterflowTask(Entry from) {
      Drawings drawing = Drawings.valueOf(from.toString());
      DrawingTask drawingTask = drawing.getDrawingTask(this);
      return drawingTask;
   }

}
