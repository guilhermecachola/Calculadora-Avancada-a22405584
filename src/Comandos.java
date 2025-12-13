import java.util.ArrayList;

/**
 * Classe responsável por gerir os comandos da aplicação de calculadora.
 * <p>
 * Suporta operações matemáticas básicas, integração com um LLM para
 * operações simbólicas e textuais, histórico de operações e um modo
 * especial de Natal com animações e mensagens temáticas.
 */
public class Comandos {

    /** Instância da calculadora tradicional */
    Calculadora calc = new Calculadora();

    /** Instância da calculadora baseada em LLM */
    LLMCalculadora llm;

    /** Histórico textual das operações realizadas */
    ArrayList<String> historico = new ArrayList<>();

    /** Indica se o modo Natal está ativo */
    boolean natal = false;

    /** Emojis e símbolos usados no modo Natal */
    String paiNatal = "🎅";
    String treno = "🛷";
    String rena = "🦌";
    String arvore = "🌲";
    String brilho = "✨";
    String presente = "🎁";
    String arvore2 = "🎄";
    String maeNatal = "🤶";
    String boneco = "☃️";
    String urso = "🐻‍";

    /** Variáveis auxiliares para parsing de operações */
    int numero1;
    int numero2;
    String texto;
    Object result;

    /**
     * Construtor da classe de comandos.
     *
     * @param llm Instância da calculadora baseada em LLM
     */
    public Comandos(LLMCalculadora llm) {
        this.llm = llm;
    }

    /**
     * Ciclo principal da aplicação.
     * <p>
     * Mostra o menu, lê opções do utilizador e executa os comandos
     * correspondentes, com variação visual caso o modo Natal esteja ativo.
     *
     * @throws Exception Exceções genéricas lançadas por operações internas
     */
    public void run() throws Exception {
        while (true) {
            if(natal) {
                String f1 = Math.random() < 0.5 ? " ❄️" : "";
                String f2 = Math.random() < 0.5 ? " ❄️" : "";
                String f3 = Math.random() < 0.5 ? " ❄️" : "";
                String f4 = Math.random() < 0.5 ? " ❄️" : "";
                String f5 = Math.random() < 0.5 ? " ❄️" : "";
                String f6 = Math.random() < 0.5 ? " ❄️" : "";
                String f7 = Math.random() < 0.5 ? " ❄️" : "";
                String f8 = Math.random() < 0.5 ? " ❄️" : "";
                String f0 = Math.random() < 0.5 ? " ❄️" : "";
                animacaoNeve();
                System.out.println("Escolha uma opção:" + (Math.random() < 0.5 ? " ❄️" : ""));
                System.out.println("1)" + paiNatal +"Soma" + f1);
                System.out.println("2)" + arvore + "Subtração" + f2);
                System.out.println("3)" + rena + "Multiplicação" + f3);
                System.out.println("4)" + arvore2 + "Divisão" + f4);
                System.out.println("5)" + paiNatal + "Potencia");
                System.out.println("6)" + maeNatal + "Raiz");
                System.out.println("7)" + treno + "Derivar expressão (LLM)" + f5);
                System.out.println("8)" + brilho + "Simplificar expressão (LLM)" + f6);
                System.out.println("9)" + presente + "Resolver equação (LLM)" + f7);
                System.out.println("10)" + maeNatal + "Verificar histórico de operações" + f8);
                System.out.println("11)" + brilho + "Escrever operação por extenso e calcular (LLM)");
                System.out.println("12)" + urso + "Modo NATAL: ON");
                System.out.println("0)" + boneco + "Sair" + f0);
                /* No modo natal os resultados aparecem a grande e antes de aparecerem
                   neva um bocado ou até é o resultado a neve e adicionar potencias e raizes */
            }
            else {
                System.out.println("Escolha uma opção:");
                System.out.println("1) Soma");
                System.out.println("2) Subtração");
                System.out.println("3) Multiplicação");
                System.out.println("4) Divisão");
                System.out.println("5) Potencia");
                System.out.println("6) Raiz");
                System.out.println("7) Derivar expressão (LLM)");
                System.out.println("8) Simplificar expressão (LLM)");
                System.out.println("9) Resolver equação (LLM)");
                System.out.println("10) Verificar histórico de operações");
                System.out.println("11) Escrever operação por extenso e calcular (LLM)");
                System.out.println("12)Modo NATAL: OFF");
                System.out.println("0) Sair");
            }
            int option = Utils.readCharFromKeyboard();

            switch (option) {
                case 1:
                    if (natal) animacaoNeve();
                    handleAdd();
                    break;

                case 2:
                    if (natal) animacaoNeve();
                    handleSub();
                    break;

                case 3:
                    if (natal) animacaoNeve();
                    handleMul();
                    break;

                case 4:
                    if (natal) animacaoNeve();
                    handleDiv();
                    break;

                case 5:
                    if (natal) animacaoNeve();
                    handlePot();
                    break;

                case 6:
                    if (natal) animacaoNeve();
                    handleRaiz();
                    break;

                case 7:
                    handleDerivative();
                    break;

                case 8:
                    handleSimplification();
                    break;

                case 9:
                    handleEquation();
                    break;

                case 10:
                    historicoString();
                    break;

                case 11:
                    handleTextoLivre();
                    break;

                case 12:
                    natal = !natal;
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida.");
            }

        }
    }

    /** Trata a operação de adição */
    void handleAdd() {
        System.out.println("Introduza o primeiro número:");
        double a = Double.parseDouble(Utils.readLine());

        System.out.println("Introduza o segundo número:");
        double b = Double.parseDouble(Utils.readLine());

        Object r = calc.ad(a, b);
        adicionaHistorico(a, b, " + ", r);
        mostrarResultado(r);
    }

