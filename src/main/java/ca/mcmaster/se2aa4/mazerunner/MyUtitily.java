package ca.mcmaster.se2aa4.mazerunner;

class MyUtitily {
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