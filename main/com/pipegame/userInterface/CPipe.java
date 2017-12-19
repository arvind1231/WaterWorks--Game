package com.pipegame.userInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.pipegame.SquarePosition;
import com.pipegame.DrawingTask;
import com.pipegame.HorizontalDrawing;
import com.pipegame.PipeDirection;
import com.pipegame.Pipeline;
import com.pipegame.VerticalDrawing;
import com.pipegame.SquarePosition.Entry;
import com.pipegame.SquarePosition.Exit;
import com.pipegame.images.CallImage;

public class CPipe extends Pipeline {

//   private static final String IMAGE = "cross_pipe.png";
	private static final String IMAGE = "cp.png";

   private enum Drawings {
      FROM_LEFT {

         @Override
         public DrawingTask getDrawingTask(CPipe thisPipe) {
            return new HorizontalDrawing(PipeDirection.LEFT_TO_RIGHT, thisPipe);
         }

      },
      FROM_RIGHT {

         @Override
         public DrawingTask getDrawingTask(CPipe thisPipe) {
            return new HorizontalDrawing(PipeDirection.RIGHT_TO_LEFT, thisPipe);
         }

      },
      FROM_UP {

         @Override
         public DrawingTask getDrawingTask(CPipe thisPipe) {
            return new VerticalDrawing(PipeDirection.UP_TO_DOWN, thisPipe);
         }

      },
      FROM_DOWN {

         @Override
         public DrawingTask getDrawingTask(CPipe thisPipe) {
            return new VerticalDrawing(PipeDirection.DOWN_TO_UP, thisPipe);
         }

      };

      public abstract DrawingTask getDrawingTask(CPipe thisPipe);
   }

   private static final Map<Entry, Exit> EXITS = new TreeMap<Entry, Exit>();
   static {
      EXITS.put(Entry.FROM_DOWN, Exit.TO_UP);
      EXITS.put(Entry.FROM_UP, Exit.TO_DOWN);
      EXITS.put(Entry.FROM_LEFT, Exit.TO_RIGHT);
      EXITS.put(Entry.FROM_RIGHT, Exit.TO_LEFT);
   }

   /**
    *
    */
   private static final long serialVersionUID = 6191642309024085652L;

   public CPipe() {
      super(CallImage.getImage(IMAGE));
   }

   @Override
   public List<SquarePosition.Exit> enterPipe(SquarePosition.Entry from) {
      List<SquarePosition.Exit> result = new LinkedList<SquarePosition.Exit>();
      result.add(EXITS.get(from));
      return result;
   }

   @Override
   public Pipeline clone() {
      return new CPipe();
   }

   @Override
   public DrawingTask getDrawWaterflowTask(Entry from) {
      Drawings drawingDirection = Drawings.valueOf(from.toString());
      DrawingTask drawingTask = drawingDirection.getDrawingTask(this);
      return drawingTask;
   }

}
