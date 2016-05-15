package gr.homedeco.www.homedeco;


import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ServerRequests {

    public static final String SERVER_ADDRESS = "http://www.compassbook.gr/";
    public static HttpURLConnection urlConnection;

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
