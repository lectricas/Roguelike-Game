package game;

import langame.LevelGenerator;

import java.io.Console;
import java.io.IOException;
import java.io.Reader;

public class GameApp {

    public static void main(String[] args) throws IOException, InterruptedException {

        boolean[][] mapArray = new LevelGenerator(30, 30, 3, 6, 150).generateMap();

        Level map = new Level(mapArray);
        Thread t = new Thread(map);
        t.start();

        String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
        Runtime.getRuntime().exec(cmd).waitFor();
        Console console = System.console();
        Reader reader = console.reader();
        while (true) {
            int charr = reader.read();
            if (charr == 'a') {

            } else if (charr == 's') {

            } else if (charr == 'd') {

            } else if (charr == 'w') {

            } else if (charr == 49) {
                break;
            }
        }
        cmd = new String[]{"/bin/sh", "-c", "stty sane </dev/tty"};
        Runtime.getRuntime().exec(cmd).waitFor();
    }
}