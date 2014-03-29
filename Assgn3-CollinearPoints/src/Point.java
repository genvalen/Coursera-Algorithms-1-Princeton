// Point.java
// Assgn3-CollinearPoints
//
// Created by mscndle on 3/29/14

import java.util.Comparator;

public class Point {
    public final Comparator<Point> SLOPE_ORDER;

    private final int x;    //this is an immutable object
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;

        SLOPE_ORDER = new SLOPE_ORDER(this);
    }

    // Private class implementing the comparator
    private static class SLOPE_ORDER implements Comparator<Point> {
        Point P;

        public SLOPE_ORDER(Point P) {
            this.P = P;
        }

        public int compare(Point A, Point B) {
            if (P.slopeTo(A) < P.slopeTo(B)) {
                return -1;
            } else if (P.slopeTo(A) > P.slopeTo(B)) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    // Plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // Draw the line segment from this point to that point
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // String representation
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // Is this point lexicographically smaller than that point
    // comparing y-coordinates and breaking ties with x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y) {
            return -1;
        } else if (this.y == that.y) {
            if (this.x < that.x) {
                return -1;
            } else if (this.x == that.x) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

    // Slope between this point and that point
    public double slopeTo(Point that) {
        if (that.y == this.y) {         //horizontal line
            return 0.0;
        } else if (that.x == this.x) {  //vertical line
            return Double.MAX_VALUE;
        } else if (that == this) {      //slope of a point with  itself
            return Double.MIN_VALUE;
        } else {
            return ((that.y - this.y) / (that.x - this.x));
        }
    }
}
