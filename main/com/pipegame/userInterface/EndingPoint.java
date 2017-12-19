package com.pipegame.userInterface;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.pipegame.DrawingTask;
import com.pipegame.BlankDrawingTask;
import com.pipegame.GenerateRandomUtil;
import com.pipegame.Pipeline;
import com.pipegame.SquarePosition.Entry;
import com.pipegame.SquarePosition.Exit;
import com.pipegame.images.CallImage;

public class EndingPoint extends Pipeline {

   private static final String IMAGE_LEFT = "epipe_left.png";

   private static final String IMAGE_DOWN = "epipe_down.png";

   private static final String IMAGE_RIGHT = "epipe_right.png";

   private static final String IMAGE_UP = "epipe_up.png";

   private static final List<String> IMAGES = Arrays.asList(new String[] {
         IMAGE_LEFT,
         IMAGE_DOWN,
         IMAGE_RIGHT,
         IMAGE_UP
   });

   private static final Map<EntryMatch, Exit> EXITS = new HashMap<EntryMatch, Exit>(
         4);
   static {
      EntryMatch left = new EntryMatch(IMAGE_LEFT, Entry.FROM_LEFT);
      EXITS.put(left, Exit.FINISH);
      EntryMatch down = new EntryMatch(IMAGE_DOWN, Entry.FROM_DOWN);
      EXITS.put(down, Exit.FINISH);
      EntryMatch right = new EntryMatch(IMAGE_RIGHT, Entry.FROM_RIGHT);
      EXITS.put(right, Exit.FINISH);
      EntryMatch up = new EntryMatch(IMAGE_UP, Entry.FROM_UP);
      EXITS.put(up, Exit.FINISH);
   }

   /**
    *
    */
   private static final long serialVersionUID = 1861356663589566005L;

   private String currentImage;

   public EndingPoint() {
      super(null);
      currentImage = GenerateRandomUtil.getRandomFromList(IMAGES);
      setIconToCurrentImage();
   }

   private void setIconToCurrentImage() {
      setIcon(CallImage.getImage(currentImage));
   }

   public Entry getEntry() {
      for (EntryMatch pair : EXITS.keySet()) {
         if (pair.imageName.equals(currentImage)) {
            return pair.from;
         }
      }
      return null;
   }

   public void setEntry(Entry entry) {
      for (EntryMatch pair : EXITS.keySet()) {
         if (pair.from == entry) {
            currentImage = pair.imageName;
            setIconToCurrentImage();
            return;
         }
      }
   }

   @Override
   public List<Exit> enterPipe(Entry from) {
      EntryMatch currentPair = new EntryMatch(currentImage, from);
      Exit exit = EXITS.get(currentPair);
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
      EndingPoint clone = new EndingPoint();
      clone.currentImage = this.currentImage;
      clone.setIconToCurrentImage();
      return clone;
   }

   @Override
   public DrawingTask getDrawWaterflowTask(Entry from) {
      return new BlankDrawingTask();
   }

}
