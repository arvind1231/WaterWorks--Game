package com.pipegame;



public class BlankDrawingTask extends DrawingTask {

   @Override
   public void run() {
      try {
         Thread.sleep(1000);
      } catch (InterruptedException e) {
         return;
      }
   }

   @Override
   public String toString() {
      return "EmptyTask";
   }

   @Override
   public void setStartingPanel(StartingPanel desktopPane) {

   }

}
