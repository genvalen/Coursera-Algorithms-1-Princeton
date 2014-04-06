// Board.java
// Assgn4-8Puzzle
//
// Created by mscndle on 4/5/14


import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private static final int[][] goalBoard = new int[][] {{1, 2, 3},
                                                          {4, 5, 6},
                                                          {7, 8, 0}};

    private int dim;
    private int[][] board;
    private ArrayList<Board> boardList;

    public Board(int[][] blocks) {
        this.dim = blocks.length;
        this.board = new int[this.dim][this.dim];
        this.boardList = new ArrayList<Board>();

        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                this.board[i][j] = blocks[i][j];
            }
        }
    }

    // Returns the board dimension N
    public int dimension() {
        return this.dim;
    }

    // Returns the hamming distance of the board - # of blocks out of place
    public int hamming() {
        int hamming = 0;

        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                if (i == this.dim-1 && j == this.dim-1) {   break;  }
                else if (this.board[i][j] != Board.goalBoard[i][j]) {   hamming++;  }
            }
        }

        return hamming;
    }

    // Returns the sum of manhattan distances between the board and the goal
    public int manhattan() {
        int manhattan = 0;
        int iExp, jExp;

        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                //if (i == this.dim-1 && j == this.dim-1) {   break;  }
                if (this.board[i][j] != Board.goalBoard[i][j] && this.board[i][j] != 0) {

                    iExp = (this.board[i][j]-1) / 3;    //expected position of this block
                    jExp = (this.board[i][j]-1) % 3;

                    manhattan += (Math.abs(iExp - i) + Math.abs(jExp - j));
                }
            }
        }

        return manhattan;
    }

    // Is this board the goal board?
    public boolean isGoal() {
        return Arrays.deepEquals(this.board, Board.goalBoard);
    }

    // Returns a board obtained by exchanging 2 adjacent blocks in the same row
    public Board twin() {
        return generateDup(this, 0, 0, 0, 1);
    }

    // Generates a duplicate board by swapping indexes in positions a, b
    private Board generateDup(Board board, int x1, int y1, int x2, int y2) {
        int[][] newBoard = new int[this.dim][this.dim];

        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                newBoard[i][j] = board.board[i][j];
            }
        }

        int temp = board.board[x1][y1];
        newBoard[x1][y1] = board.board[x2][y2];
        newBoard[x2][y2] = temp;

        return new Board(newBoard);
    }

    // Does this board equal y?
    public boolean equals(Object y) {
        if (y == null) {    return false;   }
        if (y.getClass() != this.getClass()) {    return false;   }
        else {
            Board that = (Board) y;
            return Arrays.deepEquals(this.board, that.board);
        }
    }

    // All neighboring boards
    public Iterable<Board> neighbors() {
        this.calculateNeighbors(this);
        return this.boardList;
    }

    // Calculates the number of possible moves
    // Adds it to the boardList array list
    private void calculateNeighbors(Board board) {
        int iZ = 0, jZ = 0; //indexes of the element 0
        int len = board.dimension();

        for (int i = 0; i < len; i++) { // locating the zero
            for (int j = 0; j < len; j++) {
                if (board.board[i][j] == 0) {
                    iZ = i;
                    jZ = j;
                    break;
                }
            }
        }

        // CORNERS - 2 possible moves
        if (iZ == 0 && jZ == 0) {   // top left
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ+1, jZ));
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ, jZ+1));
        } else if (iZ == 0 && jZ == len-1) {    // top right
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ+1, jZ));
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ, jZ-1));
        } else if (iZ == len-1 && jZ == 0) {    // bottom left
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ-1, jZ));
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ, jZ+1));
        } else if (iZ == len-1 && jZ == len-1) {    // bottom right
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ-1, jZ));
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ, jZ-1));
        }

        // EDGES - 3 possible moves
        else if (iZ == 0) {   // top row
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ+1, jZ));
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ, jZ-1));
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ, jZ+1));
        } else if (jZ == 0) {   // left row
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ-1, jZ));
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ+1, jZ));
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ, jZ+1));
        } else if (iZ == len-1) {   // bottom row
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ-1, jZ));
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ, jZ-1));
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ, jZ+1));
        } else if (jZ == len-1) {   // right row
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ-1, jZ));
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ+1, jZ));
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ, jZ-1));
        }

        // INTERIOR - 4 possible moves
        else {
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ-1, jZ));
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ+1, jZ));
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ, jZ-1));
            this.boardList.add(board.generateDup(board, iZ, jZ, iZ, jZ+1));
        }
    }

    // String representation of the board
    public String toString() {
        StringBuilder board = new StringBuilder();

        for (int i = 0; i < this.dim; i++) {
            for (int j = 0; j < this.dim; j++) {
                board.append(this.board[i][j]);
                board.append(" ");
            }
            board.append("\n");
        }

        return board.toString();
    }

}
