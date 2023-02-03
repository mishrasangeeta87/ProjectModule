import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpLib {
    OkHttpClient client;

    public HttpLib() {
        client = new OkHttpClient();
    }

    String getExampleDotCom() {
        Request request = new Request.Builder()
                .url("https://example.com")
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
