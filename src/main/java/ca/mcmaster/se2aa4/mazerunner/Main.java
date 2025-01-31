package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        //parsing -i and -p flag
        Options opts = new Options();
        opts.addOption("i", true, "flag that represents maze input");
        opts.addOption("p", true, "flag that represents path input");
        CommandLineParser my_parser = new DefaultParser();

        //2d array to store maze
        char maze[][] = new char[11][11];

        try {

            //assigning parameters
            CommandLine cmd = my_parser.parse(opts, args);
            String maze_location = cmd.getOptionValue("i");

            logger.info("**** Reading the maze from file " + maze_location);
            BufferedReader reader = new BufferedReader(new FileReader(maze_location));
            String line;

            //read maze into 2d array
            int height = 0;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");

                        maze[height][idx] = 'W';

                    } else if (line.charAt(idx) == ' ') {
                        System.out.print("PASS ");

                        maze[height][idx] = 'P';

                    }
                }
                System.out.print(System.lineSeparator());

                height += 1; //increment height

            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
        
        //find_path(maze);
        //check_path(maze, "FLFRFFLFFFFR");

        System.out.println("new");
        MazeUtility mu = new MazeUtility(maze);
        mu.main();
        System.out.println("");
    }

}










class MazeUtility {
    private char maze[][];
    public MazeUtility(char[][] array) {
        maze = array;
    }
    public void main() {
        //starting postition, one step in
        int pos[] = {find_enterence(), 1};
        Compass compass = new Compass('E');
        String path = "F";
        //compute path
        path = rightHandMethod(pos, path, compass);
        System.out.println(path);
        //verify path
        String manualPath = "FLFRFFLFFFFFFRFFFFRFFLFFRFFLF";
        String status = verifyPath(manualPath);
        System.out.println(status);
    }
    //find start height
    private int find_enterence() {
        int start_height = 0;
        for (int i=0; i<maze.length; i++) {
            if (maze[i][0] == 'P') {
                start_height = i;
            }
        }
        return start_height;
    }
    //use the right hand method
    private String rightHandMethod(int[] pos, String path, Compass compass) {
        while (true) {
            //if you reach the end, break
            if (pos[1] == maze.length-1) {
                break;
            }
            char facing = compass.facing;
            //check right hand and front
            char right_hand = 'X'; char front = 'X'; int pos_x = pos[1]; int pos_y = pos[0];
            switch (facing) {
                case 'N':
                    right_hand = maze[pos_y][pos_x-1];
                    front = maze[pos_y-1][pos_x];
                    break;
                case 'E':
                    right_hand = maze[pos_y-1][pos_x];
                    front = maze[pos_y][pos_x+1];
                    break;
                case 'S':
                    right_hand = maze[pos_y][pos_x+1];
                    front = maze[pos_y+1][pos_x];
                    break;
                case 'W':
                    right_hand = maze[pos_y+1][pos_x];
                    front = maze[pos_y][pos_x-1];
                    break;
            }
            //advance
            if (right_hand == 'W' && front == 'P') {
                compass.forward(pos);
                path = path + "F";
            //turn right
            } else if (right_hand == 'W' && front == 'W') {
                compass.turnRight();
                path = path + "R";
            //turn left
            } else if (right_hand == 'P') {
                compass.turnLeft();
                compass.forward(pos);
                path = path + "L";
                path = path + "F";
            } else {}
        }
        return path;
    }
    public String verifyPath(String path) {

        char[][] nummaze = maze;              /*********************************/

        //get starting position
        int pos[] = {find_enterence(), 0};
        Compass compass = new Compass('E');
        //loop though instructions
        for (int i = 0; i<path.length(); i++) {

            if (!(path.charAt(i) == ' ')) {

            char symbol = path.charAt(i);
            pos = move(pos, compass, symbol);

            if (maze[pos[0]][pos[1]] == 'W') {
                System.out.println("wall");
                return "FAIL!";
            }
            }
        }
        if (pos[1] == maze.length-1) {
            return "SUCCESS!";
        } else {
            return "FAIL!";
        }
    }
    public int[] move(int[] pos, Compass compass, char move_direction) {
        switch (move_direction) {
            case 'F':
                pos = compass.forward(pos); break;
            case 'R':
                compass.turnRight(); break;
            case 'L':
                compass.turnLeft();
        }
        return pos;
    }
}

class Compass {
    public char facing = 'N';
    public Compass(char direction) {
        facing = direction;
    }
    public void turnLeft() {
        switch (facing) {
            case 'N':
                facing = 'W'; break;
            case 'E':
                facing = 'N'; break;
            case 'S':
                facing = 'E'; break;
            case 'W':
                facing = 'S'; break;
        }
    }
    public void turnRight() {
        switch (facing) {
            case 'N':
                facing = 'E'; break;
            case 'E':
                facing = 'S'; break;
            case 'S':
                facing = 'W'; break;
            case 'W':
                facing = 'N'; break;
        }
    }
    public int[] forward(int[] pos) {
        switch (facing) {
            case 'N':
                pos[0] -= 1; break;
            case 'E':
                pos[1] += 1; break;
            case 'S':
                pos[0] += 1; break;
            case 'W':
                pos[1] -= 1; break;
        }
        return pos;
    }
}