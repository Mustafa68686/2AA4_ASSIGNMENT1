package ca.mcmaster.se2aa4.mazerunner;

class ArrayStorage extends Maze {
    public char maze[][] = new char[0][0];
    private char row[] = new char[0];

    //add item to row being made
    public void addItem(char new_item) {
        char[] new_row = new char[row.length+1];
        for (int i=0; i<row.length; i++) {
            new_row[i] = row[i];
        }
        new_row[row.length] = new_item;
        row = new_row;
    }

    //add built row to maze
    public void addRow() {
        char[][] new_maze= new char[maze.length+1][row.length];
        for (int i=0; i<maze.length; i++) {
            new_maze[i] = maze[i];
        }
        new_maze[maze.length] = row;
        maze = new_maze;
        row = new char[0];
    }

    //get lengths
    public int getYLength() {
        return maze.length;
    }
    public int getXLength() {
        return maze[0].length;
    }

    //check if indexes is in maze
    public boolean checkInMaze(int y, int x) {
        if (y < maze.length && y > -1 && x < maze[0].length && x > -1) {
            return true;
        } else {
            return false;
        }
    }

    //check right hand and front
    public char[] getRightHandAndFront(int y, int x, char facing) {
        char[] symbols = {'x', 'x'};
        switch (facing) {
            case 'N':
                symbols[0] = maze[y][x+1];
                symbols[1] = maze[y-1][x];
                break;
            case 'E':
                symbols[0] = maze[y+1][x];
                symbols[1] = maze[y][x+1];
                break;
            case 'S':
                symbols[0] = maze[y][x-1];
                symbols[1] = maze[y+1][x];
                break;
            case 'W':
                symbols[0] = maze[y-1][x];
                symbols[1] = maze[y][x-1];
                break;
        }
        return symbols;
    }

    //get entry
    public char getEntry(int y, int x) {
        return maze[y][x];
    }

    //find start height
    public int find_enterence() {
        int start_height = 0;
        for (int i=0; i<maze.length; i++) {

            if (maze[i][0] == 'P') {
                start_height = i;
                break;
            }
        }
        return start_height;
    }
}