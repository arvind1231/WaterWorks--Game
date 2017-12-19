package com.pipegame;


public enum PipeDirection {
   LEFT_TO_RIGHT,
   RIGHT_TO_LEFT,
   UP_TO_DOWN,
   DOWN_TO_UP;

   public enum Elbow {
      LEFT_TO_UP,
      LEFT_TO_DOWN,
      RIGHT_TO_UP,
      RIGHT_TO_DOWN,
      UP_TO_LEFT,
      UP_TO_RIGHT,
      DOWN_TO_LEFT,
      DOWN_TO_RIGHT;
   }
}
