package com.pipegame;

import java.awt.Point;


public class VisualGrid {

	private final int width;
	private final int height;
	
	private final GameSquare gameGrid;

    private final int squareWidth;

    private final int squareHeight;

   

   public VisualGrid(GameSquare gameGrid, int width, int height) {
      this.gameGrid = gameGrid;
      this.width = width;
      this.height = height;
      this.squareWidth = width / gameGrid.getRows();
      this.squareHeight = height / gameGrid.getColumns();
   }

   public boolean isSquareEmpty(int x, int y) {
      int row = getRowForX(x);
      int column = getColumnForY(y);
      boolean isEmpty = gameGrid.isSquareEmpty(row, column);
      return isEmpty;
   }

   private int getRowForX(int x) {
      int row = x / squareWidth;
      int rows = gameGrid.getRows();
      if (row >= rows) {
         row = rows - 1;
      }
      return row;
   }

   private int getColumnForY(int y) {
      int column = y / squareHeight;
      int columns = gameGrid.getColumns();
      if (column >= columns) {
         column = columns - 1;
      }
      return column;
   }

   public boolean isPointValid(int x, int y) {
      boolean isInsideGrid = (x < width) && (y < height);
      return isInsideGrid;
   }

   public void positionPipe(int x, int y, Pipeline pipe) {
      int row = getRowForX(x);
      int column = getColumnForY(y);
      Square cell = gameGrid.getSquare(row, column);
      cell.positionPipe(pipe);
   }

   public int getSquareWidth() {
      return squareWidth;
   }

   public int getSquareHeight() {
      return squareHeight;
   }

   public Point getSquarePoint(int x, int y) {
      int cellX = getSquareX(x);
      int cellY = getSquareY(y);
      return new Point(cellX, cellY);
   }

   private int getSquareX(int x) {
      int row = getRowForX(x);
      int cellX = row * squareWidth;
      return cellX;
   }

   private int getSquareY(int y) {
      int column = getColumnForY(y);
      int cellY = column * squareHeight;
      return cellY;
   }

   public Point getSquarePoint(SquarePosition location) {
      int x = location.row * squareWidth;
      int y = location.column * squareHeight;
      Point cellPoint = new Point(x, y);
      return cellPoint;
   }

}
