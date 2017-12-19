package com.pipegame.userInterface;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.pipegame.DrawingTask;
import com.pipegame.BlankDrawingTask;
import com.pipegame.GenerateRandomUtil;
import com.pipegame.PipeDirection;
import com.pipegame.PipeRotation;
import com.pipegame.Pipeline;
import com.pipegame.ElbowDrawing;
import com.pipegame.TurnPipe;
import com.pipegame.SquarePosition.Entry;
import com.pipegame.SquarePosition.Exit;
import com.pipegame.PipeDirection.Elbow;
import com.pipegame.images.CallImage;

public class EPipe extends Pipeline implements PipeRotation {



	   private static final String UP_LEFT = "eul.png";

	   private static final String LEFT_DOWN = "eld.png";

	   private static final String DOWN_RIGHT = "edr.png";

	   private static final String RIGHT_UP = "eru.png";
	
	
   private static final List<String> IMAGES = Arrays.asList(new String[] {
         UP_LEFT,
         LEFT_DOWN,
         DOWN_RIGHT,
         RIGHT_UP
   });

   private static final Map<String, TurnPipe> ROTATIONS = new HashMap<String, TurnPipe>();
   static {
      TurnPipe upLeftRoation = new TurnPipe(RIGHT_UP, LEFT_DOWN);
      ROTATIONS.put(UP_LEFT, upLeftRoation);
      TurnPipe leftDownRotation = new TurnPipe(UP_LEFT, DOWN_RIGHT);
      ROTATIONS.put(LEFT_DOWN, leftDownRotation);
      TurnPipe downRightRotation = new TurnPipe(LEFT_DOWN, RIGHT_UP);
      ROTATIONS.put(DOWN_RIGHT, downRightRotation);
      TurnPipe rightUpRotation = new TurnPipe(DOWN_RIGHT, UP_LEFT);
      ROTATIONS.put(RIGHT_UP, rightUpRotation);
   }

   private static final Map<EntryMatch, Exit> EXITS = new HashMap<EntryMatch, Exit>();
   static {
      EntryMatch leftToDown = new EntryMatch(LEFT_DOWN, Entry.FROM_LEFT);
      EXITS.put(leftToDown, Exit.TO_DOWN);
      EntryMatch downToLeft = new EntryMatch(LEFT_DOWN, Entry.FROM_DOWN);
      EXITS.put(downToLeft, Exit.TO_LEFT);
      EntryMatch downToRight = new EntryMatch(DOWN_RIGHT, Entry.FROM_DOWN);
      EXITS.put(downToRight, Exit.TO_RIGHT);
      EntryMatch rightToDown = new EntryMatch(DOWN_RIGHT, Entry.FROM_RIGHT);
      EXITS.put(rightToDown, Exit.TO_DOWN);
      EntryMatch rightToUp = new EntryMatch(RIGHT_UP, Entry.FROM_RIGHT);
      EXITS.put(rightToUp, Exit.TO_UP);
      EntryMatch upToRight = new EntryMatch(RIGHT_UP, Entry.FROM_UP);
      EXITS.put(upToRight, Exit.TO_RIGHT);
      EntryMatch upToLeft = new EntryMatch(UP_LEFT, Entry.FROM_UP);
      EXITS.put(upToLeft, Exit.TO_LEFT);
      EntryMatch leftToUp = new EntryMatch(UP_LEFT, Entry.FROM_LEFT);
      EXITS.put(leftToUp, Exit.TO_UP);
   }


   private static final long serialVersionUID = 6191642309024085652L;

   private String currentImage;

   public EPipe() {
      super(null);// must store the selected image
      this.currentImage = GenerateRandomUtil.getRandomFromList(IMAGES);
      setCurrentIcon();
   }

   private void setCurrentIcon() {
      setIcon(CallImage.getImage(currentImage));
   }

   @Override
   public void rotateImageClockwise() {
      TurnPipe rotation = ROTATIONS.get(currentImage);
      String clockwise = rotation.clockwiseImage;
      currentImage = clockwise;
      setCurrentIcon();
   }

   @Override
   public void rotateImageCounterclockwise() {
      TurnPipe rotation = ROTATIONS.get(currentImage);
      String counterClockwise = rotation.counterClockwiseImage;
      currentImage = counterClockwise;
      setCurrentIcon();
   }

   @Override
   public List<Exit> enterPipe(Entry from) {
      EntryMatch current = new EntryMatch(currentImage, from);
      Exit exit = EXITS.get(current);
      List<Exit> exits = new LinkedList<Exit>();
      if (exit != null) {
         exits.add(exit);
      } else {
         exits.add(Exit.FAIL);
      }
      return exits;
   }

   @Override
   public void setAngle(Entry entry, Exit exit) {
      for (EntryMatch pair : EXITS.keySet()) {
         if (pair.from == entry) {
            if (EXITS.get(pair) == exit) {
               currentImage = pair.imageName;
               setCurrentIcon();
               return;
            }
         }
      }
   }

   @Override
   public String toString() {
      return currentImage.replaceAll("\\.png", "");
   }

   @Override
   public Pipeline clone() {
      EPipe clone = new EPipe();
      clone.currentImage = this.currentImage;
      clone.setCurrentIcon();
      return clone;
   }

   @Override
   public DrawingTask getDrawWaterflowTask(Entry from) {
      EntryMatch current = new EntryMatch(currentImage, from);
      Exit exit = EXITS.get(current);
      if (exit == null) {
         return new BlankDrawingTask();
      }
      StringBuilder direction = new StringBuilder();
      String directionFrom = from.toString().split("_")[1];
      String directionTo = exit.toString().split("_")[1];
      direction.append(directionFrom);
      direction.append("_TO_");
      direction.append(directionTo);
      Elbow drawing = PipeDirection.Elbow.valueOf(direction.toString());
      if (drawing == null) {
         throw new RuntimeException("No such elbow " + directionFrom);
      }
      ElbowDrawing elbowDrawing = new ElbowDrawing(
            drawing,
            this);
      return elbowDrawing;
   }
}
