package game;

import java.io.*;

public class Level implements Runnable {

    boolean[][] levelArray;

    public Level(boolean[][] map) {
        this.levelArray = map;
    }

    @Override
    public void run() {
        try {
            long count = 0;
            BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
            String[] command = new String[]{"/bin/sh", "-c", "printf '\\33c\\e[3J'"};
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            while (true) {
                count++;
                log.write(count + "");
                Thread.sleep(1000);
                printLevel(levelArray, log);
                clearScreen(log, processBuilder);
            }
        } catch (Exception e) {

        }
    }

    public static void clearScreen(BufferedWriter log, ProcessBuilder processBuilder) {
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                log.write(line);
//                System.out.print(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printLevel(boolean[][] level, BufferedWriter log) throws IOException {
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level.length; j++) {
                if (level[i][j]) {
                    log.write('#');
                } else {
                    log.write(' ');
                }
            }
            log.newLine();
        }
    }


}
