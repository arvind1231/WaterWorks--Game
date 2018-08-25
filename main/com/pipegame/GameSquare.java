package com.pipegame;


public class GameSquare {

   private final int rows;

   private final int columns;

   private Square[][] squares;

   public GameSquare(int rows, int columns) {
      this.rows = rows;
      this.columns = columns;
   }

   public void initGrid() {
      squares = new Square[rows][columns];
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < columns; j++) {
            squares[i][j] = new Square();
         }
      }
   }

   public int getRows() {
      return rows;
   }

   public int getColumns() {
      return columns;
   }

   public void emptySquares() {
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < columns; j++) {
            squares[i][j].clear();
         }
      }
   }

   public boolean isSquareEmpty(int row, int column) {
	      boolean isEmpty = squares[row][column].isEmpty();
	      return isEmpty;
	   }
   
   public Square getSquare(int row, int column) {
      return squares[row][column];
   }

   public Square getSquare(SquarePosition location) {
      return squares[location.row][location.column];
   }
   
   public void myMethod() {
	   System.out.println("Something");
   }


}
