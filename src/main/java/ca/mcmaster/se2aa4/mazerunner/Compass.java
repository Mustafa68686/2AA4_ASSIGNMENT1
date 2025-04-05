package ca.mcmaster.se2aa4.mazerunner;

class Compass {
    private char facing = 'E';

    //applying Singleton pattern
    private Compass() {}

    private static Compass instance = new Compass();

    public static Compass getInstance() {
        return instance;
    }

    public char getFacing() {
        return facing;
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