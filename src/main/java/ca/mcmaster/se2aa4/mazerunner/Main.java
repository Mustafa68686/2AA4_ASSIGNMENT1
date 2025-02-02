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
        char maze[][] = new char[0][0];

        String given_path = null;

        try {

            //assigning parameters
            CommandLine cmd = my_parser.parse(opts, args);
            String maze_location = cmd.getOptionValue("i");
            given_path = cmd.getOptionValue("p", null);

            logger.info("**** Reading the maze from file " + maze_location);
            BufferedReader reader = new BufferedReader(new FileReader(maze_location));
            String line;

            //read maze into 2d array
            while ((line = reader.readLine()) != null) {

                char inputline[] = new char[0];
                
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        //System.out.print("WALL ");                      **************************************

                        inputline = MyUtitily.add1D(inputline, 'W');

                    } else if (line.charAt(idx) == ' ') {
                        //System.out.print("PASS ");                      **************************************

                        inputline = MyUtitily.add1D(inputline, 'P');

                    }
                }
                //System.out.print(System.lineSeparator());                      **************************************
                maze = MyUtitily.addRow(maze, inputline);

            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
            return;                      
        }

        MazeUtility mu = new MazeUtility(maze);
        if (given_path == null) {
            logger.info("**** Computing path");
            mu.main();
        } else {
            logger.info("**** Verifying path");  
            mu.main(given_path);
        }                   
        logger.info("** End of MazeRunner");                      
    }

}