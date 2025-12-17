import java.util.Scanner;

/**
 * Classe utilitária com métodos de apoio para leitura de dados
 * a partir do teclado.
 * <p>
 * Centraliza operações de input usadas pela aplicação, como
 * leitura de números inteiros e linhas de texto.
 */
public class Utils {

    /**
     * Lê um valor do teclado e tenta convertê-lo para inteiro.
     * <p>
     * Caso o valor introduzido não seja um número válido, devolve -1.
     *
     * @return Valor inteiro lido ou -1 em caso de erro de conversão
     */
    static String readCharFromKeyboard() {
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");
        try {
            return (sc.nextLine());
        } catch (NumberFormatException e) {
            return "não deu";
        }
    }

    /**
     * Lê uma linha de texto do teclado.
     *
     * @return String introduzida pelo utilizador
     */
    static String readLine() {
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");
        return sc.nextLine();
    }

}
