package ca.mcmaster.se2aa4.mazerunner;

class MyUtitily {
    public static char[] add1D(char[] old_array, char new_item) {
        char[] new_array = new char[old_array.length+1];
        for (int i=0; i<old_array.length; i++) {
            new_array[i] = old_array[i];
        }
        new_array[old_array.length] = new_item;
        return new_array;
    }
    public static char[][] addRow(char[][] old_array, char[] new_item) {
        char[][] new_array = new char[old_array.length+1][new_item.length];
        for (int i=0; i<old_array.length; i++) {
            new_array[i] = old_array[i];
        }
        new_array[old_array.length] = new_item;
        return new_array;
    }
    public static void outputCharArray(char[][] array) {
        for (int i=0; i<array.length; i++) {
            for (int j=0; j<array[0].length; j++) {
                if (array[i][j] == 'P') {
                    System.out.print(" ");
                } else {
                    System.out.print(array[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
    public static void outputPathNeat(String path) {
        for (int i=0; i<path.length(); i++) {
            System.out.print(path.charAt(i));
            if (i+1 < path.length()) {
                if ((path.charAt(i+1) != path.charAt(i)) && !(path.charAt(i) > 49 && path.charAt(i) < 58)) {
                    System.out.print(" ");
                }
                if (path.charAt(i+1) > 49 && path.charAt(i+1) < 58) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println("");
    }
}