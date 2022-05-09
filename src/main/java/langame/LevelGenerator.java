package langame;

import java.util.Random;

public class LevelGenerator {

    private int width;
    private int height;
    private Random r = new Random();

    private float chanceToStartAlive = 0.45f;

    private int deathLimit;
    private int birthLimit;

    private int numberOfSteps;

    public LevelGenerator(int width, int height, int deathLimit, int birthLimit, int numberOfSteps) {
        this.width = width;
        this.height = height;
        this.deathLimit = deathLimit;
        this.birthLimit = birthLimit;
        this.numberOfSteps = numberOfSteps;
    }

    public boolean[][] generateMap() {
        //Create a new map
        boolean[][] cellmap = new boolean[height][width];
        //Set up the map with random values
        cellmap = initialiseMap(cellmap);
        //And now run the simulation for a set number of steps
        for (int i = 0; i < numberOfSteps; i++) {
            cellmap = doSimulationStep(cellmap);
        }
        return frame(cellmap);
    }

    private boolean[][] initialiseMap(boolean[][] map) {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                if (r.nextFloat() < chanceToStartAlive) {
                    map[x][y] = true;
                }
            }
        }
        return map;
    }

    private boolean[][] doSimulationStep(boolean[][] oldMap) {
        boolean[][] newMap = new boolean[height][width];
        //Loop over each row and column of the map
        for (int x = 0; x < oldMap.length; x++) {
            for (int y = 0; y < oldMap[0].length; y++) {
                int nbs = countAliveNeighbours(oldMap, x, y);
                //The new value is based on our simulation rules
                //First, if a cell is alive but has too few neighbours, kill it.
                if (oldMap[x][y]) {
                    if (nbs < deathLimit) {
                        newMap[x][y] = false;
                    } else {
                        newMap[x][y] = true;
                    }
                } //Otherwise, if the cell is dead now, check if it has the right number of neighbours to be 'born'
                else {
                    if (nbs > birthLimit) {
                        newMap[x][y] = true;
                    } else {
                        newMap[x][y] = false;
                    }
                }
            }
        }
        return newMap;
    }

    private boolean[][] frame(boolean[][] map) {
        for (int i = 0; i < width; i++) {
            map[0][i] = true;
            map[height - 1][i] = true;
        }

        for (int i = 0; i < height; i++) {
            map[i][0] = true;
            map[i][width - 1] = true;
        }
        return map;
    }

    //Returns the number of cells in a ring around (x,y) that are alive.
    private int countAliveNeighbours(boolean[][] map, int x, int y) {
        int count = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int neighbour_x = x + i;
                int neighbour_y = y + j;
                //If we're looking at the middle point
                if (i == 0 && j == 0) {
                    //Do nothing, we don't want to add ourselves in!
                }
                //In case the index we're looking at it off the edge of the map
                else if (neighbour_x < 0 || neighbour_y < 0 || neighbour_x >= map.length || neighbour_y >= map[0].length) {
                    count = count + 1;
                }
                //Otherwise, a normal check of the neighbour
                else if (map[neighbour_x][neighbour_y]) {
                    count = count + 1;
                }
            }
        }
        return count;
    }
}
