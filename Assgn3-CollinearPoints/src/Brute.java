// Brute.java
// Assgn3-CollinearPoints
//
// Created by mscndle on 3/29/14

public class Brute {

    public static void main(String[] args) {
        In in = new In(args[0]);                //input file
        int N = Integer.valueOf(in.readLine()); //total # of points
        Point[] ptrArr = new Point[N];  //stores all points in same order as in file

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        int C = 0;
        while (C < N) { //read all points into a points array
            int x = in.readInt();
            int y = in.readInt();

            ptrArr[C] = new Point(x, y);
            ptrArr[C].draw();
            C++;
        }

        Point[] selectedFour = new Point[4];    //helpful for sorting

        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    for (int l = k+1; l < N; l++) {

                        double slope1 = ptrArr[i].slopeTo(ptrArr[j]);
                        double slope2 = ptrArr[i].slopeTo(ptrArr[k]);
                        double slope3 = ptrArr[i].slopeTo(ptrArr[l]);

                        if (slope1 == slope2 && slope2 == slope3) { //4 points on a line

                            selectedFour[0] = ptrArr[i];
                            selectedFour[1] = ptrArr[j];
                            selectedFour[2] = ptrArr[k];
                            selectedFour[3] = ptrArr[l];

                            manuallySortArrays(selectedFour);

                            System.out.println(selectedFour[0].toString() + " -> " +
                                               selectedFour[1].toString() + " -> " +
                                               selectedFour[2].toString() + " -> " +
                                               selectedFour[3].toString());

                            selectedFour[0].drawTo(selectedFour[3]);
                        }

                    }
                }
            }
        }


    }

    private static void manuallySortArrays(Point[] miniPointsArray) {
        Point temp;

        for (int i = 0; i < miniPointsArray.length; i++) {
            for (int j = i+1; j < miniPointsArray.length; j++) {

                if (miniPointsArray[i].compareTo(miniPointsArray[j]) == 1) {
                    temp = miniPointsArray[i];
                    miniPointsArray[i] = miniPointsArray[j];
                    miniPointsArray[j] = temp;
                }

            }
        }

    }
}
