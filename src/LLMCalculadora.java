import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class LLMCalculadora {

    private final LLMInteractionEngine engine;

    public LLMCalculadora(LLMInteractionEngine engine) {
        this.engine = engine;
    }

    public String askForDerivative(String expression)
            throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        String prompt =
                "Deriva a seguinte expressão matemática:\n\n" +
                        expression + "\n\n" +
                        "Responde APENAS com a derivada final.\n" +
                        "Não expliques nada.\n" +
                        "Não uses JSON.\n" +
                        "Não escrevas mais nenhuma palavra.";

        String response = engine.sendPrompt(prompt);

        // extrai apenas o texto gerado pelo modelo
        String result = JSONUtils.getJsonString(response, "text");

        return result == null ? response.trim() : result.trim();
    }


    public String askForSimplification(String expression)
            throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        String prompt =
                "Simplifica a seguinte expressão matemática:\n\n" +
                        expression + "\n\n" +
                        "Responde APENAS com a expressão simplificada.\n" +
                        "Não expliques nada.\n" +
                        "Não uses JSON.\n" +
                        "Não escrevas mais nenhuma palavra.";

        String response = engine.sendPrompt(prompt);

        String result = JSONUtils.getJsonString(response, "text");

        return result == null ? response.trim() : result.trim();
    }


    public String askForEquationSolution(String equation)
            throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        String prompt =
                "Resolve a seguinte equação matemática:\n\n" +
                        equation + "\n\n" +
                        "Responde APENAS com o resultado final.\n" +
                        "Não expliques nada.\n" +
                        "Não uses JSON.";

        String response = engine.sendPrompt(prompt);

        // extrai o campo "text" da resposta
        return JSONUtils.getJsonString(response, "text");
    }



    public String askForCalculation(String textInput)
            throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        String prompt =
                "Interpreta o texto seguinte como um problema matemático e calcula o resultado final:\n\n" +
                        "\"" + textInput + "\"\n\n" +
                        "Responde APENAS com o resultado final.\n" +
                        "Não expliques nada.\n" +
                        "Não uses JSON.\n" +
                        "Não escrevas mais nenhuma palavra.";

        String response = engine.sendPrompt(prompt);

        String result = JSONUtils.getJsonString(response, "text");

        return result == null ? response.trim() : result.trim();
    }


    public String askForNatalSongLine(Object resultado)
            throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        String prompt =
                "Gera UMA única linha curta de música natalícia divertida.\n\n" +
                        "Regras:\n" +
                        "- A linha DEVE incluir exatamente o valor \"" + resultado + "\"\n" +
                        "- Não expliques nada\n" +
                        "- Não uses JSON\n" +
                        "- Responde apenas com a frase";

        String response = engine.sendPrompt(prompt);

        String text = JSONUtils.getJsonString(response, "text");
        return text == null ? response.trim() : text.trim();
    }

}
