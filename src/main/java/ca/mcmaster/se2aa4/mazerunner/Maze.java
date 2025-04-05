package ca.mcmaster.se2aa4.mazerunner;

public abstract class Maze {
    public abstract void addItem(char new_item);
    public abstract void addRow();
    public abstract boolean checkInMaze(int y, int x);
    public abstract char[] getRightHandAndFront(int y, int x, char facing);
    public abstract char getEntry(int y, int x);
    public abstract int find_enterence();
    public abstract int getYLength();
    public abstract int getXLength();
}