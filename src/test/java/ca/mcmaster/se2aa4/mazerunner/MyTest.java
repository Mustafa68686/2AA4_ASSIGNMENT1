package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MyTest {

    @Test
    //testing verify method
    public void test1() {
        MazeUtility mu = Main.main(new String[] {"-i", "./examples/straight.maz.txt"});
        String result = mu.verifyPath("FFFF");
        assertEquals("correct path", result);
    }

    @Test
    //testing Storage by making maze from scratch
    public void test2() {
        ArrayStorage storage = new ArrayStorage();
        storage.addItem('W'); storage.addItem('W'); storage.addItem('W'); storage.addItem('W'); storage.addRow();
        storage.addItem('P'); storage.addItem('P'); storage.addItem('W'); storage.addItem('W'); storage.addRow();
        storage.addItem('W'); storage.addItem('P'); storage.addItem('P'); storage.addItem('P'); storage.addRow();
        storage.addItem('W'); storage.addItem('W'); storage.addItem('W'); storage.addItem('W'); storage.addRow();
        MazeUtility mu = new MazeUtility(storage);
        String result = mu.verifyPath("FRFLFF");
        assertEquals("correct path", result);
    }

    @Test
    //testing find height function
    public void test3() {
        ArrayStorage storage = new ArrayStorage();
        storage.addItem('W'); storage.addItem('W'); storage.addItem('W'); storage.addItem('W'); storage.addRow();
        storage.addItem('P'); storage.addItem('P'); storage.addItem('W'); storage.addItem('W'); storage.addRow();
        storage.addItem('W'); storage.addItem('P'); storage.addItem('P'); storage.addItem('P'); storage.addRow();
        storage.addItem('W'); storage.addItem('W'); storage.addItem('W'); storage.addItem('W'); storage.addRow();
        int height = storage.find_enterence();
        assertEquals(height, 1);
    }

    @Test
    //testing factorize method
    public void test4() {
        MazeUtility mu = Main.main(new String[] {"-i", "./examples/straight.maz.txt"});
        String factorized = mu.factorize("FFLLRR");
        assertEquals("2F2L2R", factorized);
    }

    @Test
    //testing defactorize
    public void test5() {
        MazeUtility mu = Main.main(new String[] {"-i", "./examples/straight.maz.txt"});
        String path = mu.defactorize("2FRL4F");
        assertEquals("FFRLFFFF", path);
    }

    @Test
    //testing Compass class forward
    public void test6() {
        Compass compass = Compass.getInstance();
        int[] old_pos = {5, 5};
        int[] new_pos = compass.forward(old_pos);
        assertEquals(new_pos[1], 6);
    }

    @Test
    //testing Compass class turn right
    public void test7() {
        Compass compass = Compass.getInstance();
        compass.turnRight();
        assertEquals(compass.getFacing(), 'S');
    }

    @Test
    //testing Compass class turn left
    public void test8() {
        Compass compass = Compass.getInstance();
        compass.turnLeft(); compass.turnLeft(); //facing south from previous test
        assertEquals(compass.getFacing(), 'N');
    }

    @Test
    //testing move function
    public void test9() {
        MazeUtility mu = Main.main(new String[] {"-i", "./examples/straight.maz.txt"});
        Compass compass = Compass.getInstance();
        int[] old_pos = {5, 5};
        int[] new_pos = mu.move(old_pos, compass, 'F');
        assertEquals(new_pos, old_pos);
    }
    
    @Test
    //testing right hand method from the middle
    public void test10() {
        MazeUtility mu = Main.main(new String[] {"-i", "./examples/straight.maz.txt"});
        int[] pos = {2, 2};
        Compass compass = Compass.getInstance();
        String path = mu.rightHandMethod(pos, "", compass);
        assertEquals("FF", path);
    }
    
}