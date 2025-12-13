

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class LLMCalculadora {

    LLMInteractionEngine engine;

    public LLMCalculadora(LLMInteractionEngine engine) {
        this.engine = engine;
    }

    public String askForDerivative(String expression)
            throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        String prompt = "Deriva a seguinte expressão matemática: " + expression +
                ". Responde apenas em JSON no formato {\"operation\":\"derive\",\"result\":\"...\"}.";

        String jsonResponse = engine.sendPrompt(prompt);
        return JSONUtils.getJsonString(jsonResponse, "text");
    }

    public String askForSimplification(String expression)
            throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        String prompt = "Simplifica a expressão: " + expression +
                ". Responde apenas em JSON no formato {\"operation\":\"simplify\",\"result\":\"...\"}.";
        String jsonResponse = engine.sendPrompt(prompt);
        return JSONUtils.getJsonString(jsonResponse, "text");
    }

    public String askForEquationSolution(String equation)
            throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        String prompt = "Resolve a equação: " + equation +
                ". Devolve apenas JSON do tipo {\"operation\":\"solve\",\"result\":\"...\"}.";

        String jsonResponse = engine.sendPrompt(prompt);
        return JSONUtils.getJsonString(jsonResponse, "text");
    }

    public String askForCalculation(String text)
            throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        String prompt =
                "Interpreta o texto seguinte como um problema matemático e devolve apenas o resultado final. " +
                        "Texto: \"" + text + "\". " +
                        "Responde apenas em JSON no formato {\"operation\":\"calculate\",\"result\":\"...\"}.";

        String jsonResponse = engine.sendPrompt(prompt);
        return JSONUtils.getJsonString(jsonResponse, "text");
    }

    public String askForNatalSongLine(Object resultado)
            throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        String prompt =
                "Gera UMA única linha curta de música natalícia divertida. " +
                        "A linha DEVE incluir o valor \"" + resultado + "\". " +
                        "Não expliques nada. Não uses JSON. Apenas a frase.";

        return engine.sendPrompt(prompt);
    }

}
