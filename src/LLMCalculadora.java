

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
}
