package gr.homedeco.www.homedeco;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {

    private final String SERVER_ADDRESS = "http://83.212.107.169/home-deco/public";
    private HttpURLConnection urlConnection;

    public Connection() {
    }

    //Establishes a HttpURLConnection with GET as a RequestMethod
    public HttpURLConnection openGetConnection(String uri) {

        try {
            URL url = new URL(SERVER_ADDRESS + uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return urlConnection;
    }

    //Establishes a HttpURLConnection with POST as a RequestMethod
    private HttpURLConnection openPostConnection(String uri) {

        try {
            URL url = new URL(SERVER_ADDRESS + uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return urlConnection;
    }
}
