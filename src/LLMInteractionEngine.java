import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * Classe responsável por gerir a interação com um serviço LLM (Large Language Model)
 * através de pedidos HTTP.
 * <p>
 * Permite enviar prompts para um endpoint configurado, com opção de ignorar
 * problemas de certificados SSL através de um "hack".
 */
public class LLMInteractionEngine {

    /** URL do endpoint do serviço LLM */
    String url;

    /** Chave de autenticação da API */
    String apiKey;

    /** Nome ou identificador do modelo a utilizar */
    String model;

    /** Indica se deve ser usado o hack para ignorar validação de certificados SSL */
    boolean useHack;

    /**
     * Construtor completo da classe.
     *
     * @param url URL do endpoint do serviço LLM
     * @param apiKey Chave da API para autenticação
     * @param model Modelo a utilizar
     * @param useHack Indica se o hack SSL deve ser utilizado
     */
    LLMInteractionEngine(String url, String apiKey, String model, boolean useHack) {
        this.url = url;
        this.apiKey = apiKey;
        this.model = model;
        this.useHack = useHack;
    }

    /**
     * Construtor que inicializa a classe sem utilizar o hack SSL.
     *
     * @param url URL do endpoint do serviço LLM
     * @param apiKey Chave da API para autenticação
     * @param model Modelo a utilizar
     */
    LLMInteractionEngine(String url, String apiKey, String model) {
        this.url = url;
        this.apiKey = apiKey;
        this.model = model;
        this.useHack = false;
    }

    /**
     * Constrói uma string JSON com o modelo e o prompt fornecidos.
     *
     * @param model Modelo a incluir no JSON
     * @param prompt Prompt a enviar ao serviço LLM
     * @return String no formato JSON
     */
    String buildJSON(String model, String prompt) {
        String json = "";
        json += "{";
        json += "\"" + "model" + "\": " + "\"" + model + "\",";
        json += "\"" + "prompt" + "\" :" + "\"" + prompt + "\"";
        json += "}";
        return json;
    }

    /**
     * Envia um prompt para o serviço LLM.
     * <p>
     * Caso o modo hack esteja ativo, utiliza uma versão alternativa
     * que ignora validações de certificados SSL.
     *
     * @param prompt Texto a enviar para o modelo
     * @return Resposta devolvida pelo serviço LLM
     * @throws IOException Erro de I/O durante o envio do pedido
     * @throws InterruptedException Se a execução for interrompida
     * @throws NoSuchAlgorithmException Se o algoritmo SSL não estiver disponível
     * @throws KeyManagementException Se ocorrer erro na gestão de chaves SSL
     */
    String sendPrompt(String prompt) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {
        if(useHack) {
            return sendPrompt_Hack(prompt);
        }
        // pedido normal
        HttpClient client = HttpClient.newHttpClient();
        String json = buildJSON(model, prompt);
        return sendRequestToClientAndGetReply(client, url, apiKey, json);
    }

    /**
     * Envia um prompt para o serviço LLM ignorando a validação de certificados SSL.
     * <p>
     * Este método aplica um hack que aceita qualquer certificado, sendo
     * potencialmente inseguro.
     *
     * @param prompt Texto a enviar para o modelo
     * @return Resposta devolvida pelo serviço LLM
     * @throws IOException Erro de I/O durante o envio do pedido
     * @throws InterruptedException Se a execução for interrompida
     * @throws NoSuchAlgorithmException Se o algoritmo SSL não estiver disponível
     * @throws KeyManagementException Se ocorrer erro na gestão de chaves SSL
     */
    String sendPrompt_Hack(String prompt) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

        // *************
        // hack por causa dos certificados
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, new TrustManager[]{ new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] c, String a) {}
            public void checkServerTrusted(X509Certificate[] c, String a) {}
            public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
        }}, new SecureRandom());

        HttpClient insecureClient = HttpClient.newBuilder().sslContext(sc).build();
        // fim do hack
        // *************

        String json = buildJSON(model, prompt);

        return sendRequestToClientAndGetReply(insecureClient, url, apiKey, json);
    }

    /**
     * Envia um pedido HTTP POST para o serviço LLM e devolve a resposta.
     *
     * @param client Cliente HTTP a utilizar
     * @param url URL do endpoint
     * @param apiKey Chave da API para autenticação
     * @param json Corpo do pedido em formato JSON
     * @return Corpo da resposta HTTP como string
     * @throws IOException Erro de I/O durante o envio do pedido
     * @throws InterruptedException Se a execução for interrompida
     */
    String sendRequestToClientAndGetReply(HttpClient client, String url, String apiKey, String json) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

        return resp.body();
    }
}
