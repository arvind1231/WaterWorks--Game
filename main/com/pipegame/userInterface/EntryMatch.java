package com.pipegame.userInterface;

import com.pipegame.SquarePosition.Entry;


public class EntryMatch {

   public final String imageName;

   public final Entry from;

   public EntryMatch(String imageName, Entry from) {
      this.imageName = imageName;
      this.from = from;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = (prime * result) + ((from == null) ? 0 : from.hashCode());
      result = (prime * result)
            + ((imageName == null) ? 0 : imageName.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      EntryMatch other = (EntryMatch) obj;
      if (from != other.from) {
         return false;
      }
      if (imageName == null) {
         if (other.imageName != null) {
            return false;
         }
      } else if (!imageName.equals(other.imageName)) {
         return false;
      }
      return true;
   }
}
