package com.pipegame;

public class Match<T, E> {

   public T first;

   public E second;

   public Match() {

   }

   public Match(T first, E second) {
      this.first = first;
      this.second = second;
   }

   @Override
   public String toString() {
      return "Pair [first=" + first + ", second=" + second + "]";
   }

}
