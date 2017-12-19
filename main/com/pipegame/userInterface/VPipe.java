package com.pipegame.userInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.pipegame.DrawingTask;
import com.pipegame.PipeDirection;
import com.pipegame.Pipeline;
import com.pipegame.VerticalDrawing;
import com.pipegame.SquarePosition.Entry;
import com.pipegame.SquarePosition.Exit;
import com.pipegame.images.CallImage;

public class VPipe extends Pipeline {

	private static final String IMAGE = "vp.png";

   private static final Map<Entry, Exit> EXITS = new TreeMap<Entry, Exit>();
   static {
      EXITS.put(Entry.FROM_UP, Exit.TO_DOWN);
      EXITS.put(Entry.FROM_DOWN, Exit.TO_UP);
   }

   private enum Drawings {
      FROM_UP {

         @Override
         public DrawingTask getDrawingTask(VPipe thisPipe) {
            VerticalDrawing drawingTask = new VerticalDrawing(
                  PipeDirection.UP_TO_DOWN, thisPipe);
            return drawingTask;
         }
      },
      FROM_DOWN {

         @Override
         public DrawingTask getDrawingTask(VPipe thisPipe) {
            VerticalDrawing drawingTask = new VerticalDrawing(
                  PipeDirection.DOWN_TO_UP, thisPipe);
            return drawingTask;
         }
      };

      public abstract DrawingTask getDrawingTask(VPipe thisPipe);
   }

   /**
    *
    */
   private static final long serialVersionUID = 6191642309024085652L;

   public VPipe() {
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
      return new VPipe();
   }

   @Override
   public DrawingTask getDrawWaterflowTask(Entry from) {
      Drawings drawing = Drawings.valueOf(from.toString());
      DrawingTask drawingTask = drawing.getDrawingTask(this);
      return drawingTask;
   }

}
