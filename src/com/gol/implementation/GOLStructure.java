package com.gol.implementation;

/**
 * Data structure and algorithm of Game of Life
 * */
public class GOLStructure {
    private final int len;

    private final GOLEnum[][] board;

    private final Rule rule;

    public GOLStructure(int len, Rule rule) {
        this.len = len;
        this.rule = rule;

        board = new GOLEnum[len][len];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < this.len; i++) {
            for (int j = 0; j < this.len; j++) {
                board[i][j] = GOLEnum.DEAD;
            }
        }
    }

    public int getLen() {
        return this.len;
    }

    public void updateCellState(int row, int column, GOLEnum cellState) {
        if (row >= 0 && row < this.len && column >= 0 && column < this.len) {
            board[row][column] = cellState;
        }
    }

    public GOLEnum getCellState(int row, int column) {
        if (row >= 0 && row < this.len && column >= 0 && column < this.len) {
            return board[row][column];
        }

        return GOLEnum.DEAD;
    }

    public void scanAndUpdateBoard() {
        for (int i = 0; i < this.len; i++) {
            for (int j = 0; j < this.len; j++) {
                int liveNeighboursCount = getLiveNeighboursCount(i, j);
                board[i][j] = rule.applyRulesAndGetCurrentState(liveNeighboursCount, board[i][j]);
            }
        }
    }

    private int getLiveNeighboursCount(int row, int column) {
        int count = 0;

        int[][] directions = getDefaultDirectionIndexes(row, column);

        for (int[] direction : directions) {
            int newRow = direction[0];
            int newColumn = direction[1];

            if (newRow >= 0 && newRow < this.len && newColumn >= 0 && newColumn < this.len) {
                if (board[newRow][newColumn] == GOLEnum.ALIVE) {
                    count++;
                }
            }
        }

        return count;
    }

    private int[][] getDefaultDirectionIndexes(int row, int column) {
        int[][] directions = new int[8][2];

        // Left
        directions[0][0] = row;
        directions[0][1] = column - 1;

        // Left upper diagonal
        directions[1][0] = row - 1;
        directions[1][1] = column - 1;

        // Upper
        directions[2][0] = row - 1;
        directions[2][1] = column;

        // Right upper diagonal
        directions[3][0] = row - 1;
        directions[3][1] = column + 1;

        // Right
        directions[4][0] = row;
        directions[4][1] = column + 1;

        // Right lower diagonal
        directions[5][0] = row + 1;
        directions[5][1] = column + 1;

        // Lower
        directions[6][0] = row + 1;
        directions[6][1] = column;

        // Left lower diagonal
        directions[7][0] = row + 1;
        directions[7][1] = column - 1;

        return directions;
    }
}
