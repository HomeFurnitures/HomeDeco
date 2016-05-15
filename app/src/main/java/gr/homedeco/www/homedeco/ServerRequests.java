package gr.homedeco.www.homedeco;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ServerRequests {

    public static final String SERVER_ADDRESS = "http://www.compassbook.gr/";
    public static HttpURLConnection urlConnection;


    //--------------------------------- ASYNC TASKS ----------------------------------------------//


    //------------------------------ CONNECTION ESTABLISHERS -------------------------------------//

    //Establishes a HttpURLConnection with GET as a RequestMethod
    private HttpURLConnection openGetConnection(String uri, HashMap<String,String> dataToSend) {

        try {
            URL url = new URL(SERVER_ADDRESS + uri + getQueryData("GET", dataToSend));
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

    //------------------------------------ QUERY DATA --------------------------------------------//

    //Resolve dataToSend
    private String getQueryData(String method, HashMap<String, String> data) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;


        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (first) {
                if (method.equals("POST")) {
                    result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                } else {
                    result.append("?");
                }

                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                first = false;
            } else {
                result.append("&");
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
        }

        return result.toString();
    }
}
