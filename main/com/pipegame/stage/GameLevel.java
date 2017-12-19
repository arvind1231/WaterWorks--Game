package com.pipegame.stage;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import com.pipegame.Square;
import com.pipegame.SquarePosition;
import com.pipegame.StartingPanel;
import com.pipegame.DrawingTask;
import com.pipegame.GameSquare;
import com.pipegame.GameEnvironment;
import com.pipegame.GenerateRandomUtil;
import com.pipegame.GameOver;
import com.pipegame.GameWon;
import com.pipegame.NextPipeBlock;
import com.pipegame.Match;
import com.pipegame.PanelSetting;
import com.pipegame.PipeSquare;
import com.pipegame.PipeRotation;
import com.pipegame.Pipeline;
import com.pipegame.PipesRemaining;
import com.pipegame.SelectPanel;
import com.pipegame.SimplePipeRoute;
import com.pipegame.Solution;
import com.pipegame.VisualGrid;
import com.pipegame.SquarePosition.Entry;
import com.pipegame.SquarePosition.Exit;
import com.pipegame.userInterface.StartingPoint;

public class GameLevel {

   private final GameSquare gameGrid;

   private final VisualGrid visualGrid;

   private final GameEnvironment background;

   private final SelectPanel selectPanel;

   private final NextPipeBlock nextPipeBar;

   private final PipesRemaining remainingPipes;
   

   private final StartingPanel desktopPane;

   private final GameOver levelFailedPanel;

   private final GameWon levelFinishedPanel;

   private boolean running = false;
   
   static Thread thread = new Thread();

   private List<PipeSquare> solution;

   private final ScheduledExecutorService threadPool = Executors
         .newScheduledThreadPool(1);

	 
   
   
   public GameLevel(GameSquare gameGrid, VisualGrid visualGrid,
           GameEnvironment background, SelectPanel selectPanel,
           NextPipeBlock nextPipeBar, PipesRemaining remainingPipes,
           StartingPanel desktopPane, GameOver levelFailedPanel,
           GameWon levelFinishedPanel) {  
      this.gameGrid = gameGrid;
      this.visualGrid = visualGrid;
      this.background = background;
      this.selectPanel = selectPanel;
      this.nextPipeBar = nextPipeBar;
      this.remainingPipes = remainingPipes;
      this.desktopPane = desktopPane;
      this.levelFailedPanel = levelFailedPanel;
      this.levelFinishedPanel = levelFinishedPanel;
   }


   public boolean isRunning() {
      return running;
   }

 
   public void generateRandomLevel() {
      Solution randomSolution = new Solution(new SimplePipeRoute());
      randomSolution.findSolution();
      solution = randomSolution.getSolution();
   }


   public void resetLevel() {
      clearLevel();
      loadPipes();
   }


   private void loadPipes() {
      PipeSquare startingPipe = solution.get(0);
      loadPipeFromCell(startingPipe);

      PipeSquare endingPipe = solution.get(solution.size() - 1);
      loadPipeFromCell(endingPipe);

      List<Pipeline> clones = new ArrayList<Pipeline>();
      for (int i = 1; i < (solution.size() - 1); i++) {
         PipeSquare pipeCell = solution.get(i);
         Pipeline pipe = pipeCell.getPipe();
         Pipeline clone = pipe.clone();
         clones.add(clone);
      }

      for (int i = 0; i < (clones.size() - 3); i++) {
         GenerateRandomUtil.shuffleSublist(clones, i, i + 2);
      }

      for (Pipeline clone : clones) {
         if (clone instanceof PipeRotation) {
            PipeRotation rotatable = (PipeRotation) clone;
            boolean rotateClockwise = GenerateRandomUtil.nextBoolean();
            int rotateCount = GenerateRandomUtil.nextInt(1, 3);
            for (int i = 0; i < rotateCount; i++) {
               if (rotateClockwise) {
                  rotatable.rotateImageClockwise();
               } else {
                  rotatable.rotateImageCounterclockwise();
               }
            }
         }
      }

      for (Pipeline pipe : clones) {
         nextPipeBar.pushPipe(pipe);
      }
      nextPipeBar.paintPipeIconsToLabels();
      setRemainingPipes();
   }


   public void showSolution() {
      clearLevel();
      for (PipeSquare pipeCell : solution) {
         loadPipeFromCell(pipeCell);
      }
   }


