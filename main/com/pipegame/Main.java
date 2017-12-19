package com.pipegame;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.pipegame.stage.GameLevel;


public class Main extends javax.swing.JFrame {
  
   private static final long serialVersionUID = 9133513209386943169L;


   public Main() {
      initComponents();
      initGameComponents();
      playGame();
   }

   public static void main(String args[]) {

      try {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
               .getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
               javax.swing.UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (Exception e) {
         java.util.logging.Logger.getLogger(Main.class.getName()).log(
               java.util.logging.Level.SEVERE, null, e);
      }

      java.awt.EventQueue.invokeLater(new Runnable() {

         @Override
         public void run() {
            new Main().setVisible(true);
         }
      });
   }


   private void initComponents() {

      desktopPane = new javax.swing.JDesktopPane();
      jPlayButton = new javax.swing.JMenuItem();
      jAboutLabel = new javax.swing.JLabel();
      jAboutCloseButton = new javax.swing.JButton();
      jLevelFinishedPanel = new javax.swing.JPanel();
      jPlayerScore = new javax.swing.JLabel();
      jMaxScore = new javax.swing.JLabel();
      jFinishLevelScreen = new javax.swing.JLabel();
      jLevelFailedlPanel = new javax.swing.JPanel();
      jLevelFailedLabel = new javax.swing.JLabel();
      jLevelFailedScreen = new javax.swing.JLabel();
      jSelectPanel = new javax.swing.JPanel();
      jBottomBar = new javax.swing.JPanel();
      jRotateClockwise = new javax.swing.JButton();
      jRotateCounterClockwise = new javax.swing.JButton();
      jNewLevelButton = new javax.swing.JMenuItem();
      jNextPipesPanel = new javax.swing.JPanel();
      jHighlightFirstPipeLabel = new javax.swing.JLabel();
      jNextFirstPipeLabel = new javax.swing.JLabel();
      jNextSecondPipeLabel = new javax.swing.JLabel();
      jRemainingPipes = new javax.swing.JLabel();
      jRemainingTime = new javax.swing.JLabel();
      
      jNextThirdPipeLabel = new javax.swing.JLabel();
      jResetLevel = new javax.swing.JMenuItem();
      jShowSolution = new javax.swing.JMenuItem();
      jFinishLevel = new javax.swing.JButton();
      jBottomBarLabel = new javax.swing.JLabel();
      jRightBar = new javax.swing.JPanel();
      jRightBarLabel = new javax.swing.JLabel();
      jBackgroundLabel = new javax.swing.JLabel();
      menuBar = new javax.swing.JMenuBar();
      fileMenu = new javax.swing.JMenu();
      mainScreenMenuItem = new javax.swing.JMenuItem();
      exitMenuItem = new javax.swing.JMenuItem();
      helpMenu = new javax.swing.JMenu();
      aboutMenuItem = new javax.swing.JMenuItem();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

     jPlayButton.setBackground(new java.awt.Color(204, 204, 255));
      jPlayButton.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); 
      jPlayButton.setText("Play");
      jPlayButton.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory
                  .createEtchedBorder(javax.swing.border.EtchedBorder.RAISED),
            new javax.swing.border.SoftBevelBorder(
                  javax.swing.border.BevelBorder.RAISED)));
      jPlayButton.setFocusable(false);
      jPlayButton.addActionListener(new java.awt.event.ActionListener() {

         @Override
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jPlayButtonActionPerformed(evt);
         }
      });
      jPlayButton.setBounds(370, 320, 100, 40);

      


      jLevelFinishedPanel.setBackground(new java.awt.Color(153, 153, 153));
      jLevelFinishedPanel.setBorder(javax.swing.BorderFactory
            .createBevelBorder(javax.swing.border.BevelBorder.RAISED));
      jLevelFinishedPanel.setFocusable(false);
      jLevelFinishedPanel.setLayout(null);

      jPlayerScore.setFont(new java.awt.Font("Consolas", 1, 24));
      jPlayerScore.setForeground(new java.awt.Color(255, 255, 255));
      jPlayerScore.setText("YOU WIN !");
      jPlayerScore.setFocusable(false);
