
import java.util.Scanner;

public class Utils {

    static int readCharFromKeyboard() {
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    static String readLine() {
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");
        return sc.nextLine();
    }

}
