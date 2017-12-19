package com.pipegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateRandomUtil {

   private static final Random random = new Random();


   public static <T> T getRandomFromList(List<T> objects) {
      int length = objects.size();
      int randIndex = random.nextInt(length);
      return objects.get(randIndex);
   }

   public static int getRandomIncreasing(int max, int currentIteration,
         int maxIterations, int percentTail, int percentHead) {
      double currentIterationPercent = (double) currentIteration
            / maxIterations;
      int percent = (int) (currentIterationPercent * 100.0);
      int randomPercent;
      if (percent < percentTail) {
         randomPercent = nextInt(0, percent + percentHead);
      } else {
         randomPercent = nextInt(percent - percentTail, percent + percentHead);
      }
      double unrounded = (max * randomPercent) / 100;
      int result = (int) Math.round(unrounded);
      return result;
   }


   public static <T> void shuffleSublist(List<T> objects, int start, int end) {
      for (int index = start; index <= end; index++) {
         T current = objects.get(index);
         int randomIndex = nextInt(start, end);
         T randomObject = objects.get(randomIndex);
         objects.set(index, randomObject);
         objects.set(randomIndex, current);
      }
   }


   public static int nextInt(int max) {
      int randomInt = random.nextInt(max);
      return randomInt;
   }


   public static int nextInt(int min, int max) {
      int rand = random.nextInt((max + 1) - min);
      int result = rand + min;
      return result;
   }


   public static int pickOneRandomly(int first, int second) {
      boolean pickFirst = random.nextBoolean();
      if (pickFirst) {
         return first;
      } else {
         return second;
      }
   }


   public static boolean nextBoolean() {
      boolean randomBoolean = random.nextBoolean();
      return randomBoolean;
   }


   public static boolean tryChance(int chance) {
      int rand = random.nextInt(100) + 1;
      boolean success = chance <= rand;
      return success;
   }

   public static List<Integer> createChancePrefixList(List<Integer> chances) {
      List<Integer> result = new ArrayList<Integer>();
      Integer range = 0;
      for (Integer chance : chances) {
         range = range + chance;
         result.add(range);
      }
      return result;
   }


   public static int getRandomIndexFromPrefixList(List<Integer> prefixList) {
      int last = prefixList.size() - 1;
      int maxRange = prefixList.get(last);
      int randomInt = random.nextInt(maxRange) + 1;
      int index = -1;
      for (int i = 0; i < prefixList.size(); i++) {
         Integer range = prefixList.get(i);
         if (randomInt <= range) {
            index = i;
            break;
         }
      }
      return index;
   }
}
