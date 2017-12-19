package com.pipegame;

import com.pipegame.SquarePosition.Entry;
import com.pipegame.SquarePosition.Exit;

public interface PipeRotation {

   public void rotateImageClockwise();

   public void rotateImageCounterclockwise();

   public void setAngle(Entry entry, Exit exit);

}
