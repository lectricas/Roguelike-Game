package langame;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws InterruptedException, IOException {

        Screen screen = new DefaultTerminalFactory().createScreen();
        TextGraphics tGraphics = screen.newTextGraphics();

        screen.startScreen();
        screen.clear();
        int rows = screen.getTerminalSize().getRows();
        int columns = screen.getTerminalSize().getColumns();
        Level level = new Level(columns, rows);

        while (true) {
            tGraphics.drawImage(new TerminalPosition(0, 0), level.getImage());
            screen.refresh();
            KeyStroke k = screen.pollInput();

            if (k != null) {
                char charr = k.getCharacter();
                if (charr == 'a') {
                    level.moveLeft();
                } else if (charr == 's') {
                    level.moveDown();
                } else if (charr == 'd') {
                    level.moveRight();
                } else if (charr == 'w') {
                    level.moveUp();
                } else if (charr == 49) {
                    break;
                }
                level.refresh();
            }
        }
        screen.stopScreen();
    }
}