   public void clearLevel() {
      nextPipeBar.clearPipes();
      nextPipeBar.paintPipeIconsToLabels();
      clearWaterFlows();
      clearPipesFromDesktopPane();
      gameGrid.emptySquares();
      remainingPipes.clear();
      
      levelFailedPanel.hide();
      levelFinishedPanel.hide();
   }

   private void clearWaterFlows() {
      desktopPane.clearWaterFlows();
   }

   private void clearPipesFromDesktopPane() {
      for (int i = 0; i < PanelSetting.GAME_GRID_ROWS; i++) {
         for (int j = 0; j < PanelSetting.GAME_GRID_COLUMNS; j++) {
            Square cell = gameGrid.getSquare(i, j);
            if (!cell.isEmpty()) {
               Pipeline pipeInCell = cell.getPipe();
               desktopPane.remove(pipeInCell);
            }
         }
      }
   }


   private void loadPipeFromCell(PipeSquare pipeCell) {
      Square cell = gameGrid.getSquare(pipeCell);
      Pipeline pipeClone = pipeCell.getPipe().clone();
      cell.positionPipe(pipeClone);
      addPipeToDesktopPane(pipeClone);
      setPipeBounds(pipeClone);
      Point cellPoint = visualGrid.getSquarePoint(pipeCell);
      pipeClone.setLocation(cellPoint);
      pipeClone.setVisible(true);
   }


   public boolean isPointValid(int x, int y) {
      boolean cellEmpty = visualGrid.isSquareEmpty(x, y);
      boolean pointValid = visualGrid.isPointValid(x, y);
      return (cellEmpty && pointValid);
   }


   public void mouseMoved(int x, int y) {
      Point cellPoint = visualGrid.getSquarePoint(x, y);
      selectPanel.setPosition(cellPoint);
      boolean cellEmpty = visualGrid.isSquareEmpty(x, y);
      if (cellEmpty) {
         selectPanel.setToGreen();
      } else {
         selectPanel.setToRed();
      }
   }

   public void placePipe(int x, int y) {
      if (nextPipeBar.isEmpty()) {
         return;
      }
      Pipeline pipe = nextPipeBar.popPipe();

      setPipeBounds(pipe);
      setPipeLocationToSelectPanel(pipe);
      pipe.setVisible(true);
      addPipeToDesktopPane(pipe);

      visualGrid.positionPipe(x, y, pipe);

      selectPanel.setToRed();

      nextPipeBar.paintPipeIconsToLabels();
      setRemainingPipes();
      setRemainingTime();
   }

   private void setRemainingPipes() {
      remainingPipes.setRemaining(nextPipeBar.getCount());
   }

   private void setRemainingTime() {

	   }
   
   public void time() throws InterruptedException
	  {
	  for(int i=60;i>=0;i--)
	  {
	  thread.sleep(1000);
	  System.out.println(i);
	  }
	  }
   
   
   private void setPipeLocationToSelectPanel(Pipeline pipe) {
      Point targetPoint = selectPanel.getPosition();
      pipe.setLocation(targetPoint);
   }

   private void setPipeBounds(Pipeline pipe) {
      int cellWidth = visualGrid.getSquareWidth();
      int cellHeight = visualGrid.getSquareHeight() + PanelSetting.FIX_CELL_HEIGHT;
      pipe.setBounds(0, 0, cellWidth, cellHeight);
   }

   private void addPipeToDesktopPane(Pipeline pipe) {
      desktopPane.add(pipe, javax.swing.JLayeredPane.DEFAULT_LAYER);
      desktopPane.setComponentZOrder(pipe, 1);
   }

   public void startLevel() {
      background.setToGameBackground();
      setSelectPanelToCenter();
      selectPanel.show();
      loadPipes();
      running = true;
   }

   private void setSelectPanelToCenter() {
      selectPanel.setPosition(PanelSetting.VISUAL_GRID_WIDTH / 2,
            PanelSetting.VISUAL_GRID_HEIGHT / 2);
   }

