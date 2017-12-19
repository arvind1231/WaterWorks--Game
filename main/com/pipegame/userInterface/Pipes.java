package com.pipegame.userInterface;

import java.util.ArrayList;
import java.util.List;
import com.pipegame.Pipeline;
import com.pipegame.SquarePosition.Entry;
import com.pipegame.SquarePosition.Exit;


public enum Pipes {
   VERTICAL(25),
   HORIZONTAL(25),
   TEE(9),
   CROSS(8),
   ELBOW(10);

   private final int chance;

   Pipes(int chance) {
      this.chance = chance;
   }

   public int getChance() {
      return chance;
   }

   public static List<Integer> getChancesList() {
      List<Integer> chances = new ArrayList<Integer>();
      for (Pipes pipeType : Pipes.values()) {
         chances.add(pipeType.getChance());
      }
      return chances;
   }

   public static List<Pipeline> getAllPipes() {
      List<Pipeline> result = new ArrayList<Pipeline>();
      result.add(new CPipe());
      result.add(new TPipe());
      result.add(new HPipe());
      result.add(new VPipe());

      EPipe leftDown = new EPipe();
      leftDown.setAngle(Entry.FROM_LEFT, Exit.TO_DOWN);
      result.add(leftDown);

      EPipe downRight = new EPipe();
      downRight.setAngle(Entry.FROM_DOWN, Exit.TO_RIGHT);
      result.add(downRight);

      EPipe rightUp = new EPipe();
      rightUp.setAngle(Entry.FROM_RIGHT, Exit.TO_UP);
      result.add(rightUp);

      EPipe upLeft = new EPipe();
      upLeft.setAngle(Entry.FROM_UP, Exit.TO_LEFT);
      result.add(upLeft);

      return result;
   }
}
