/**
 * Classe que implementa uma calculadora básica com operações aritméticas
 * fundamentais.
 * <p>
 * Disponibiliza métodos para adição, subtração, multiplicação, divisão,
 * potenciação e cálculo de raiz.
 */
public class Calculadora {

    /**
     * Realiza a adição de dois valores.
     *
     * @param a Primeiro operando
     * @param b Segundo operando
     * @return Resultado da soma de {@code a} com {@code b}
     */
    public static double ad(double a, double b) {
        return a + b;
    }

    /**
     * Realiza a subtração de dois valores.
     *
     * @param a Primeiro operando
     * @param b Segundo operando
     * @return Resultado da subtração de {@code b} a {@code a}
     */
    public static double sub(double a, double b) {
        return a - b;
    }

    /**
     * Realiza a multiplicação de dois valores.
     *
     * @param a Primeiro operando
     * @param b Segundo operando
     * @return Resultado da multiplicação de {@code a} por {@code b}
     */
    public static double mul(double a, double b) {
        return a * b;
    }

    /**
     * Realiza a divisão de dois valores.
     *
     * @param a Dividendo
     * @param b Divisor
     * @return Resultado da divisão de {@code a} por {@code b}
     * @throws IllegalArgumentException Se o divisor for zero
     */
    public static double div(double a, double b) {
        if (b == 0) throw new IllegalArgumentException("Divisão por zero");
        return a / b;
    }

    /**
     * Calcula a potência de um valor.
     *
     * @param a Base
     * @param b Expoente
     * @return Resultado de {@code a} elevado a {@code b}
     */
    public static double pot(double a,  double b){
        return Math.pow(a , b);
    }
    /**
     * Calcula a raiz de um valor.
     *
     * @param a Valor base
     * @param b Índice da raiz
     * @return Resultado da raiz de índice {@code b} do valor {@code a}
     */
    public static double raiz(double a,  double b){
        return Math.pow(a , 1.0/b);
    }
}
