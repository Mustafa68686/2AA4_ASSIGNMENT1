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

    public static MazeUtility main(String[] args) {
        //logger.info("** Starting Maze Runner");

        //parsing -i and -p flag
        Options opts = new Options();
        opts.addOption("i", true, "flag that represents maze input");
        opts.addOption("p", true, "flag that represents path input");
        CommandLineParser my_parser = new DefaultParser();

        //maze storage
        Storage storage = new Storage();

        //variable to store given path
        String given_path = null;

        try {

            //assigning parameters
            CommandLine cmd = my_parser.parse(opts, args);
            String maze_location = cmd.getOptionValue("i");
            given_path = cmd.getOptionValue("p", null);

            //logger.info("**** Reading the maze from file " + maze_location);
            BufferedReader reader = new BufferedReader(new FileReader(maze_location));
            String line;

            //read maze
            while ((line = reader.readLine()) != null) {
                
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {

                        storage.addItem('W');

                    } else if (line.charAt(idx) == ' ') {

                        storage.addItem('P');


                    }
                }

                storage.addRow();

            }
        } catch(Exception e) {
            //logger.error("/!\\ An error has occured /!\\");
            return null;                      
        }

        MazeUtility mu = new MazeUtility(storage);
        if (given_path == null) {
            //logger.info("**** Computing path");
            mu.main();
        } else {
            //logger.info("**** Verifying path");  
            mu.main(given_path);
        }                   
        //logger.info("** End of MazeRunner");  
        return mu;                    
    }
}