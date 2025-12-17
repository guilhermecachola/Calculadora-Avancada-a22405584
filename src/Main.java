public class Main {
    static String apiKey = "sk-rUw_10kdjpBaPh5BbEr7DQ";
    static String url = "https://modelos.ai.ulusofona.pt/v1/completions";
    static String model = "gpt-4-turbo";
    static boolean useHack = true;
    public static void main(String[] args) {

        
        LLMInteractionEngine engine = new LLMInteractionEngine(url, apiKey, model, useHack);
        LLMCalculadora llm = new LLMCalculadora(engine);

        new CalculadoraSwing(llm);
    }
}

