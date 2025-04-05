package ca.mcmaster.se2aa4.mazerunner;

class MazeUtility {
    public Storage mazeStorage;

    public MazeUtility(Storage storage) {
        mazeStorage = storage;
    }
    public void main() {

        //starting postition, one step in
        int pos[] = {mazeStorage.find_enterence(), 1};
        Compass compass = new Compass('E');
        String path = "F";

        //compute path
        path = rightHandMethod(pos, path, compass);

        //factorize path
        String factorized_path = factorize(path);
        //System.out.println(factorized_path);
        MyUtitily.outputPathNeat(factorized_path);
    }

    //verify path
    public void main(String path) {
        path = defactorize(path);

        String status = verifyPath(path);
        System.out.println(status);
    }

    //find start height
   public String rightHandMethod(int[] pos, String path, Compass compass) {
        while (true) {

            //if you reach the end, break
            if (pos[1] == mazeStorage.getXLength()-1) {
                break;
            }
            char facing = compass.getFacing();

            //check right hand and front
            char[] symbols = mazeStorage.getRightHandAndFront(pos[0], pos[1], facing);
            char right_hand = symbols[0];
            char front = symbols[1];
            
            //advance
            if (right_hand == 'W' && front == 'P') {
                compass.forward(pos);
                path = path + "F";
            //turn left
            } else if (right_hand == 'W' && front == 'W') {
                compass.turnLeft(); path = path + "L";
            //turn right
            } else {
                compass.turnRight(); path = path + "R";
                compass.forward(pos); path = path + "F";
            }
        }
        return path;
    }
    
    //factorize given path
    public String factorize(String old) {
        int len = old.length();
        String factorized = "";
        int idx = 0;

        //while you are not at the end of the old string
        while (idx < len) {

            //count how many letters are the same ahead, skip to the last index
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

            //add that number infront
            if (count > 1) {
                factorized = factorized + String.valueOf(count);
            }
            factorized = factorized + old.charAt(idx);
            idx += 1;
        }
        return factorized;
    }

    //function to decode given path into readable form
    public String defactorize(String old) {
        String basic = "";
        int idx = 0; int len = old.length();
        while (idx < len) {
            if (old.charAt(idx) > 49 && old.charAt(idx) < 58) {
                int num = old.charAt(idx) - 48;
                for (int i=0; i<num; i++) {
                    basic = basic + old.charAt(idx+1);
                }
                idx += 2;
            } else {
                basic = basic + old.charAt(idx);
                idx += 1;
            }
        }
        return basic;
    }

    //method that verifies path
   public String verifyPath(String path) {

        //get starting position
        int pos[] = {mazeStorage.find_enterence(), 0};
        Compass compass = new Compass('E');

        //loop though instructions
        for (int i = 0; i<path.length(); i++) {

            //skip empty spaces
            if (!(path.charAt(i) == ' ')) {

                //give the letter to move function
                char symbol = path.charAt(i);
                pos = move(pos, compass, symbol);

                //if you leave maze area
                if (!(mazeStorage.checkInMaze(pos[0], pos[1]))) {
                    return "incorrect path";
                }

                //if you run into wall, failure
                if (mazeStorage.getEntry(pos[0], pos[1]) == 'W') {
                    return "incorrect path";
                }
            }
        }
        if (pos[1] == mazeStorage.getXLength()-1) {
            return "correct path";
        } else {
            return "incorrect path";
        }
    }

    //function that implements move
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