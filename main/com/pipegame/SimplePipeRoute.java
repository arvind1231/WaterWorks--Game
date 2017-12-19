package com.pipegame;

import java.util.ArrayList;
import java.util.List;


public class SimplePipeRoute implements RandomPipeRoute {

   @Override
   public List<PipeSquare> createPath(int pathLength) {
      List<PipeSquare> resultList = new ArrayList<PipeSquare>();
      int row = GenerateRandomUtil.nextInt(PanelSetting.GAME_GRID_ROWS);
      int column = GenerateRandomUtil.nextInt(PanelSetting.GAME_GRID_COLUMNS);
      PipeSquare first = new PipeSquare(row, column);
      PipeSquare current = first;
      boolean finished = false;
      while (!finished) {
         List<PipeSquare> adjacentList = current.getAdjacent();
         for (int i = 0; i < adjacentList.size(); i++) {
            PipeSquare cell = adjacentList.get(i);
            if (!cell.isValid(PanelSetting.GAME_GRID_ROWS,
                  PanelSetting.GAME_GRID_COLUMNS)) {
               adjacentList.remove(i);
               continue;
            }
            List<PipeSquare> adjacentOfSelected = cell.getAdjacent();
            adjacentOfSelected.retainAll(resultList);
            if (adjacentOfSelected.size() >= 2) {
               adjacentList.remove(i);
               continue;
            }
         }

         if (adjacentList.isEmpty()) {
            return resultList;
         }

         PipeSquare random = GenerateRandomUtil.getRandomFromList(adjacentList);
         while (!validCell(random, adjacentList, resultList)) {
            adjacentList.remove(random);
            if (adjacentList.isEmpty()) {
               return resultList;
            }
            random = GenerateRandomUtil.getRandomFromList(adjacentList);
         }
         current = random;
         resultList.add(current);
         finished = resultList.size() == pathLength;
      }
      return resultList;
   }

   private boolean validCell(PipeSquare cell, List<PipeSquare> adjacent,
         List<PipeSquare> resultList) {
      boolean cellNotUsed = !resultList.contains(cell);
      boolean validCoordinates = cell.isValid(PanelSetting.GAME_GRID_ROWS,
            PanelSetting.GAME_GRID_COLUMNS);
      boolean result = cellNotUsed && validCoordinates;
      return result;
   }

}
