package com.pipegame;

import java.awt.Rectangle;
import javax.swing.JLabel;


public class HorizontalDrawing extends DrawingTask {

   private final PipeDirection direction;

   private final JLabel label;

   private StartingPanel startingPanel;

   private boolean startFromCenter;

   public HorizontalDrawing(PipeDirection direction, JLabel label) {
      this.direction = direction;
      this.label = label;
   }

   public HorizontalDrawing(PipeDirection direction, JLabel label,
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
      if (direction == PipeDirection.LEFT_TO_RIGHT) {
         int currentX = labelX - WaterFlowLength.width;
         if (startFromCenter) {
            currentX = currentX + (labelWidth / 2);
         }
         while (currentX < (labelX + labelWidth)) {
            int targetX = currentX + WaterFlowLength.width;
            int targetY = (labelY + (labelHeight / 2))
                  - (WaterFlowLength.height / 2);
            WaterFlow next = new WaterFlow();
            startingPanel.addWaterFlow(next);
            next.setBounds(targetX, targetY, WaterFlowLength.width,
                  WaterFlowLength.height);
            next.setVisible(true);
            startingPanel.setComponentZOrder(next, 1);
            currentX = currentX + WaterFlowLength.width;
            sleep();
         }
      } else if (direction == PipeDirection.RIGHT_TO_LEFT) {
         int currentX = labelX + labelWidth + WaterFlowLength.width;
         if (startFromCenter) {
            currentX = currentX - (labelWidth / 2);
         }
         while (currentX > labelX) {
            int targetX = currentX - WaterFlowLength.width;
            int targetY = (labelY + (labelHeight / 2))
                  - (WaterFlowLength.height / 2);
            WaterFlow next = new WaterFlow();
            startingPanel.addWaterFlow(next);
            next.setBounds(targetX, targetY, WaterFlowLength.width,
                  WaterFlowLength.height);
            next.setVisible(true);
            startingPanel.setComponentZOrder(next, 1);
            currentX = currentX - WaterFlowLength.width;
            sleep();
         }
      } else {
         throw new RuntimeException("Invalid direction: " + direction);
      }
   }

   @Override
   public String toString() {
      return "HorizontalDrawing [direction=" + direction + ", label=" + label
            + ", desktopPane=" + startingPanel + ", startFromCenter="
            + startFromCenter + "]";
   }

}
