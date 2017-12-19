package com.pipegame;

import java.util.LinkedList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JLabel;

public class NextPipeBlock {

   private final List<JLabel> labels;

   private final LinkedList<Pipeline> pipes = new LinkedList<Pipeline>();

   public NextPipeBlock(List<JLabel> labels) {
      this.labels = labels;
   }

   public void pushPipe(Pipeline pipeLabel) {
      pipes.add(pipeLabel);
   }

   public void paintPipeIconsToLabels() {
      for (int i = 0; i < labels.size(); i++) {
         JLabel label = labels.get(i);
         if (i < pipes.size()) {
            Icon icon = pipes.get(i).getIcon();
            label.setIcon(icon);
         } else {
            label.setIcon(null);
         }
         label.repaint();
      }
   }

   public void clearPipes() {
      pipes.clear();
   }

   public Pipeline getFirst() {
      if (pipes.isEmpty()) {
         return null;
      }
      return pipes.getFirst();
   }

   public Pipeline getLast() {
      if (pipes.isEmpty()) {
         return null;
      }
      return pipes.getLast();
   }

   public Pipeline popPipe() {
      Pipeline top = pipes.removeFirst();
      return top;
   }

   public boolean isEmpty() {
      return pipes.isEmpty();
   }

   public int getCount() {
      return pipes.size();
   }
}
