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

        SLOPE_ORDER = new SLOPE_ORDER();
    }

    // Private class implementing the comparator
    private final class SLOPE_ORDER implements Comparator<Point> {
        public int compare(Point A, Point B) {
            if (Point.this.slopeTo(A) < Point.this.slopeTo(B)) {
                return -1;
            } else if (Point.this.slopeTo(A) > Point.this.slopeTo(B)) {
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
        if (that == this) {         //horizontal line
            return Double.POSITIVE_INFINITY;
        } else if (that.x == this.x) {  //vertical line
            return Double.POSITIVE_INFINITY;
        } else if (that.y == this.y) {      //slope of a point with  itself
            return 0.0;
        } else {
            double num = that.y - this.y;
            double den = that.x - this.x;
            return num / den;
        }
    }
}
