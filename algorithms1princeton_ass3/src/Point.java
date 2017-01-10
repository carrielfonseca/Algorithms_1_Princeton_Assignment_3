
import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Fabio
 */
public class Point implements Comparable<Point> {
    
    private final int x;
    private final int y;

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }                            // draws this point
 
    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    
     public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    
    
   @Override
   public int compareTo(Point that)  {    // compare two points by y-coordinates, breaking ties by x-coordinates
      int i;
      if (this.y < that.y) {
          i = -1;
      }
      else if (this.y > that.y) {
          i = 1;
      }
      else {
          if (this.x < that.x) {
              i = -1;
          }
          else if (this.x > that.x) {
              i = 1;
          }
          else {
              i = 0;
          }
      }
      return i;
   }
           
   public double slopeTo(Point that)    {   // the slope between this point and that point
       double slope;
       if (that.y == this.y && that.x == this.x) {
           slope = Double.NEGATIVE_INFINITY;
       }
       else if (that.x == this.x) {
           slope = Double.POSITIVE_INFINITY;
       }
       else if (that.y == this.y) {
           slope = +0;
       }       
       else {
           slope = (double) (that.y - this.y) / (that.x - this.x);           
       } 
       return slope;
   }
   
   private class BySlope implements Comparator<Point> {
       public int compare(Point a, Point b) {
           int i;
           if ((slopeTo(a) - slopeTo(b)) < 0) {
               i = -1;
           }
           else if ((slopeTo(a) - slopeTo(b)) > 0) {
               i = 1;
           }
           else {
               i = 0;
           }
        return i;   
       }
   }
   
   public Comparator<Point> slopeOrder()     {         // compare two points by slopes they make with this point   
       return new BySlope();    
   }
   
    public static void main(String[] args) {
        int x = 50;
        int y = 13;
        double d = (double) x/y;
        System.out.println(d);
    }
    
}
