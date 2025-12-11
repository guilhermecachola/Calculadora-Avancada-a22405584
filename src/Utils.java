
import java.util.Scanner;

public class Utils {

    static int readCharFromKeyboard() { //código base
        Scanner sc = new Scanner(System.in);

        System.out.print("> ");
        // read a whole line
        String input = sc.nextLine();

        if (!input.isEmpty()) {
            // take the first char
            char c = input.charAt(0);
            return c - '0';
        }

        return -1; // hack
    }

    static String readLine() {
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");
        return sc.nextLine();
    }

}
