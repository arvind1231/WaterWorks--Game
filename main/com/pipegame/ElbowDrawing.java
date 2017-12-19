package com.pipegame;

import java.awt.Rectangle;

import javax.swing.JLabel;

public class ElbowDrawing extends DrawingTask {

   private final PipeDirection.Elbow direction;

   private final JLabel label;

   private StartingPanel startingPanel;

   public ElbowDrawing(PipeDirection.Elbow direction, JLabel label) {
      this.direction = direction;
      this.label = label;
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
      String[] directions = direction.toString().split("_");
      toCenter(directions[0], labelX, labelY, labelWidth, labelHeight);
      centerTo(directions[2], labelX, labelY, labelWidth, labelHeight);
   }

   private void toCenter(String direction, int labelX, int labelY,
         int labelWidth,
         int labelHeight) {
      if (direction.equals("LEFT")) {
         leftToCenter(labelX, labelY, labelWidth, labelHeight);
      } else if (direction.equals("RIGHT")) {
         rightToCenter(labelX, labelY, labelWidth, labelHeight);
      } else if (direction.equals("UP")) {
         upToCenter(labelX, labelY, labelWidth, labelHeight);
      } else if (direction.equals("DOWN")) {
         downToCenter(labelX, labelY, labelWidth, labelHeight);
      }
   }

   private void centerTo(String direction, int labelX, int labelY,
         int labelWidth,
         int labelHeight) {
      if (direction.equals("LEFT")) {
         centerToLeft(labelX, labelY, labelWidth, labelHeight);
      } else if (direction.equals("RIGHT")) {
         centerToRight(labelX, labelY, labelWidth, labelHeight);
      } else if (direction.equals("UP")) {
         centerToUp(labelX, labelY, labelWidth, labelHeight);
      } else if (direction.equals("DOWN")) {
         centerToDown(labelX, labelY, labelWidth, labelHeight);
      }
   }

   private void rightToCenter(int labelX, int labelY, int labelWidth,
         int labelHeight) {
      int currentX = labelX + labelWidth + WaterFlowLength.width;
      int midX = (labelX + (labelWidth / 2)) - WaterFlowLength.width;
      int targetY = (labelY + (labelHeight / 2))
            - (WaterFlowLength.height / 2);
      while (currentX > midX) {
         int targetX = currentX - WaterFlowLength.width;
         createHorizontalWaterflow(targetX, targetY);
         currentX = currentX - WaterFlowLength.width;
         sleep();
      }
   }

   private void centerToRight(int labelX, int labelY, int labelWidth,
         int labelHeight) {
      int currentX = (labelX + (labelWidth / 2)) - WaterFlowLength.width;
      int right = labelX + labelWidth;
      int targetY = (labelY + (labelHeight / 2))
            - (WaterFlowLength.height / 2);
      while (currentX < right) {
         int targetX = currentX + WaterFlowLength.width;
         createHorizontalWaterflow(targetX, targetY);
         currentX = currentX + WaterFlowLength.width;
         sleep();
      }
   }

   private void centerToLeft(int labelX, int labelY, int labelWidth,
         int labelHeight) {
      int currentX = (labelX + (labelWidth / 2)) - WaterFlowLength.width;
      int left = labelX;
      int targetY = (labelY + (labelHeight / 2))
            - (WaterFlowLength.height / 2);
      while (currentX > left) {
         int targetX = currentX - WaterFlowLength.width;
         createHorizontalWaterflow(targetX, targetY);
         currentX = currentX - WaterFlowLength.width;
         sleep();
      }
   }

   private void createHorizontalWaterflow(int targetX, int targetY) {
      WaterFlow next = new WaterFlow();
      startingPanel.addWaterFlow(next);
      next.setBounds(targetX, targetY, WaterFlowLength.width,
            WaterFlowLength.height);
      next.setVisible(true);
      startingPanel.setComponentZOrder(next, 1);
   }

   private void leftToCenter(int labelX, int labelY, int labelWidth,
         int labelHeight) {
      int currentX = labelX - WaterFlowLength.width;
      int midX = labelX + (labelWidth / 2) + WaterFlowLength.width;
      int targetY = (labelY + (labelHeight / 2))
            - (WaterFlowLength.height / 2);
      while (currentX < midX) {
         int targetX = currentX + WaterFlowLength.width;
         createHorizontalWaterflow(targetX, targetY);
         currentX = currentX + WaterFlowLength.width;
         sleep();
      }
   }

   private void upToCenter(int labelX, int labelY, int labelWidth,
         int labelHeight) {
      int currentY = labelY - WaterFlowLength.width;
      int midY = (labelY + (labelHeight / 2)) - (2 * WaterFlowLength.width);
      int targetX = (labelX + (labelWidth / 2))
            - (WaterFlowLength.height / 2);
      while (currentY < midY) {
         int targetY = currentY + WaterFlowLength.height;
         createVerticalWaterflow(targetX, targetY);
         currentY = currentY + WaterFlowLength.width;
         sleep();
      }
   }

   private void createVerticalWaterflow(int targetX, int targetY) {
      WaterFlow next = new WaterFlow();
      next.setToVertical();
      startingPanel.addWaterFlow(next);
      next.setBounds(targetX, targetY, WaterFlowLength.height,
            WaterFlowLength.width);
      next.setVisible(true);
      startingPanel.setComponentZOrder(next, 1);
      next.repaint();
   }

   private void downToCenter(int labelX, int labelY, int labelWidth,
         int labelHeight) {
      int currentY = labelY + labelHeight + WaterFlowLength.width;
      int midY = labelY + (labelHeight / 2) + (2 * WaterFlowLength.width);
      int targetX = (labelX + (labelWidth / 2))
            - (WaterFlowLength.height / 2);
      while (currentY > midY) {
         int targetY = currentY - WaterFlowLength.height;
         createVerticalWaterflow(targetX, targetY);
         currentY = currentY - WaterFlowLength.width;
         sleep();
      }
   }

   private void centerToDown(int labelX, int labelY, int labelWidth,
         int labelHeight) {
      int currentY = (labelY + (labelHeight / 2)) - (6 * WaterFlowLength.width);
      int down = labelY + labelHeight;
      int targetX = (labelX + (labelWidth / 2))
            - (WaterFlowLength.height / 2);
      while (currentY < down) {
         int targetY = currentY + WaterFlowLength.height;
         createVerticalWaterflow(targetX, targetY);
         currentY = currentY + WaterFlowLength.width;
         sleep();
      }
   }

   private void centerToUp(int labelX, int labelY, int labelWidth,
         int labelHeight) {
      int currentY = (labelY + (labelHeight / 2)) + (6 * WaterFlowLength.width);
      int up = labelY;
      int targetX = (labelX + (labelWidth / 2))
            - (WaterFlowLength.height / 2);
      while (currentY > up) {
         int targetY = currentY - WaterFlowLength.height;
         createVerticalWaterflow(targetX, targetY);
         currentY = currentY - WaterFlowLength.width;
         sleep();
      }
   }

}
