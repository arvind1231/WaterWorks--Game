package com.pipegame;

import java.util.ArrayList;
import java.util.List;
import com.pipegame.userInterface.CPipe;
import com.pipegame.userInterface.HPipe;
import com.pipegame.userInterface.Pipes;
import com.pipegame.userInterface.EPipe;
import com.pipegame.userInterface.TPipe;
import com.pipegame.userInterface.VPipe;

public class RandomPipe {

   private static final List<Integer> PREFIX_LIST = GenerateRandomUtil
         .createChancePrefixList(Pipes.getChancesList());


   public static Pipeline nextPipe() {
      int type = GenerateRandomUtil.getRandomIndexFromPrefixList(PREFIX_LIST);
      Pipes pipe = Pipes.values()[type];
      switch (pipe) {
      case VERTICAL:
         return new VPipe();
      case HORIZONTAL:
         return new HPipe();
      case TEE:
         return new TPipe();
      case ELBOW:
         return new EPipe();
      case CROSS:
         return new CPipe();
      default:
         throw new RuntimeException("Undefined type of pipe " + pipe);
      }
   }


   public static List<Pipeline> nextPipes(int count) {
      List<Pipeline> pipes = new ArrayList<Pipeline>(count);
      for (int i = 0; i < count; i++) {
         Pipeline randomPipe = nextPipe();
         pipes.add(randomPipe);
      }
      return pipes;
   }

}
