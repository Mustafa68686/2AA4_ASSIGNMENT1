package ca.mcmaster.se2aa4.mazerunner;

class MyUtitily {
    //print path with spaces inbetween
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