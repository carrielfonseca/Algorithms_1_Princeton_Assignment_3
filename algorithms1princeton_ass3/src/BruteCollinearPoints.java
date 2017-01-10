
import edu.princeton.cs.algs4.LinkedQueue;
import java.util.Arrays;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fabio
 */
public class BruteCollinearPoints {
   private int n = 0;  //number of line segments
   private Point[] points;  //points of the input
   private LinkedQueue<LineSegment> lineSegmentsFoundQ = new LinkedQueue<LineSegment>();
   private LineSegment[] lineSegmentsFound;   //lineSegments that have been found
   
   public BruteCollinearPoints(Point[] points)   { // finds all line segments containing 4 points
       for(int i = 0; i < points.length - 1; i++ ) {
           if (points[i] == null) throw new java.lang.NullPointerException("There is a null element in the array");           
       }      
       this.points = copyArray(points);
       Arrays.sort(this.points);
       //check for invalid entries (null or duplicated points
       for(int i = 0; i < this.points.length - 1; i++ ) {        
           if (this.points[i].compareTo(this.points[i+1]) == 0)  {
               throw new java.lang.IllegalArgumentException("There are repeated points in this array");
           }
       }            
      for (int i = 0; i < (this.points.length - 3); i++) {
          for (int j = i + 1; j <  (this.points.length - 2); j++) {
              for (int k = j + 1; k < (this.points.length - 1); k++) {
                  for (int l = k + 1; l < (this.points.length); l++)    {
                      if (isLineSegmentWith4Points(this.points[i],this.points[j],this.points[k],this.points[l])) {
                          //System.out.println("n i equald to "  + n);
                          //System.out.println("i " + i + " j " + j + " k " + k + " l " + l);
                          lineSegmentsFoundQ.enqueue(new LineSegment(this.points[i], this.points[l])); 
                          n++;
                      }                      
                  }
              }
          }
      } 
      //passes over to the array of the LineSegments
      int i = 0;
       lineSegmentsFound = new LineSegment[n];
       while (!lineSegmentsFoundQ.isEmpty()) {
           lineSegmentsFound[i] = lineSegmentsFoundQ.dequeue();
           i++;
       }
   }
   
   public int numberOfSegments() {
       return n;
   }
   
   public LineSegment[] segments() {       
       return lineSegmentsFound;
   }
           
           
   private boolean isLineSegmentWith4Points(Point a, Point b, Point c, Point d) {
       boolean bo = false;
       if (a.slopeTo(b) == a.slopeTo(c) && a.slopeTo(b) == a.slopeTo(d)) {
           bo = true;
       }
       return bo;
   }
   
  
   
   private static void printArray(Object [] object) {
      int i = 0;
      while (i < object.length && object[i] != null) {  
          System.out.println(object[i]); 
      i++;    
      }       
   }
   
   private static Point[] copyArray(Point [] point) {
      Point[] pointCopy = new Point[point.length];       
      for (int i = 0; i < point.length; i++) { 
          pointCopy[i] = point[i];
      }  
      return pointCopy;
   }
   
   public static void main(String[] args) {
       Point[] points = new Point[10];
       points[0] = new Point(13,10);
       points[1] = new Point(26,20);
       points[2] = new Point(65,50);
       points[3] = new Point(4,8);
       points[4] = new Point(39,30);
       points[5] = new Point(6,12);
       points[6] = new Point(13,12);
       points[7] = new Point(52,40);
       points[8] = new Point(1,2);
       points[9] = new Point(2,4);
               
     
       //printArray(points);
       
       BruteCollinearPoints brute = new BruteCollinearPoints(points);
       printArray(brute.points);
       //printArray(brute.segments());
       System.out.println("*****");
       printArray(brute.segments());
       
       
       
      
             
       
      
      
       
       //printArray(points);
       //printArray(brute.segments());
      
               
       
    }  
}