   public void finishLevel() {
      Match<SquarePosition, StartingPoint> pair = findStartingPipe();
      SquarePosition current = pair.first;
      final List<PipeSquare> pipeline = new ArrayList<PipeSquare>();
      boolean finished = false;
      List<Match<SquarePosition, Entry>> enteredCells = new ArrayList<Match<SquarePosition, Entry>>();
      List<Match<SquarePosition, Entry>> queue = new ArrayList<Match<SquarePosition, Entry>>();
      queue.add(new Match<SquarePosition, Entry>(current, Entry.START));
      boolean leak = false;
      boolean failed = false;
      List<Future<?>> futures = new ArrayList<Future<?>>();
      while (!finished) {
         enteredCells.addAll(queue);
         queue.clear();

         for (Match<SquarePosition, Entry> entered : enteredCells) {
            Entry entry = entered.second;
            SquarePosition currentLocation = entered.first;
            boolean locationNotValid = !currentLocation.isValid(
                  PanelSetting.GAME_GRID_ROWS,
                  PanelSetting.GAME_GRID_COLUMNS);
            if (locationNotValid) {
               leak = true;
               continue;
            }
            Square cell = gameGrid.getSquare(currentLocation);
            Pipeline pipe = cell.getPipe();
            if ((pipe == null)) {
               leak = true;
               continue;
            }

            PipeSquare pipeCell = new PipeSquare(currentLocation.row,
                  currentLocation.column);
            pipeCell.setPipe(pipe);
            pipeline.add(pipeCell);

            List<Exit> exits = pipe.enterPipe(entry);
            for (Exit exit : exits) {
               if (exit == Exit.FINISH) {
                  finished = true;
                  continue;
               } else if (exit == Exit.FAIL) {
                  failed = true;
                  finished = true;
                  continue;
               }
               DrawingTask task = pipe.getDrawWaterflowTask(entry);
               task.setStartingPanel(desktopPane);
               Future<?> future = threadPool.submit(task);
               futures.add(future);

               SquarePosition nextLocation = exit
                     .getNextLocation(currentLocation);
               Entry exitAsEntry = SquarePosition.getExitAsEntry(exit);
               Match<SquarePosition, Entry> pair2 = new Match<SquarePosition, Entry>(
                     nextLocation,
                     exitAsEntry);
               queue.add(pair2);
            }
         }

         enteredCells.clear();
         if (leak) {
            failed = true;
            finished = true;
         }
      }
      final boolean levelFinished = !failed && finished;
      Runnable showFinishScreen = new Runnable() {

         @Override
         public void run() {
            sleep();
            if (!levelFinished) {
               desktopPane.setComponentZOrder(levelFailedPanel.getComponent(),
                     1);
               levelFailedPanel.show();
            } else {
               int playerScore = pipeline.size() * 100;
               levelFinishedPanel.setPlayerScore(String.valueOf(playerScore));
               int maxScore = solution.size() * 100;
               levelFinishedPanel.setMaxScore(String.valueOf(maxScore));
               desktopPane.setComponentZOrder(
                     levelFinishedPanel.getComponent(), 1);
               levelFinishedPanel.show();
            }
         }

         private void sleep() {
            try {
               Thread.sleep(850);
            } catch (InterruptedException e) {

            }
         }
      };
      threadPool.submit(showFinishScreen);
   }

   private Match<SquarePosition, StartingPoint> findStartingPipe() {
      for (int i = 0; i < PanelSetting.GAME_GRID_ROWS; i++) {
         for (int j = 0; j < PanelSetting.GAME_GRID_COLUMNS; j++) {
            Square cell = gameGrid.getSquare(i, j);
            Pipeline pipe = cell.getPipe();
            if (pipe instanceof StartingPoint) {
               StartingPoint startingPipe = (StartingPoint) pipe;
               SquarePosition location = new SquarePosition(i, j);
               Match<SquarePosition, StartingPoint> pair = new Match<SquarePosition, StartingPoint>(
                     location, startingPipe);
               return pair;
            }
         }
      }
      return null;
   }

   public void exitLevel() {
      running = false;
      selectPanel.hide();
      nextPipeBar.clearPipes();
      clearWaterFlows();
      clearPipesFromDesktopPane();
      gameGrid.emptySquares();
      levelFailedPanel.hide();
      levelFinishedPanel.hide();
      background.setToStartingBackground();
   }

   public void rotateNextPipeClockwise() {
      Pipeline pipe = nextPipeBar.getFirst();
      if (pipe instanceof PipeRotation) {
         PipeRotation rotatable = (PipeRotation) pipe;
         rotatable.rotateImageClockwise();
         nextPipeBar.paintPipeIconsToLabels();
      }
   }

   public void rotateNextPipeCounterclockwise() {
      Pipeline pipe = nextPipeBar.getFirst();
      if (pipe instanceof PipeRotation) {
         PipeRotation rotatable = (PipeRotation) pipe;
         rotatable.rotateImageCounterclockwise();
         nextPipeBar.paintPipeIconsToLabels();
      }
   }
}
