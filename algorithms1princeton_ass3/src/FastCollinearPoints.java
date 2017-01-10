
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedQueue;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

// http://stackoverflow.com/questions/17631111/netbeans-java-project-path-of-text-file 

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fabio
 */
public class FastCollinearPoints {
   private int n = 0;  //number of line segments
   private Point[] points;  //points of the input
   private Point[] pointsOrganizedBySlope;  //points organized by slope
   
   private LinkedQueue<LineSegment> lineSegmentsFoundQ = new LinkedQueue<LineSegment>();
   private LineSegment[] lineSegmentsFound;   //lineSegments that have been found
   
   
   public FastCollinearPoints(Point[] points)   { // finds all line segments containing 4 points
       for(int i = 0; i < points.length - 1; i++ ) {
           if (points[i] == null) throw new java.lang.NullPointerException("There is a null element in the array");           
       }      
       this.points = copyArray(points,0);
       Arrays.sort(this.points);
       //check for invalid entries (null or duplicated points
       for(int i = 0; i < this.points.length - 1; i++ ) {        
           if (this.points[i].compareTo(this.points[i+1]) == 0)  {
               throw new java.lang.IllegalArgumentException("There are repeated points in this array");
           }
       }   
       int test;
       pointsOrganizedBySlope =  new Point[points.length];  
       pointsOrganizedBySlope = copyArray(this.points,0);
      // start a loop in each point AND constructs the array that will be sorted by slope
       for (int i = 0; i < points.length; i++) {          
            Point pointEdgeLower = this.points[i];
            Point pointEdgeHigher = this.points[i];           
            Arrays.sort(pointsOrganizedBySlope, this.points[i].slopeOrder());          
            int countAdjacentPoints = 2; //kinda tricky here. needs to be two
            for (int j = 0; j < pointsOrganizedBySlope.length - 1; j++) {
               if (this.points[i].slopeTo(pointsOrganizedBySlope[j]) == this.points[i].slopeTo(pointsOrganizedBySlope[j+1])) {
                   if (countAdjacentPoints == 2) {
                      pointEdgeLower = lowerPoint(pointsOrganizedBySlope[j], pointsOrganizedBySlope[j+1]);
                      //pointEdgeLower = lowerPoint(pointEdgeLower, pointsOrganizedBySlope[j + 1]);
                      pointEdgeHigher= higherPoint(pointsOrganizedBySlope[j], pointsOrganizedBySlope[j+1]);
                   }
                   else {     
                       pointEdgeLower = lowerPoint(pointEdgeLower,    pointsOrganizedBySlope[j+1]);
                       pointEdgeHigher = higherPoint(pointEdgeHigher, pointsOrganizedBySlope[j+1]);
                    }                 
                   countAdjacentPoints++;
               }
               else if (this.points[i].slopeTo(pointsOrganizedBySlope[j]) != this.points[i].slopeTo(pointsOrganizedBySlope[j+1])) {
                   if (countAdjacentPoints >= 4 && pointEdgeLower.compareTo(this.points[i]) > 0) {
                      lineSegmentsFoundQ.enqueue(new LineSegment(this.points[i], pointEdgeHigher)); 
                      n++;
                   }
                   countAdjacentPoints = 2;
               }                
               if (countAdjacentPoints >= 4 && (j+1) == (pointsOrganizedBySlope.length - 1)) {
                    if (pointEdgeLower.compareTo(this.points[i]) > 0) {
                        lineSegmentsFoundQ.enqueue(new LineSegment(this.points[i], pointEdgeHigher)); 
                        n++;  
                    }
                   // countAdjacentPoints = 2;
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
        

  private Point higherPoint(Point a, Point b) {
      Point p;
      if (a.compareTo(b) >= 0) {
         p = a; 
      }
      else {
          p = b;
      }
      return p;
  }
  
  private Point lowerPoint(Point a, Point b) {
      Point p;
      if (a.compareTo(b) <= 0) {
         p = a; 
      }
      else {
          p = b;
      }
      return p;
  }
   
   private static void printArray(Object [] object) {
      int i = 0;
      while (i < object.length && object[i] != null) {  
          System.out.println(object[i]); 
      i++;    
      }       
   }
   
   /*used to create a copy of the points starting from index j (particularly in the constructor*/
   private static Point[] copyArray(Point [] point, int j) {
      Point[] pointCopy = new Point[point.length - j];       
      for (int i = j; i < point.length; i++) { 
          pointCopy[i-j] = point[i];
      }  
      return pointCopy;
   }
   
   public static void main(String[] args) throws FileNotFoundException {
       
       //In in = new In(args[0]); 
             
       Point[] points = new Point[11];
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
       points[10] = new Point(78,60);
              
       
     /*  Point[] points = new Point[5];
       
        points[0] = new Point(1000,17000);
       points[1] = new Point(28000 ,29000);
       points[2] = new Point(22000,29000);
       points[3] = new Point(2000,29000);
       points[4] = new Point(4000, 29000);
      
      */
      
       
       
       //Scanner in = new Scanner(new FileReader("input40.txt"));
       /*
       int n = in.nextInt();
       System.out.println("n is " + n);
       
       Point[] points = new Point[n];
       
    for (int i = 0; i < n; i++) {
        int x = in.nextInt();
        int y = in.nextInt();
        points[i] = new Point(x, y);
    } 
            */  
    
       FastCollinearPoints fast = new FastCollinearPoints(points);
    

     // printArray(fast.pointsOrganizedBySlope);
      System.out.println("*****");
      printArray(fast.segments());
       
       
       
      
             
       
      
      
       
       //printArray(points);
       //printArray(brute.segments());
      
               
       
    }  
}