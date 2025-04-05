package ca.mcmaster.se2aa4.mazerunner;

enum MazeType {
    CharArray
}

class MazeFactory {

    public Maze getMaze(MazeType type) {
        switch(type) {
            case CharArray:
                return new ArrayStorage();
            default:
                return new ArrayStorage();
        }
    }

}