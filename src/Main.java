public class Main {

    static String apiKey = "A-CHAVE-DA-AULA";
    static String url = "https://modelos.ai.ulusofona.pt/v1/completions";
    static String model = "gpt-4-turbo";
    static boolean useHack = false;

    public static void main(String[] args) throws Exception {

        LLMInteractionEngine engine =
                new LLMInteractionEngine(url, apiKey, model, useHack);

        LLMCalculadora llmCalc = new LLMCalculadora(engine);

        Comandos command = new Comandos(llmCalc);
        command.run();
    }
}
