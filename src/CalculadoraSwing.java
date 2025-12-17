import javax.swing.*;
import java.awt.*;

public class CalculadoraSwing {

    private final LLMCalculadora llm;
    private final MotorCalculo motor;

    private boolean modoNatal = false;

    private JTextField input;
    private JTextArea output;
    private DefaultListModel<String> historicoModel;
    private JProgressBar progress;

    // Referências para aplicar tema
    private JPanel main;
    private JPanel left;
    private JPanel inputPanel;
    private JPanel outputPanel;
    private JPanel botoes;

    // Cores
    private static final Color NATAL_BG = new Color(121, 165, 127);
    private static final Color NATAL_FG = new Color(220, 30, 30);
    private static final Color NORMAL_BG = Color.WHITE;
    private static final Color NORMAL_FG = Color.BLACK;

    public CalculadoraSwing(LLMCalculadora llm) {
        this.llm = llm;
        this.motor = new MotorCalculo(llm);
        criarUI();
    }

    private void criarUI() {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ignored) {}

        JFrame frame = new JFrame("Calculadora Avançada");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(840, 500);
        frame.setLocationRelativeTo(null);

        main = new JPanel(new BorderLayout(12, 12));
        main.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // ===== INPUT =====
        input = new JTextField();
        input.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        input.setPreferredSize(new Dimension(0, 38));

        inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Expressão"));
        inputPanel.add(input, BorderLayout.CENTER);

        // ===== BOTÕES =====
        JButton btnCalcular = criarBotao("Calcular");
        JButton btnDerivar = criarBotao("Derivar");
        JButton btnSimplificar = criarBotao("Simplificar");
        JButton btnResolver = criarBotao("Resolver Equação");
        JButton btnTextoLivre = criarBotao("Por Extenso");
        JButton btnClear = criarBotao("C");
        JButton btnRegras = criarBotao("Regras");
        JToggleButton btnNatal = new JToggleButton("🎄 Modo Natal");

        botoes = new JPanel(new GridLayout(3, 2, 8, 8));
        botoes.add(btnCalcular);
        botoes.add(btnDerivar);
        botoes.add(btnSimplificar);
        botoes.add(btnResolver);
        botoes.add(btnTextoLivre);
        botoes.add(btnClear);
        botoes.add(btnRegras);


        // ===== OUTPUT =====
        output = new JTextArea();
        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setFont(new Font("Consolas", Font.PLAIN, 14));

        outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Resultado"));
        outputPanel.add(new JScrollPane(output), BorderLayout.CENTER);

        // ===== PROGRESS =====
        progress = new JProgressBar();
        progress.setIndeterminate(true);
        progress.setVisible(false);
        progress.setStringPainted(true);
        progress.setString("A calcular...");

        // ===== HISTÓRICO =====
        historicoModel = new DefaultListModel<>();
        JList<String> historico = new JList<>(historicoModel);
        historico.setFont(new Font("Consolas", Font.PLAIN, 12));
        historico.setFixedCellHeight(24);

        JScrollPane histScroll = new JScrollPane(historico);
        histScroll.setPreferredSize(new Dimension(300, 0));
        histScroll.setBorder(BorderFactory.createTitledBorder("Histórico"));

        // ===== LADO ESQUERDO =====
        left = new JPanel(new BorderLayout(10, 10));
        left.add(inputPanel, BorderLayout.NORTH);
        left.add(botoes, BorderLayout.CENTER);
        left.add(outputPanel, BorderLayout.SOUTH);

        main.add(left, BorderLayout.CENTER);
        main.add(histScroll, BorderLayout.EAST);
        main.add(progress, BorderLayout.SOUTH);
        main.add(btnNatal, BorderLayout.NORTH);

        // ===== AÇÕES =====
        btnCalcular.addActionListener(e ->
                executar("Calcular", () ->
                        motor.calcular(input.getText()))
        );

        btnDerivar.addActionListener(e ->
                executar("Derivar", () ->
                        llm.askForDerivative(input.getText()))
        );

        btnSimplificar.addActionListener(e ->
                executar("Simplificar", () ->
                        llm.askForSimplification(input.getText()))
        );

        btnResolver.addActionListener(e ->
                executar("Resolver", () ->
                        llm.askForEquationSolution(input.getText()))
        );

        btnTextoLivre.addActionListener(e ->
                executar("Por extenso", () ->
                        motor.calcularPorExtenso(input.getText()))
        );

        btnClear.addActionListener(e -> {
            input.setText("");
            output.setText("");
            historicoModel.clear();
            progress.setVisible(false);
        });

        btnNatal.addActionListener(e -> {
            modoNatal = btnNatal.isSelected();
            atualizarTema();
        });

        input.addActionListener(e -> btnCalcular.doClick());

        btnRegras.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    null,
                    """
                    REGRAS DE UTILIZAÇÃO DA CALCULADORA
        
                    Operações básicas:
        
                    • Adição:           a + b
                      Exemplo:          2 + 3
        
                    • Subtração:        a - b
                      Exemplo:          5 - 1
        
                    • Multiplicação:   a * b
                      Exemplo:          4 * 6
        
                    • Divisão:          a / b
                      Exemplo:          10 / 2
        
                    • Potência:         a ^ b
                      Exemplo:          2 ^ 3
        
                    • Raiz:             raiz(a, b)
                      Exemplo:          raiz(9, 2)
        
                    Também são aceites:
                    • Texto por extenso
                    • Expressões matemáticas
                    • Operações avançadas com LLM
                    """,
                    "Regras da Calculadora",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });


        frame.setContentPane(main);
        frame.setVisible(true);

    }

    // ===== TEMA =====
    private void atualizarTema() {
        aplicarTema(main);

        output.setFont(
                output.getFont().deriveFont(modoNatal ? Font.BOLD : Font.PLAIN)
        );
        input.setCaretColor(modoNatal ? Color.WHITE : Color.BLACK);
        output.repaint();
    }

    private void aplicarTema(Component c) {
        if (modoNatal) {
            c.setBackground(NATAL_BG);
            c.setForeground(NATAL_FG);
        } else {
            c.setBackground(NORMAL_BG);
            c.setForeground(NORMAL_FG);
        }

        if (c instanceof Container) {
            for (Component filho : ((Container) c).getComponents()) {
                aplicarTema(filho);
            }
        }
    }

    // ===== UTIL =====
    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(0, 36));
        return btn;
    }

    private void executar(String operacao, Tarefa tarefa) {

        String expr = input.getText().trim();
        if (expr.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Introduz uma expressão.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        output.setText("A calcular...");
        progress.setVisible(true);

        new SwingWorker<String, Void>() {

            @Override
            protected String doInBackground() throws Exception {
                return tarefa.executar();
            }

            @Override
            protected void done() {
                progress.setVisible(false);
                try {
                    String res = get();
                    output.setText(res);
                    historicoModel.addElement(
                            operacao + ": " + expr + " → " + res
                    );
                } catch (Exception e) {
                    output.setText("Erro ao calcular.");
                }
            }
        }.execute();
    }

    @FunctionalInterface
    interface Tarefa {
        String executar() throws Exception;
    }
}
