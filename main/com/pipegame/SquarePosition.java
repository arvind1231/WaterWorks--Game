package com.pipegame;

import java.util.HashMap;
import java.util.Map;


public class SquarePosition {

   public static enum Entry {
      FROM_LEFT(Exit.TO_LEFT),
      FROM_DOWN(Exit.TO_DOWN),
      FROM_RIGHT(Exit.TO_RIGHT),
      FROM_UP(Exit.TO_UP),
      START(null);

      private final Exit asExit;

      Entry(Exit asExit) {
         this.asExit = asExit;
      }

      public Exit getAsExit() {
         return asExit;
      }

      public static Exit getExit(SquarePosition from, SquarePosition to) {
         int shiftRow = to.row - from.row;
         int shiftColumn = to.column - from.column;
         IntMatch shift = new IntMatch(shiftRow, shiftColumn);
         for (Exit exit : Exit.values()) {
            if (exit.getMovePair().equals(shift)) {
               return exit;
            }
         }
         return null;
      }
   }

   public static enum Exit {
      TO_LEFT(new IntMatch(-1, 0)),
      TO_DOWN(new IntMatch(0, 1)),
      TO_RIGHT(new IntMatch(1, 0)),
      TO_UP(new IntMatch(0, -1)),
      FAIL(new IntMatch(-1, -1)),
      FINISH(new IntMatch(-1, -1));

      private final int moveRow;

      private final int moveColumn;

      Exit(IntMatch pair) {
         this.moveRow = pair.first;
         this.moveColumn = pair.second;
      }

      public SquarePosition getNextLocation(SquarePosition current) {
         int rowMoved = current.row + moveRow;
         int columnMoved = current.column + moveColumn;
         SquarePosition next = new SquarePosition(rowMoved, columnMoved);
         return next;
      }

      public IntMatch getMovePair() {
         return new IntMatch(moveRow, moveColumn);
      }
   }

   private static Map<Exit, Entry> EXIT_TO_ENTRY = new HashMap<Exit, Entry>();
   static {
      EXIT_TO_ENTRY.put(Exit.TO_LEFT, Entry.FROM_RIGHT);
      EXIT_TO_ENTRY.put(Exit.TO_DOWN, Entry.FROM_UP);
      EXIT_TO_ENTRY.put(Exit.TO_RIGHT, Entry.FROM_LEFT);
      EXIT_TO_ENTRY.put(Exit.TO_UP, Entry.FROM_DOWN);
   }

   public static Entry getExitAsEntry(Exit exit) {
      return EXIT_TO_ENTRY.get(exit);
   }

   public final int row;

   public final int column;

   public SquarePosition(int row, int column) {
      this.row = row;
      this.column = column;
   }

   public boolean isValid(int maxRows, int maxColumns) {
      boolean validRow = (row >= 0) && (row < maxRows);
      boolean validColumn = (column >= 0) && (column < maxColumns);
      return validRow && validColumn;
   }

   @Override
   public String toString() {
      return "CellLocation [row=" + row + ", column=" + column + "]";
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = (prime * result) + column;
      result = (prime * result) + row;
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      SquarePosition other = (SquarePosition) obj;
      if (column != other.column)
         return false;
      if (row != other.row)
         return false;
      return true;
   }

}
