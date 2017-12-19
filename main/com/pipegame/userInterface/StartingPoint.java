package com.pipegame.userInterface;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.pipegame.DrawingTask;
import com.pipegame.GenerateRandomUtil;
import com.pipegame.HorizontalDrawing;
import com.pipegame.PipeDirection;
import com.pipegame.Pipeline;
import com.pipegame.VerticalDrawing;
import com.pipegame.SquarePosition.Entry;
import com.pipegame.SquarePosition.Exit;
import com.pipegame.images.CallImage;


public class StartingPoint extends Pipeline {

   private static final String IMAGE_RIGHT = "spipe_right.png";

   private static final String IMAGE_DOWN = "spipe_down.png";

   private static final String IMAGE_LEFT = "spipe_left.png";

   private static final String IMAGE_UP = "spipe_up.png";

   private static final List<String> IMAGES = Arrays.asList(new String[] {
         IMAGE_UP,
         IMAGE_LEFT,
         IMAGE_DOWN,
         IMAGE_RIGHT
   });

   private static final Map<String, Exit> EXITS = new HashMap<String, Exit>(4);
   static {
      EXITS.put(IMAGE_UP, Exit.TO_UP);
      EXITS.put(IMAGE_LEFT, Exit.TO_LEFT);
      EXITS.put(IMAGE_DOWN, Exit.TO_DOWN);
      EXITS.put(IMAGE_RIGHT, Exit.TO_RIGHT);
   }

   private enum Drawings {
      TO_UP {

         @Override
         public DrawingTask getDrawingTask(StartingPoint thisPipe) {
            VerticalDrawing task = new VerticalDrawing(
                  PipeDirection.DOWN_TO_UP, thisPipe, true);
            return task;
         }

      },
      TO_DOWN {

         @Override
         public DrawingTask getDrawingTask(StartingPoint thisPipe) {
            VerticalDrawing task = new VerticalDrawing(
                  PipeDirection.UP_TO_DOWN, thisPipe, true);
            return task;
         }

      },
      TO_LEFT {

         @Override
         public DrawingTask getDrawingTask(StartingPoint thisPipe) {
            HorizontalDrawing task = new HorizontalDrawing(
                  PipeDirection.RIGHT_TO_LEFT, thisPipe, true);
            return task;
         }

      },
      TO_RIGHT {

         @Override
         public DrawingTask getDrawingTask(StartingPoint thisPipe) {
            HorizontalDrawing task = new HorizontalDrawing(
                  PipeDirection.LEFT_TO_RIGHT, thisPipe, true);
            return task;
         }

      };

      public abstract DrawingTask getDrawingTask(StartingPoint thisPipe);
   }

   /**
    *
    */
   private static final long serialVersionUID = 1861356663589566005L;

   private String currentImage;

   public StartingPoint() {
      super(null);
      currentImage = GenerateRandomUtil.getRandomFromList(IMAGES);
      setIconToCurrentImage();
   }

   private void setIconToCurrentImage() {
      setIcon(CallImage.getImage(currentImage));
   }

   public Exit getExit() {
      return EXITS.get(currentImage);
   }

   public void setExit(Exit exit) {
      for (String image : IMAGES) {
         if (EXITS.get(image) == exit) {
            currentImage = image;
            setIconToCurrentImage();
            return;
         }
      }
   }

   @Override
   public List<Exit> enterPipe(Entry from) {
      List<Exit> exits = new LinkedList<Exit>();
      if (from == Entry.START) {
         Exit exit = EXITS.get(currentImage);
         exits.add(exit);
      } else {
         exits.add(Exit.FAIL);
      }
      return exits;
   }

   @Override
   public Pipeline clone() {
      StartingPoint clone = new StartingPoint();
      clone.currentImage = this.currentImage;
      clone.setIconToCurrentImage();
      return clone;
   }

   @Override
   public DrawingTask getDrawWaterflowTask(Entry from) {
      Exit exit = EXITS.get(currentImage);
      Drawings drawing = Drawings.valueOf(exit.toString());
      DrawingTask task = drawing.getDrawingTask(this);
      return task;
   }

}
