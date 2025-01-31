package ca.mcmaster.se2aa4.mazerunner;

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
        //System.out.println(path);
        //MyUtitily.outputPathNeat(path);

        //factorize path
        String factorized_path = factorize(path);
        System.out.println(factorized_path);
        //MyUtitily.outputPathNeat(factorized_path);
    }

    //verify path
    public void main(String path) {
        String status = verifyPath(path);
        System.out.println(status);
    }

    //find start height
    private int find_enterence() {
        int start_height = 0;
        for (int i=0; i<maze.length; i++) {

            if (maze[i][0] == 'P') {
                start_height = i;
                break;
            }
        }
        return start_height;
    }
    //use the right hand method
    private String rightHandMethod(int[] pos, String path, Compass compass) {

        while (true) {

            //if you reach the end, break
            if (pos[1] == maze[0].length-1) {
                break;
            }
            char facing = compass.facing;
            //check right hand and front
            char right_hand = 'X'; char front = 'X'; int pos_x = pos[1]; int pos_y = pos[0];
            switch (facing) {
                case 'N':
                    right_hand = maze[pos_y][pos_x+1];
                    front = maze[pos_y-1][pos_x];
                    break;
                case 'E':
                    right_hand = maze[pos_y+1][pos_x];
                    front = maze[pos_y][pos_x+1];
                    break;
                case 'S':
                    right_hand = maze[pos_y][pos_x-1];
                    front = maze[pos_y+1][pos_x];
                    break;
                case 'W':
                    right_hand = maze[pos_y-1][pos_x];
                    front = maze[pos_y][pos_x-1];
                    break;
            }
            //advance
            if (right_hand == 'W' && front == 'P') {
                compass.forward(pos);
                path = path + "F";
            //turn left
            } else if (right_hand == 'W' && front == 'W') {
                //compass.turnRight(); path = path + "R";
                compass.turnLeft(); path = path + "L";
            //turn right
            } else if (right_hand == 'P') {
                //compass.turnLeft(); path = path + "L";
                compass.turnRight(); path = path + "R";
                compass.forward(pos); path = path + "F";
            } else {}
        }
        return path;
    }

    public String factorize(String old) {
        int len = old.length();
        String factorized = "";
        int idx = 0;

        //while you are not at the end of the old string
        while (idx < len) {

            //count how many letters same in a row
            int count = 1;
            int count_idx = idx;
            while (true) {
                count_idx += 1;
                if (count_idx == len) {
                    idx = count_idx-1; break;
                } else if (old.charAt(count_idx) == old.charAt(idx)) {
                    count += 1;
                } else {
                    idx = count_idx-1; break;
                }
            }

            
            if (count > 1) {
                factorized = factorized + String.valueOf(count);
            }
            factorized = factorized + old.charAt(idx);
            idx += 1;
        }
        return factorized;
    }

    //method that verifies path
    public String verifyPath(String path) {

        //get starting position
        int pos[] = {find_enterence(), 0};
        Compass compass = new Compass('E');

        //loop though instructions
        for (int i = 0; i<path.length(); i++) {

            //skip empty spaces
            if (!(path.charAt(i) == ' ')) {

                //give the letter to move function
                char symbol = path.charAt(i);
                pos = move(pos, compass, symbol);

                //if you leave maze area
                if (!(pos[0] < maze.length && pos[0] > -1 && pos[1] < maze[0].length && pos[1] > -1)) {
                    return "incorrect path";
                }

                //if you run into wall, failure
                if (maze[pos[0]][pos[1]] == 'W') {
                    return "incorrect path";
                }
            }
        }
        if (pos[1] == maze[0].length-1) {
            return "correct path";
        } else {
            return "incorrect path";
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