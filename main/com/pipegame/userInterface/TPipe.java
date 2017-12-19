package com.pipegame.userInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.pipegame.DrawingTask;
import com.pipegame.Pipeline;
import com.pipegame.SquarePosition.Entry;
import com.pipegame.SquarePosition.Exit;
import com.pipegame.images.CallImage;


public class TPipe extends Pipeline {

	  private static final String IMAGE = "tee.png";

   private static final Map<Entry, List<Exit>> EXITS = new TreeMap<Entry, List<Exit>>();
   static {
      List<Exit> exits = new LinkedList<Exit>();
      exits.add(Exit.TO_LEFT);
      exits.add(Exit.TO_RIGHT);
      EXITS.put(Entry.FROM_UP, exits);
   }

   private static final long serialVersionUID = 6191642309024085652L;

   public TPipe() {
      super(CallImage.getImage(IMAGE));
   }

   @Override
   public List<Exit> enterPipe(Entry from) {
      List<Exit> exit = EXITS.get(from);
      if (exit != null) {
         return exit;
      } else {
         exit = new LinkedList<Exit>();
         exit.add(Exit.FAIL);
         return exit;
      }
   }

   @Override
   public Pipeline clone() {
      return new TPipe();
   }

   @Override
   public DrawingTask getDrawWaterflowTask(Entry from) {
      return null;
   }

}
