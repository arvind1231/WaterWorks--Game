package com.pipegame;

import java.util.LinkedList;
import java.util.List;

public class PipeSquare extends SquarePosition {

   private PipeSquare entrySquare;

   private PipeSquare exitSquare;

   private Entry entry;

   private Exit exit;

   private Pipeline pipe;

   public PipeSquare(int row, int column) {
      super(row, column);
   }

   public PipeSquare getEntry() {
      return entrySquare;
   }

   public void setEntry(PipeSquare entry) {
      this.entrySquare = entry;
   }

   public Pipeline getPipe() {
      return pipe;
   }

   public void setPipe(Pipeline pipe) {
      this.pipe = pipe;
   }

   public PipeSquare getExit() {
      return exitSquare;
   }

   public void setExit(PipeSquare exit) {
      this.exitSquare = exit;
   }

   public List<PipeSquare> getAdjacent() {
      List<PipeSquare> adjacent = new LinkedList<PipeSquare>();
      adjacent.add(new PipeSquare(row + 1, column));
      adjacent.add(new PipeSquare(row - 1, column));
      adjacent.add(new PipeSquare(row, column + 1));
      adjacent.add(new PipeSquare(row, column - 1));
      return adjacent;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      PipeSquare other = (PipeSquare) obj;
      boolean rowMatch = other.row == super.row;
      boolean columnMatch = other.column == super.column;
      boolean result = rowMatch && columnMatch;
      return result;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("[row=" + row);
      sb.append(" column=" + column);
      sb.append("] " + entry);
      sb.append(" " + exit);
      sb.append(" pipe: " + pipe.toString());
      return sb.toString();
   }

   public void setEntry(Entry entry) {
      this.entry = entry;
   }

   public void setExit(Exit exit) {
      this.exit = exit;
   }
}
