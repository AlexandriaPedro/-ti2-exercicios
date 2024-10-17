import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

/*
 * CERTAMENTE NÃO VOU COLOCAR MINHA CHAVE AQUI, mas é só substituir!
 */

public class AzureOpenAI {
    private static final String endpoint = "https://<seu-serviço>.openai.azure.com/";
    private static final String apiKey = "<sua-chave-azure>";

    public static void main(String[] args) {
        String prompt = "Explain the concept of cloud computing.";

        try {
            String response = sendPrompt(prompt);
            System.out.println("Response: " + response);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static String sendPrompt(String prompt) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String requestBody = new JSONObject()
                .put("prompt", prompt)
                .put("max_tokens", 100)
                .put("temperature", 0.7)
                .put("top_p", 1)
                .put("frequency_penalty", 0)
                .put("presence_penalty", 0)
                .toString();

        Request request = new Request.Builder()
                .url(endpoint)
                .post(RequestBody.create(requestBody, mediaType))
                .addHeader("api-key", apiKey)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }
}
