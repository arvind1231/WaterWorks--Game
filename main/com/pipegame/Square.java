package com.pipegame;



public class Square {

   private Pipeline pipe;

 
   public void positionPipe(Pipeline pipe) {
      this.pipe = pipe;
   }


   public Pipeline getPipe() {
      return pipe;
   }


   public void clear() {
      pipe = null;
   }

   public boolean isEmpty() {
      return pipe == null;
   }

}
