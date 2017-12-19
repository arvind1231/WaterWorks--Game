package com.pipegame;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;


public class StartingPanel {

   private final JDesktopPane startingPanel;

   private final List<WaterFlow> waterFlows = new ArrayList<WaterFlow>();

   public StartingPanel(JDesktopPane desktopPane) {
      this.startingPanel = desktopPane;
   }

   public void addWaterFlow(WaterFlow waterFlow) {
      waterFlows.add(waterFlow);
      startingPanel.add(waterFlow);
   }

   public void clearWaterFlows() {
      for (WaterFlow waterFlow : waterFlows) {
         waterFlow.setVisible(false);
         startingPanel.remove(waterFlow);
      }
      waterFlows.clear();
   }

   public void setComponentZOrder(Component comp, int index) {
      startingPanel.setComponentZOrder(comp, index);
   }

   public void add(Component component) {
      startingPanel.add(component);
   }

   public void add(Component component, Object constrains) {
      startingPanel.add(component, constrains);
   }

   public void remove(Component component) {
      startingPanel.remove(component);
   }
}
