import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Classe responsável por fornecer operações matemáticas através de um
 * motor de interação com um LLM.
 * <p>
 * Esta classe constrói prompts específicos para diferentes tipos de
 * operações matemáticas (derivação, simplificação, resolução de equações,
 * cálculos gerais e geração de texto criativo) e delega o envio desses
 * prompts ao {@link LLMInteractionEngine}.
 */
public class LLMCalculadora {

    /** Motor responsável pela comunicação com o LLM */
    LLMInteractionEngine engine;

    /**
     * Construtor da calculadora baseada em LLM.
     *
     * @param engine Instância do motor de interação com o LLM
     */
    public LLMCalculadora(LLMInteractionEngine engine) {
        this.engine = engine;
    }

    /**
     * Solicita ao LLM a derivada de uma expressão matemática.
     *
     * @param expression Expressão matemática a derivar
     * @return Resultado da derivação extraído do JSON de resposta
     * @throws IOException Erro de I/O durante a comunicação
     * @throws NoSuchAlgorithmException Se o algoritmo de segurança não existir
     * @throws InterruptedException Se a execução for interrompida
     * @throws KeyManagementException Se ocorrer erro na gestão de chaves
     */
    public String askForDerivative(String expression)
            throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        String prompt = "Deriva a seguinte expressão matemática: " + expression +
                ". Responde apenas em JSON no formato {\"operation\":\"derive\",\"result\":\"...\"}.";

        String jsonResponse = engine.sendPrompt(prompt);
        return JSONUtils.getJsonString(jsonResponse, "text");
    }

    /**
     * Solicita ao LLM a simplificação de uma expressão matemática.
     *
     * @param expression Expressão matemática a simplificar
     * @return Resultado da simplificação extraído do JSON de resposta
     * @throws IOException Erro de I/O durante a comunicação
     * @throws NoSuchAlgorithmException Se o algoritmo de segurança não existir
     * @throws InterruptedException Se a execução for interrompida
     * @throws KeyManagementException Se ocorrer erro na gestão de chaves
     */
    public String askForSimplification(String expression)
            throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        String prompt = "Simplifica a expressão: " + expression +
                ". Responde apenas em JSON no formato {\"operation\":\"simplify\",\"result\":\"...\"}.";
        String jsonResponse = engine.sendPrompt(prompt);
        return JSONUtils.getJsonString(jsonResponse, "text");
    }

    /**
     * Solicita ao LLM a resolução de uma equação matemática.
     *
     * @param equation Equação a resolver
     * @return Solução da equação extraída do JSON de resposta
     * @throws IOException Erro de I/O durante a comunicação
     * @throws NoSuchAlgorithmException Se o algoritmo de segurança não existir
     * @throws InterruptedException Se a execução for interrompida
     * @throws KeyManagementException Se ocorrer erro na gestão de chaves
     */
    public String askForEquationSolution(String equation)
            throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        String prompt = "Resolve a equação: " + equation +
                ". Devolve apenas JSON do tipo {\"operation\":\"solve\",\"result\":\"...\"}.";

        String jsonResponse = engine.sendPrompt(prompt);
        return JSONUtils.getJsonString(jsonResponse, "text");
    }

    /**
     * Solicita ao LLM a interpretação e cálculo de um problema matemático
     * descrito em linguagem natural.
     *
     * @param text Texto que descreve o problema matemático
     * @return Resultado final do cálculo extraído do JSON de resposta
     * @throws IOException Erro de I/O durante a comunicação
     * @throws NoSuchAlgorithmException Se o algoritmo de segurança não existir
     * @throws InterruptedException Se a execução for interrompida
     * @throws KeyManagementException Se ocorrer erro na gestão de chaves
     */
    public String askForCalculation(String text)
            throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        String prompt =
                "Interpreta o texto seguinte como um problema matemático e devolve apenas o resultado final. " +
                        "Texto: \"" + text + "\". " +
                        "Responde apenas em JSON no formato {\"operation\":\"calculate\",\"result\":\"...\"}.";

        String jsonResponse = engine.sendPrompt(prompt);
        return JSONUtils.getJsonString(jsonResponse, "text");
    }

    /**
     * Solicita ao LLM a geração de uma única linha curta de uma música natalícia
     * divertida que inclua explicitamente um determinado valor.
     *
     * @param resultado Valor que deve ser incluído na frase gerada
     * @return Linha de música natalícia gerada pelo LLM
     * @throws IOException Erro de I/O durante a comunicação
     * @throws NoSuchAlgorithmException Se o algoritmo de segurança não existir
     * @throws InterruptedException Se a execução for interrompida
     * @throws KeyManagementException Se ocorrer erro na gestão de chaves
     */
    public String askForNatalSongLine(Object resultado)
            throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        String prompt =
                "Gera UMA única linha curta de música natalícia divertida. " +
                        "A linha DEVE incluir o valor \"" + resultado + "\". " +
                        "Não expliques nada. Não uses JSON. Apenas a frase.";

        return engine.sendPrompt(prompt);
    }

}
