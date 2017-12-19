package com.pipegame;

import java.awt.Rectangle;
import javax.swing.JLabel;

public class VerticalDrawing extends DrawingTask {

   private final PipeDirection direction;

   private final JLabel label;

   private StartingPanel startingPanel;

   private boolean startFromCenter;

   public VerticalDrawing(PipeDirection direction, JLabel label) {
      this.direction = direction;
      this.label = label;
   }

   public VerticalDrawing(PipeDirection direction, JLabel label,
                          boolean startFromCenter) {
      this.direction = direction;
      this.label = label;
      this.startFromCenter = startFromCenter;
   }

   @Override
   public void setStartingPanel(StartingPanel desktopPane) {
      this.startingPanel = desktopPane;
   }

   @Override
   public void run() {
      Rectangle bounds = label.getBounds();
      int labelX = bounds.x;
      int labelY = bounds.y;
      int labelWidth = bounds.width;
      int labelHeight = bounds.height;
      if (direction == PipeDirection.UP_TO_DOWN) {
         upToDown(labelX, labelY, labelWidth, labelHeight);
      } else if (direction == PipeDirection.DOWN_TO_UP) {
         downToUp(labelX, labelY, labelWidth, labelHeight);
      } else {
         throw new RuntimeException("Invalid direction: " + direction);
      }
   }

   private void upToDown(int labelX, int labelY, int labelWidth, int labelHeight) {
      int currentY = labelY - WaterFlowLength.width;
      if (startFromCenter) {
         currentY = currentY + (labelHeight / 2);
      }
      while (currentY < (labelY + labelWidth)) {
         int targetX = (labelX + (labelWidth / 2))
               - (WaterFlowLength.height / 2);
         int targetY = currentY + WaterFlowLength.height;
         WaterFlow next = new WaterFlow();
         next.setToVertical();
         startingPanel.addWaterFlow(next);
         next.setBounds(targetX, targetY, WaterFlowLength.height,
               WaterFlowLength.width);
         next.setVisible(true);
         startingPanel.setComponentZOrder(next, 1);
         currentY = currentY + WaterFlowLength.width;
         next.repaint();
         sleep();
      }
   }

   private void downToUp(int labelX, int labelY, int labelWidth, int labelHeight) {
      int currentY = labelY + labelHeight + WaterFlowLength.width;
      if (startFromCenter) {
         currentY = currentY - (labelHeight / 2);
      }
      while (currentY > labelY) {
         int targetX = (labelX + (labelWidth / 2))
               - (WaterFlowLength.height / 2);
         int targetY = currentY - WaterFlowLength.height;
         WaterFlow next = new WaterFlow();
         next.setToVertical();
         startingPanel.addWaterFlow(next);
         next.setBounds(targetX, targetY, WaterFlowLength.height,
               WaterFlowLength.width);
         next.setVisible(true);
         startingPanel.setComponentZOrder(next, 1);
         currentY = currentY - WaterFlowLength.width;
         next.repaint();
         sleep();
      }
   }

   @Override
   public String toString() {
      return "VerticalDrawing [direction=" + direction + ", label=" + label
            + ", desktopPane=" + startingPanel + ", startFromCenter="
            + startFromCenter + "]";
   }

}
