package com.pipegame;



public abstract class DrawingTask implements
      Runnable {

   public abstract void setStartingPanel(StartingPanel desktopPane);

   protected void sleep() {
      try {
         Thread.sleep(GenerateRandomUtil.nextInt(15) + 4);
      } catch (InterruptedException e) {

      }
   }
}
