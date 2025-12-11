public class Calculadora {

    public double ad(double a, double b) {
        return a + b;
    }

    public double sub(double a, double b) {
        return a - b;
    }

    public double mul(double a, double b) {
        return a * b;
    }

    public double div(double a, double b) {
        if (b == 0) throw new IllegalArgumentException("Divisão por zero");
        return a / b;
    }

    public double pot(double a,  double b){
        return Math.pow(a , b);
    }

    public double raiz(double a,  double b){
        return Math.pow(a , 1.0/b);
    }
}
