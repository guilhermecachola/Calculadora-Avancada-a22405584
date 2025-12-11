import java.util.ArrayList;

public class Comandos {

    Calculadora calc = new Calculadora();
    LLMCalculadora llm;
    ArrayList<String> historico = new ArrayList<>();
    public Comandos(LLMCalculadora llm) {
        this.llm = llm;
    }

    public void run() throws Exception {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1) Soma");
            System.out.println("2) Subtração");
            System.out.println("3) Multiplicação");
            System.out.println("4) Divisão");
            System.out.println("5) Derivar expressão (LLM)");
            System.out.println("6) Simplificar expressão (LLM)");
            System.out.println("7) Resolver equação (LLM)");
            System.out.println("8) Verificar histórico de operações");
            System.out.println("0) Sair");

            int option = Utils.readCharFromKeyboard();

            switch (option) {
                case 1: handleAdd(); break;
                case 2: handleSub(); break;
                case 3: handleMul(); break;
                case 4: handleDiv(); break;
                case 5: handleDerivative(); break;
                case 6: handleSimplification(); break;
                case 7: handleEquation(); break;
                case 0: return;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    void handleAdd() {
        System.out.println("Introduza o primeiro número:");
        double a = Double.parseDouble(Utils.readLine());

        System.out.println("Introduza o segundo número:");
        double b = Double.parseDouble(Utils.readLine());
        adicionaHistorico(a , b , " + " , calc.ad(a, b));
        System.out.println("Resultado: " + calc.ad(a, b));
    }

    void handleSub() {
        System.out.println("Introduza o primeiro número:");
        double a = Double.parseDouble(Utils.readLine());

        System.out.println("Introduza o segundo número:");
        double b = Double.parseDouble(Utils.readLine());
        adicionaHistorico(a , b , " - " , calc.sub(a, b));
        System.out.println("Resultado: " + calc.sub(a, b));
    }

    void handleMul() {
        System.out.println("Introduza o primeiro número:");
        double a = Double.parseDouble(Utils.readLine());

        System.out.println("Introduza o segundo número:");
        double b = Double.parseDouble(Utils.readLine());
        adicionaHistorico(a , b , " * " , calc.mul(a, b));
        System.out.println("Resultado: " + calc.mul(a, b));
    }

    void handleDiv() {
        System.out.println("Introduza o primeiro número:");
        double a = Double.parseDouble(Utils.readLine());

        System.out.println("Introduza o segundo número:");
        double b = Double.parseDouble(Utils.readLine());

        try {
            adicionaHistorico(a , b , " / " , calc.div(a, b));
            System.out.println("Resultado: " + calc.div(a, b));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }


    void handleDerivative() throws Exception {
        System.out.println("Introduza a expressão a derivar:");
        String expr = Utils.readLine();

        String resposta = llm.askForDerivative(expr);
        System.out.println("Derivada: " + resposta);
    }

    void handleSimplification() throws Exception {
        System.out.println("Introduza a expressão a simplificar:");
        String expr = Utils.readLine();

        String resposta = llm.askForSimplification(expr);
        System.out.println("Simplificação: " + resposta);
    }

    void handleEquation() throws Exception {
        System.out.println("Introduza a equação a resolver:");
        String eq = Utils.readLine();

        String resposta = llm.askForEquationSolution(eq);
        System.out.println("Solução: " + resposta);
    }

    void adicionaHistorico(double a , double b , String simbolo , double resultado){
        historico.add(a + simbolo + b + " = " + resultado);
    }

}