//      jLevelFinishedPanel.add(jPlayerScore);
      jPlayerScore.setBounds(70, 0, 300, 57);

      jMaxScore.setFont(new java.awt.Font("Consolas", 1, 24)); 
      jMaxScore.setForeground(new java.awt.Color(255, 255, 255));
//      jMaxScore.setText("Max for level:");
      jMaxScore.setFocusable(false);
      jLevelFinishedPanel.add(jMaxScore);
      jMaxScore.setBounds(100, 0, 300, 100);

      jFinishLevelScreen.setIcon(new javax.swing.ImageIcon(getClass()
            .getResource("/com/pipegame/images/bottom.jpg")));
      jFinishLevelScreen.setFocusable(false);
      jLevelFinishedPanel.add(jFinishLevelScreen);
      jFinishLevelScreen.setBounds(10, 0, 320, 100);

      jLevelFinishedPanel.setBounds(260, 260, 340, 100);
      desktopPane.add(jLevelFinishedPanel,
            javax.swing.JLayeredPane.DEFAULT_LAYER);

      jLevelFailedlPanel.setFocusable(false);
      jLevelFailedlPanel.setLayout(null);

      jLevelFailedLabel.setFont(new java.awt.Font("Consolas", 1, 36));
      jLevelFailedLabel.setForeground(new java.awt.Color(255, 255, 255));
      jLevelFailedLabel.setText("LEVEL FAILED");
      jLevelFailedLabel.setFocusable(false);
      jLevelFailedlPanel.add(jLevelFailedLabel);
      jLevelFailedLabel.setBounds(291, 328, 250, 70);

      jLevelFailedScreen.setIcon(new javax.swing.ImageIcon(getClass()
            .getResource("/com/pipegame/images/game_over.png"))); 
      jLevelFailedScreen.setToolTipText("");
      jLevelFailedScreen.setFocusable(false);
      jLevelFailedlPanel.add(jLevelFailedScreen);
      jLevelFailedScreen.setBounds(0, 0, 800, 400);

      jLevelFailedlPanel.setBounds(0, 0, 800, 400);
      desktopPane.add(jLevelFailedlPanel,
            javax.swing.JLayeredPane.DEFAULT_LAYER);

      jSelectPanel.setBackground(new java.awt.Color(102, 255, 102, 77));
      jSelectPanel.setBorder(new javax.swing.border.SoftBevelBorder(
            javax.swing.border.BevelBorder.RAISED));
      jSelectPanel.setFocusable(false);

      javax.swing.GroupLayout jSelectPanelLayout = new javax.swing.GroupLayout(
            jSelectPanel);
      jSelectPanel.setLayout(jSelectPanelLayout);
      jSelectPanelLayout.setHorizontalGroup(
            jSelectPanelLayout.createParallelGroup(
                  javax.swing.GroupLayout.Alignment.LEADING)
                  .addGap(0, 64, Short.MAX_VALUE)
            );
      jSelectPanelLayout.setVerticalGroup(
            jSelectPanelLayout.createParallelGroup(
                  javax.swing.GroupLayout.Alignment.LEADING)
                  .addGap(0, 52, Short.MAX_VALUE)
            );

      jSelectPanel.setBounds(130, 72, 70, 58);
      desktopPane.add(jSelectPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

      jBottomBar.setLayout(null);

      jRotateClockwise.setIcon(new javax.swing.ImageIcon(getClass()
            .getResource("/com/pipegame/images/clockwise.png"))); 
      jRotateClockwise.setBorder(null);
      jRotateClockwise.setBorderPainted(false);
      jRotateClockwise.setContentAreaFilled(false);
      jRotateClockwise.setOpaque(true);
      jRotateClockwise.addActionListener(new java.awt.event.ActionListener() {

         @Override
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jRotateClockwiseActionPerformed(evt);
         }
      });
      jBottomBar.add(jRotateClockwise);
      jRotateClockwise.setBounds(0, 0, 100, 80);

 

      jNewLevelButton.setText("New Game");
      jNewLevelButton.setFocusable(false);
      jNewLevelButton.addActionListener(new java.awt.event.ActionListener() {

         @Override
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jNewLevelButtonActionPerformed(evt);
         }
      });
      jBottomBar.add(jNewLevelButton);
      jNewLevelButton.setBounds(540, 10, 100, 23);

      jNextPipesPanel.setBackground(new java.awt.Color(115, 8, 15));
      jNextPipesPanel.setBorder(javax.swing.BorderFactory
            .createBevelBorder(javax.swing.border.BevelBorder.RAISED));
      jNextPipesPanel.setForeground(new java.awt.Color(204, 204, 255));
      jNextPipesPanel.setLayout(null);

      jHighlightFirstPipeLabel.setBorder(javax.swing.BorderFactory
            .createLineBorder(new java.awt.Color(255, 102, 102)));
      jNextPipesPanel.add(jHighlightFirstPipeLabel);
      jHighlightFirstPipeLabel.setBounds(17, 7, 80, 70);
      jNextPipesPanel.add(jNextFirstPipeLabel);
      jNextFirstPipeLabel.setBounds(7, 7, 90, 70);
      jNextPipesPanel.add(jNextSecondPipeLabel);
      jNextSecondPipeLabel.setBounds(100, 7, 90, 70);
      
         
      
      jNextPipesPanel.add(jNextThirdPipeLabel);
      jNextThirdPipeLabel.setBounds(190, 7, 90, 70);

      jBottomBar.add(jNextPipesPanel);
      jNextPipesPanel.setBounds(100, 0, 290, 80);

      jResetLevel.setText("Play Again");
      jResetLevel.setFocusable(false);
      jResetLevel.addActionListener(new java.awt.event.ActionListener() {

         @Override
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jResetLevelActionPerformed(evt);
         }
      });
      jBottomBar.add(jResetLevel);
      jResetLevel.setBounds(540, 40, 100, 23);

      jShowSolution.setText("Solve");
      jShowSolution.addActionListener(new java.awt.event.ActionListener() {

         @Override
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jShowSolutionActionPerformed(evt);
         }
      });
      jBottomBar.add(jShowSolution);
      jShowSolution.setBounds(650, 40, 110, 23);

      jFinishLevel.setText("Turn On Water");
      jFinishLevel.addActionListener(new java.awt.event.ActionListener() {

         @Override
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFinishLevelActionPerformed(evt);
         }
      });
      jBottomBar.add(jFinishLevel);
      jFinishLevel.setBounds(450, 10, 270, 50);

      jBottomBarLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(
              ""))); 


      jBottomBar.setBounds(0, 500, 800, 80);
      desktopPane.add(jBottomBar, javax.swing.JLayeredPane.DEFAULT_LAYER);


      jBackgroundLabel.setFocusable(false);
      jBackgroundLabel.addMouseListener(new java.awt.event.MouseAdapter() {

         @Override
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            jBackgroundLabelMouseClicked(evt);
         }
      });
      jBackgroundLabel
            .addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

               @Override
               public void mouseMoved(java.awt.event.MouseEvent evt) {
                  jBackgroundLabelMouseMoved(evt);
               }
            });
      jBackgroundLabel.setBounds(0, 0, 800, 580);
      desktopPane.add(jBackgroundLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

      menuBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

      fileMenu.setMnemonic('f');
      fileMenu.setText("Menu");

      jNewLevelButton.setMnemonic('x');
      jNewLevelButton.setText("New Game");
      jNewLevelButton.addActionListener(new java.awt.event.ActionListener() {

         @Override
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jNewLevelButtonActionPerformed(evt);
         }
      });
      fileMenu.add(jNewLevelButton);   
      
      
      exitMenuItem.setMnemonic('x');
      exitMenuItem.setText("Exit Game");
      exitMenuItem.addActionListener(new java.awt.event.ActionListener() {

         @Override
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            exitMenuItemActionPerformed(evt);
         }
      });
      fileMenu.add(exitMenuItem);

      jResetLevel.setMnemonic('x');
      jResetLevel.setText("Play Again");
      jResetLevel.addActionListener(new java.awt.event.ActionListener() {

         @Override
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jResetLevelActionPerformed(evt);
         }
      });

      fileMenu.add(jResetLevel);
      
      jShowSolution.setMnemonic('x');
      jShowSolution.setText("Solve");
      jShowSolution.addActionListener(new java.awt.event.ActionListener() {

         @Override
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jShowSolutionActionPerformed(evt);
         }
      });
      fileMenu.add(jShowSolution);

      exitMenuItem.setMnemonic('x');
      exitMenuItem.setText("Exit Game");
      exitMenuItem.addActionListener(new java.awt.event.ActionListener() {

         @Override
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            exitMenuItemActionPerformed(evt);
         }
      });
      fileMenu.add(exitMenuItem);

      menuBar.add(fileMenu);

      helpMenu.setMnemonic('h');

      menuBar.add(helpMenu);

      setJMenuBar(menuBar);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
            getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
            layout.createParallelGroup(
                  javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(desktopPane,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 800,
                        Short.MAX_VALUE)
            );
      layout.setVerticalGroup(
            layout.createParallelGroup(
                  javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(desktopPane,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 577,
                        Short.MAX_VALUE)
            );

      pack();
      setLocationRelativeTo(null);
   }



   private void jFinishLevelActionPerformed(java.awt.event.ActionEvent evt) {
      gameLevel.finishLevel();
      jFinishLevel.setEnabled(false);
   }

   private void jResetLevelActionPerformed(java.awt.event.ActionEvent evt) {
      gameLevel.resetLevel();
      jShowSolution.setEnabled(true);
      jFinishLevel.setEnabled(true);
   }

   private void jShowSolutionActionPerformed(java.awt.event.ActionEvent evt) {
      gameLevel.showSolution();
      jShowSolution.setEnabled(false);
   }

   private void jNewLevelButtonActionPerformed(java.awt.event.ActionEvent evt) {
      gameLevel.exitLevel();
      gameLevel.generateRandomLevel();
      gameLevel.startLevel();
      jShowSolution.setEnabled(true);
      jFinishLevel.setEnabled(true);
   }

   private void jRotateClockwiseActionPerformed(java.awt.event.ActionEvent evt) {
      gameLevel.rotateNextPipeClockwise();
   }

   private void jRotateCounterClockwiseActionPerformed(
         java.awt.event.ActionEvent evt) {
      gameLevel.rotateNextPipeCounterclockwise();
   }

   private void mainScreenMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
      setToMainScreen();
   }

   private void setToMainScreen() {
      gameLevel.exitLevel();
      hideBars();
      jPlayButton.setVisible(true);
   }

   private void jBackgroundLabelMouseMoved(java.awt.event.MouseEvent evt) {
      if (!gameLevel.isRunning()) {
         return;
      }
      int x = evt.getX();
      int y = evt.getY();
      gameLevel.mouseMoved(x, y);
   }

   private void jBackgroundLabelMouseClicked(java.awt.event.MouseEvent evt) {
      clickBackground(evt);
   }

   private void clickBackground(java.awt.event.MouseEvent evt) {
      if (!gameLevel.isRunning()) {
         return;
      }
      int x = evt.getX();
      int y = evt.getY();
      if (gameLevel.isPointValid(x, y)) {
         gameLevel.placePipe(x, y);
      }
   }

   private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
      System.exit(0);
   }

   private void jPlayButtonActionPerformed(java.awt.event.ActionEvent evt) {
      playGame();
   }

   private void playGame() {
      gameLevel.generateRandomLevel();
      gameLevel.startLevel();
      hideMainScreen();
      showBars();
   }

   private void hideMainScreen() {
      jPlayButton.setVisible(false);
   }

   private void initGameComponents() {
      background = new GameEnvironment(jBackgroundLabel);
      background.setToStartingBackground();

      gameGrid = new GameSquare(PanelSetting.GAME_GRID_ROWS,
            PanelSetting.GAME_GRID_COLUMNS);
      gameGrid.initGrid();

      visualGrid = new VisualGrid(gameGrid, PanelSetting.VISUAL_GRID_WIDTH,
            PanelSetting.VISUAL_GRID_HEIGHT);

      selectPanel = new SelectPanel(jSelectPanel);
      selectPanel.hide();

      initNextPipeBar();

      remainingPipes = new PipesRemaining(jRemainingPipes);
      

      levelFailedPanel = new GameOver(jLevelFailedlPanel);
      levelFinishedPanel = new GameWon(jLevelFinishedPanel,
            jPlayerScore, jMaxScore);
      levelFailedPanel.hide();
      levelFinishedPanel.hide();

      StartingPanel desktopPaneWrapper = new StartingPanel(desktopPane);
      gameLevel = new GameLevel(gameGrid, visualGrid, background, selectPanel,
            nextPipeBar, remainingPipes, desktopPaneWrapper, levelFailedPanel,
            levelFinishedPanel);
      
      bars = new ArrayList<JPanel>(2);
      bars.add(jBottomBar);
      bars.add(jRightBar);
      hideBars();
   }

   private void initNextPipeBar() {
      int nextPipeCount = PanelSetting.NEXT_PIPES_COUNT;
      List<JLabel> nextPipeLabels = new ArrayList<JLabel>(nextPipeCount);
      nextPipeLabels.add(jNextFirstPipeLabel);
      nextPipeLabels.add(jNextSecondPipeLabel);
      nextPipeLabels.add(jNextThirdPipeLabel);

      nextPipeBar = new NextPipeBlock(nextPipeLabels);
   }

   private void hideBars() {
      for (JPanel bar : bars) {
         bar.setVisible(false);
      }
   }

   private void showBars() {
      for (JPanel bar : bars) {
         bar.setVisible(true);
      }
   }

   private GameEnvironment background;

   private VisualGrid visualGrid;

   private GameSquare gameGrid;

   private SelectPanel selectPanel;

   private NextPipeBlock nextPipeBar;

   private List<JPanel> bars;

   private GameLevel gameLevel;

   private PipesRemaining remainingPipes;
   

   private GameOver levelFailedPanel;

   private GameWon levelFinishedPanel;

   private javax.swing.JMenuItem aboutMenuItem;

   private javax.swing.JDesktopPane desktopPane;

   private javax.swing.JMenuItem exitMenuItem;

   private javax.swing.JMenu fileMenu;

   private javax.swing.JMenu helpMenu;

   private javax.swing.JButton jAboutCloseButton;

   private javax.swing.JLabel jAboutLabel;

   private javax.swing.JLabel jBackgroundLabel;

   private javax.swing.JPanel jBottomBar;

   private javax.swing.JLabel jBottomBarLabel;

   private javax.swing.JButton jFinishLevel;

   private javax.swing.JLabel jFinishLevelScreen;

   private javax.swing.JLabel jHighlightFirstPipeLabel;

   private javax.swing.JLabel jLevelFailedLabel;

   private javax.swing.JLabel jLevelFailedScreen;

   private javax.swing.JPanel jLevelFailedlPanel;

   private javax.swing.JPanel jLevelFinishedPanel;

   private javax.swing.JLabel jMaxScore;

   private javax.swing.JMenuItem jNewLevelButton;

   private javax.swing.JLabel jNextFirstPipeLabel;

   private javax.swing.JPanel jNextPipesPanel;

   private javax.swing.JLabel jNextSecondPipeLabel;

   private javax.swing.JLabel jNextThirdPipeLabel;

   private javax.swing.JMenuItem jPlayButton;

   private javax.swing.JLabel jPlayerScore;

   private javax.swing.JLabel jRemainingPipes;
   
   private javax.swing.JLabel jRemainingTime;

   private javax.swing.JMenuItem jResetLevel;

   private javax.swing.JPanel jRightBar;

   private javax.swing.JLabel jRightBarLabel;

   private javax.swing.JButton jRotateClockwise;

   private javax.swing.JButton jRotateCounterClockwise;

   private javax.swing.JPanel jSelectPanel;

   private javax.swing.JMenuItem jShowSolution;

   private javax.swing.JMenuItem mainScreenMenuItem;

   private javax.swing.JMenuBar menuBar;
}
