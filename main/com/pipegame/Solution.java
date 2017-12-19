package com.pipegame;

import java.util.ArrayList;
import java.util.List;
import com.pipegame.SquarePosition.Entry;
import com.pipegame.SquarePosition.Exit;
import com.pipegame.userInterface.EndingPoint;
import com.pipegame.userInterface.Pipes;
import com.pipegame.userInterface.StartingPoint;


public class Solution {

   private static final List<Pipeline> ALL_PIPES = Pipes.getAllPipes();

   private final RandomPipeRoute randomPipeRoute;

   public Solution(RandomPipeRoute randomPipePath) {
      this.randomPipeRoute = randomPipePath;
   }

   private List<PipeSquare> solution;

   public void findSolution() {
      List<PipeSquare> randomCells = randomPipeRoute.createPath(15);

      PipeSquare startingCell = randomCells.get(0);
      PipeSquare second = randomCells.get(1);
      Exit startingExit = Entry.getExit(startingCell, second);
      startingCell.setExit(startingExit);
      StartingPoint startingPipe = new StartingPoint();
      startingPipe.setExit(startingExit);
      startingCell.setPipe(startingPipe);

      int randomCellsLength = randomCells.size();
      PipeSquare secondLast = randomCells.get(randomCellsLength - 2);
      PipeSquare endingCell = randomCells.get(randomCellsLength - 1);
      Exit endingExit = Entry.getExit(secondLast, endingCell);
      Entry endingEntry = SquarePosition.getExitAsEntry(endingExit);
      EndingPoint endingPipe = new EndingPoint();
      endingPipe.setEntry(endingEntry);
      endingCell.setEntry(endingEntry);
      endingCell.setPipe(endingPipe);

      for (int i = 1; i < (randomCellsLength - 1); i++) {
         PipeSquare entry = randomCells.get(i - 1);
         PipeSquare mid = randomCells.get(i);
         PipeSquare last = randomCells.get(i + 1);
         Exit exitToMid = Entry.getExit(entry, mid);
         Exit exitToLast = Entry.getExit(mid, last);
         Entry entryToMid = SquarePosition.getExitAsEntry(exitToMid);
         List<Pipeline> suitablePipes = getSuitablePipes(entryToMid, exitToLast);
         Pipeline randomSuitable = GenerateRandomUtil.getRandomFromList(suitablePipes);
         mid.setEntry(entryToMid);
         mid.setExit(exitToLast);
         mid.setPipe(randomSuitable);
      }
      solution = randomCells;
   }

   private List<Pipeline> getSuitablePipes(Entry from, Exit to) {
      List<Pipeline> resultList = new ArrayList<Pipeline>();
      for (Pipeline pipe : ALL_PIPES) {
         List<Exit> enterPipe = pipe.enterPipe(from);
         if ((enterPipe.get(0) == to) && (enterPipe.size() == 1)) {
            resultList.add(pipe);
         }
      }
      return resultList;
   }

   public List<PipeSquare> getSolution() {
      return solution;
   }
}
