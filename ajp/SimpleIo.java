package ajp;

import java.io.*;

public class SimpleIo {
    private static InputStreamReader streamIn = new InputStreamReader(System.in);
    private static BufferedReader in = new BufferedReader(streamIn, 1);

    public static void prompt(String msg) {
        System.out.print(msg);
        System.out.flush();
    }

    public static String readLine() {
        String line = null;
        try {
            line = in.readLine();
        } catch (IOException e) {
            System.out.println("Error in SimpleIo.readline: " + e.getMessage());
            System.exit(-1);
        }
        return line;
    }

}