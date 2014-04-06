// Solver.java
// Assgn4-8Puzzle
//
// Created by mscndle on 4/5/14

import java.util.ArrayList;
import java.util.Iterator;

public class Solver {
    private int totalMoves;
    private Board initial;
    private Board previous;
    private MinPQ<Node> solverPQ;
    private ArrayList<Board> solutionList;

    // Private node class that encapsulates the board
    // manhattan distance and # of moves
    // Implements comparable so that the PQ can use compareTo method
    private class Node implements Comparable<Node> {
        private Board nodeBoard;
        private int manhattan;
        private int moves;
        private int priority;

        Node(Board B, int moves) {
            int L = B.dimension();
            this.nodeBoard = B;
            this.manhattan = B.manhattan();
            this.moves = moves;
            this.priority = this.moves + this.manhattan;
        }

        @Override
        public int compareTo(Node that) {
            if (this.priority < that.priority)    {   return -1;  }
            if (this.priority == that.priority)   {   return 0;   }
            else                                  {   return 1;   }
        }
    }

    // Calculates the solution to the initial board using the A* algorithm
    public Solver(Board initial) {
        this.totalMoves = 0;
        this.initial = initial;
        this.previous = null;
        solutionList = new ArrayList<Board>();
        solverPQ = new MinPQ<Node>();

        if (!this.isSolvable()) {
            System.out.println("Unsolvable!!!");
        } else {

            solverPQ.insert(new Node(this.initial, this.totalMoves));
            Node tempSol = solverPQ.delMin();
            this.solutionList.add(tempSol.nodeBoard);

            while (!tempSol.nodeBoard.isGoal()) {
                this.totalMoves++;
                Iterator<Board> localItr = tempSol.nodeBoard.neighbors().iterator();

                while (localItr.hasNext()) {    // insert all neighbors
                    Board local = localItr.next();
                    if (!local.equals(this.previous)) {
                        solverPQ.insert(new Node(local, this.totalMoves));
                    }
                }

                this.previous = tempSol.nodeBoard;
                tempSol = solverPQ.delMin();
                this.solutionList.add(tempSol.nodeBoard);   // add board to list of states

            }
            System.out.println("Solved!");
        }
    }

    // Checks if the initial board is solvable
    public boolean isSolvable() {
        //TODO: fix
        return true;
    }

    // Returns the min number of moves to solve the initial board
    // Returns -1 if no solution exists
    public int moves() {
        return this.initial.hamming();
    }

    // Returns the sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        return solutionList;
    }

    public static void main(String[] args) {

        int[][] test1 = new int[][] {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board testBoard1 = new Board(test1);

//        System.out.println(testBoard1.toString());
//
//        Iterable<Board> item = testBoard1.neighbors();
//        Iterator<Board> itr = item.iterator();
//
//        while (itr.hasNext()) {
//            System.out.println(itr.next().toString());
//        }


        Solver testSolver1 = new Solver(testBoard1);

        for (Board test: testSolver1.solutionList) {
            System.out.println(test.toString());
        }
    }




}
