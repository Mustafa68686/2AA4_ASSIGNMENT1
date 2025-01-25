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
        System.out.println("** Starting Maze Runner");

        //parsing -i flag
        Options opts = new Options();
        opts.addOption("i", true, "flag that represents maze input");
        CommandLineParser my_parser = new DefaultParser();

        //2d array to store maze
        char maze[][] = new char[11][11];

        try {

            //assigning parameters
            CommandLine cmd = my_parser.parse(opts, args);
            String maze_location = cmd.getOptionValue("i");

            System.out.println("**** Reading the maze from file " + maze_location);
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
            System.err.println("/!\\ An error has occured /!\\");
        }
        System.out.println("**** Computing path");
        System.out.println("PATH NOT COMPUTED");
        System.out.println("** End of MazeRunner");
        
        //output grid
        for (int i=0; i<11; i++) {
            for (int j=0; j<11; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println("");
        }

    }
}
