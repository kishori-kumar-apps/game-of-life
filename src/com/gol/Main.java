package com.gol;

import com.gol.implementation.GOLEnum;
import com.gol.implementation.GameOfLife;

public class Main {
    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife(10);

        gameOfLife.updateGOLState(1, 2, GOLEnum.ALIVE);
        gameOfLife.updateGOLState(2, 3, GOLEnum.ALIVE);
        gameOfLife.updateGOLState(3, 1, GOLEnum.ALIVE);
        gameOfLife.updateGOLState(3, 2, GOLEnum.ALIVE);
        gameOfLife.updateGOLState(3, 3, GOLEnum.ALIVE);

        System.out.println("-----------Initial State of game of life-------------");
        gameOfLife.printGameOfLifeState();

        for (int i = 0; i < 3; i++) {
            System.out.println("Generation Number : " + (i + 1));
            gameOfLife.nextGeneration();
        }
    }
}