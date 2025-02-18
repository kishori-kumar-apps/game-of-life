package com.gol.implementation;

/**
 * A kind of front controller for Game of Life
 * */
public class GameOfLife {
    private final GOLStructure gOLStructure;

    public GameOfLife() {
        this.gOLStructure = new GOLStructure(25, new Rule());
    }

    public GameOfLife(int len) {
        this.gOLStructure = new GOLStructure(len, new Rule());
    }

    public GameOfLife(int len, Rule rule) {
        this.gOLStructure = new GOLStructure(len, rule);
    }

    public void updateGOLState(int row, int column, GOLEnum state) {
        gOLStructure.updateCellState(row, column, state);
    }

    public void nextGeneration() {
        gOLStructure.scanAndUpdateBoard();
        printGameOfLifeState();
    }

    public void printGameOfLifeState() {
        for (int i = 0; i < gOLStructure.getLen(); i++) {
            for (int j = 0; j < gOLStructure.getLen(); j++) {
                System.out.print(gOLStructure.getCellState(i, j) + " ");
            }
            System.out.println();
        }
    }
}