    /** Trata a operação de subtração */
    void handleSub() {
        System.out.println("Introduza o primeiro número:");
        double a = Double.parseDouble(Utils.readLine());

        System.out.println("Introduza o segundo número:");
        double b = Double.parseDouble(Utils.readLine());

        Object r = calc.sub(a, b);
        adicionaHistorico(a, b, " - ", r);
        mostrarResultado(r);
    }

    /** Trata a operação de multiplicação */
    void handleMul() {
        System.out.println("Introduza o primeiro número:");
        double a = Double.parseDouble(Utils.readLine());

        System.out.println("Introduza o segundo número:");
        double b = Double.parseDouble(Utils.readLine());

        Object r = calc.mul(a, b);
        adicionaHistorico(a, b, " * ", r);
        mostrarResultado(r);
    }

    /** Trata a operação de divisão */
    void handleDiv() {
        System.out.println("Introduza o primeiro número:");
        double a = Double.parseDouble(Utils.readLine());

        System.out.println("Introduza o segundo número:");
        double b = Double.parseDouble(Utils.readLine());

        try {
            Object r = calc.div(a, b);
            adicionaHistorico(a, b, " / ", r);
            mostrarResultado(r);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /** Trata a operação de potenciação */
    void handlePot(){
        boolean pode = false;
        while(!pode) {
            System.out.println("Introduza a potencia no formato a^b:");
            texto = Utils.readLine();

            if (texto.matches("^\\d+\\^\\d+$")) {
                pode = true;
            } else {
                System.out.println("Formato inválido, tente outra vez.");
            }
        }

        tiraPotencia(texto);
        Object r = calc.pot(numero1 , numero2);
        adicionaHistorico(numero1, numero2 , " elevado a " , r);
        mostrarResultado(r);
    }

    /** Trata a operação de raiz */
    void handleRaiz(){
        System.out.println("Introduza o número dentro da raiz:");
        double a = Double.parseDouble(Utils.readLine());

        System.out.println("Introduza o índice da raiz:");
        double b = Double.parseDouble(Utils.readLine());

        Object r = calc.raiz(a, b);
        adicionaHistorico(a , b , " tem raiz «» " , r);
        mostrarResultado(r);
    }

    /**
     * Extrai os valores base e expoente de uma string no formato a^b.
     *
     * @param tira String com a potência
     */
    void tiraPotencia(String tira){
        String[] partes = tira.split("\\^");
        numero1 = Integer.parseInt(partes[0]);
        numero2 = Integer.parseInt(partes[1]);
    }

    /** Trata o pedido de derivação usando o LLM */
    void handleDerivative() throws Exception {
        System.out.println("Introduza a expressão a derivar:");
        String expr = Utils.readLine();

        String resposta = llm.askForDerivative(expr);
        System.out.println("Derivada: " + resposta);
    }

    /** Trata o pedido de simplificação usando o LLM */
    void handleSimplification() throws Exception {
        System.out.println("Introduza a expressão a simplificar:");
        String expr = Utils.readLine();

        String resposta = llm.askForSimplification(expr);
        System.out.println("Simplificação: " + resposta);
    }

    /** Trata o pedido de resolução de equações usando o LLM */
    void handleEquation() throws Exception {
        System.out.println("Introduza a equação a resolver:");
        String eq = Utils.readLine();

        String resposta = llm.askForEquationSolution(eq);
        System.out.println("Solução: " + resposta);
    }

    /**
     * Adiciona uma entrada ao histórico de operações.
     *
     * @param a Primeiro operando
     * @param b Segundo operando
     * @param simbolo Símbolo da operação
     * @param resultado Resultado obtido
     */
    void adicionaHistorico(double a , double b , String simbolo , Object resultado){
        historico.add(a + simbolo + b + " = " + resultado);
    }

    /** Executa uma animação simples de neve no terminal */
    void animacaoNeve() {
        String floco = "❄️";
        int largura = 40;
        int linhas = 10;
        int densidade = 8;

        try {
            for (int i = 0; i < linhas; i++) {
                StringBuilder linha = new StringBuilder();

                for (int c = 0; c < largura; c++) {
                    if (Math.random() < (double)densidade / largura) {
                        linha.append(floco);
                    } else {
                        linha.append(" ");
                    }
                }

                System.out.println(linha);
                Thread.sleep(150);
            }
        } catch (InterruptedException e) {

        }
    }

    /** Mostra o histórico de operações realizadas */
    void historicoString(){
        if(historico.isEmpty()){
            System.out.println("Sem histórico");
            return;
        }
        for(String a: historico ){
            System.out.println(a);
        }
    }

    /**
     * Mostra o resultado de uma operação, com comportamento especial
     * caso o modo Natal esteja ativo.
     *
     * @param resultado Resultado a apresentar
     */
    void mostrarResultado(Object resultado) {

        if (natal) {
            animacaoNeve();
            System.out.println("🎄❄️ RESULTADO ❄️🎄");

            try {
                String frase = llm.askForNatalSongLine(resultado);
                System.out.println(frase.trim());
            } catch (Exception e) {
                System.out.println("🎶 Ho ho ho! Resultado especial de Natal!");
            }

            System.out.println(paiNatal + resultado + maeNatal);
        }
        else {
            System.out.println("Resultado: " + resultado);
        }
    }

    /** Trata problemas matemáticos escritos em linguagem natural */
    void handleTextoLivre() throws Exception {
        System.out.println("Escreva o problema matemático:");
        String texto = Utils.readLine();

        Object r = llm.askForCalculation(texto);
        mostrarResultado(r);
    }

}
