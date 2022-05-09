import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.BasicTextImage;

import static com.googlecode.lanterna.TextColor.ANSI.*;

public class Level {

    private static final TextCharacter WALL = new TextCharacter('#', YELLOW, CYAN);
    private static final TextCharacter ROAD = new TextCharacter(' ', BLUE, GREEN);
    private static final TextCharacter HERO = new TextCharacter('@', WHITE, RED);

    TerminalPosition heroPosition;

    int columns;
    int rows;
    private final BasicTextImage image;

    public Level(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        boolean[][] mapArray = new LevelGenerator(columns, rows, 3, 6, 150).generateMap();
        image = new BasicTextImage(columns, rows);
        heroPosition = getHeroInitialPosition(mapArray);
        fillImage(mapArray);
    }

    private void fillImage(boolean[][] mapArray) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (mapArray[i][j]) {
                    image.setCharacterAt(j, i, WALL);
                } else if (j == heroPosition.getColumn() && i == heroPosition.getRow()) {
                    image.setCharacterAt(j, i, HERO);
                } else {
                    image.setCharacterAt(j, i, ROAD);
                }
            }
        }
    }

    public void refresh() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (image.getCharacterAt(j, i) == WALL) {
                    image.setCharacterAt(j, i, WALL);
                } else if (j == heroPosition.getColumn() && i == heroPosition.getRow()) {
                    image.setCharacterAt(j, i, HERO);
                } else {
                    image.setCharacterAt(j, i, ROAD);
                }
            }
        }
    }

    public TerminalPosition getHeroInitialPosition(boolean[][] mapArray) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!mapArray[i][j]) {
                    return new TerminalPosition(j, i);
                }
            }
        }
        throw new IllegalStateException("cant be");
    }

    public BasicTextImage getImage() {
        return image;
    }

    public void moveLeft() {
        TerminalPosition newPosition = heroPosition.withColumn(heroPosition.getColumn() - 1);
        if (!isWall(newPosition)) {
            heroPosition = newPosition;
        }
    }

    public void moveRight() {
        TerminalPosition newPosition = heroPosition.withColumn(heroPosition.getColumn() + 1);
        if (!isWall(newPosition)) {
            heroPosition = newPosition;
        }
    }

    public void moveDown() {
        TerminalPosition newPosition = heroPosition.withRow(heroPosition.getRow() + 1);
        if (!isWall(newPosition)) {
            heroPosition = newPosition;
        }
    }

    public void moveUp() {
        TerminalPosition newPosition = heroPosition.withRow(heroPosition.getRow() - 1);
        if (!isWall(newPosition)) {
            heroPosition = newPosition;
        }
    }

    private boolean isWall(TerminalPosition position) {
        return image.getCharacterAt(position) == WALL;
    }
}
