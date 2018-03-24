import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Client {

    public static void main(String[] args) throws IOException {
        try {
            Client client = new Client();
            for (int i = 0; i < 100000; i ++) {
                client.fireInTheHole();
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fireInTheHole() throws Exception {
        URL url = new URL("http://localhost:8080");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        System.out.println(reader.readLine());
        reader.close();
        inputStream.close();
    }
}
