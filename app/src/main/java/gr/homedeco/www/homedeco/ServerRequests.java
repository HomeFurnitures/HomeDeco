package gr.homedeco.www.homedeco;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerRequests {

    public static final String SERVER_ADDRESS = "http://83.212.107.169/home-deco/public";
    public static HttpURLConnection urlConnection;
    ProgressDialog progressDialog;
    BufferedReader reader = null;

    public ServerRequests(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Φόρτωση");
        progressDialog.setMessage("Παρακαλώ περιμένετε...");
    }

    //--------------------------------- ASYNC TASKS ----------------------------------------------//

    //Fetches Product's data from the server
    public User fetchProductDataInBackground(int productID, GetProductCallback productCallback) {
        progressDialog.show();
        new FetchProductDataAsyncTask(productID, productCallback).execute();
        return null;
    }

    //AsyncTask that get User's data from the server
    public class FetchProductDataAsyncTask extends AsyncTask<Void, Void, List<Product>> {

        int productID;
        GetProductCallback productCallback;

        //FetchUserDataAsyncTask constructor
        public FetchProductDataAsyncTask(int productID, GetProductCallback productCallback) {
            this.productID = productID;
            this.productCallback = productCallback;
        }



        @Override
        protected List<Product> doInBackground(Void... params) {
            List<Product> products = new ArrayList<>();
            Product returnedProduct;

            try {

                if (productID != 0) {
                    openGetConnection("/product/" + productID);
                } else {
                    openGetConnection("/product");
                }

                urlConnection.connect();

                InputStream is = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is));

                StringBuffer buffer = new StringBuffer();

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String result = buffer.toString();
                JSONArray jArray = new JSONArray(result);

                for (int i=0; i<jArray.length(); i++) {
                    JSONObject jObject = jArray.getJSONObject(i);

                    if (jObject.length() != 0) {
                        int productID = jObject.getInt("ProductID");
                        String SKU = jObject.getString("SKU");
                        String name = jObject.getString("Name");
                        String desc = jObject.getString("Description");
                        String shortDesc = jObject.getString("ShortDescription");
                        String image = jObject.getString("Image");

                        returnedProduct = new Product();
                        returnedProduct.setProductID(productID);
                        returnedProduct.setName(name);
                        returnedProduct.setDescription(desc);
                        returnedProduct.setShortDescription(shortDesc);
                        returnedProduct.setImage(image);

                        products.add(returnedProduct);
                    }
                }




            } catch (JSONException | IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return products;
        }

        @Override
        protected void onPostExecute(List<Product> returnedProduct) {
            progressDialog.dismiss();
            productCallback.done(returnedProduct);
            super.onPostExecute(returnedProduct);
        }
    }









    //------------------------------ CONNECTION ESTABLISHERS -------------------------------------//

    //Establishes a HttpURLConnection with GET as a RequestMethod
    private HttpURLConnection openGetConnection(String uri) {

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
