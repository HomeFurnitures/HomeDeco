package gr.homedeco.www.homedeco;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class ServerRequests {
    ProgressDialog progressDialog;
    BufferedReader reader = null;


    public ServerRequests(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Φόρτωση");
        progressDialog.setMessage("Παρακαλώ περιμένετε...");
    }

//------------------------------------------------------------------------------------------------//
//                                    PRODUCTS
//------------------------------------------------------------------------------------------------//

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

        public FetchProductDataAsyncTask(int productID, GetProductCallback productCallback) {
            this.productID = productID;
            this.productCallback = productCallback;
        }

        @Override
        protected List<Product> doInBackground(Void... params) {
            List<Product> products = new ArrayList<>();
            ServerConnection connection = new ServerConnection();
            HttpURLConnection urlConnection;

            if (productID != 0) {
                urlConnection = connection.openGetConnection("/product/" + productID);
            } else {
                urlConnection = connection.openGetConnection("/product");
            }


            try {
                urlConnection.connect();

                InputStream is = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is));

                StringBuffer buffer = new StringBuffer();

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String result = buffer.toString();
                JSONparser parser = new JSONparser();
                products = parser.toProduct(result);


            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return products;
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            progressDialog.dismiss();
            productCallback.done(products);
            super.onPostExecute(products);
        }
    }

//------------------------------------------------------------------------------------------------//
//                                    LOGIN
//------------------------------------------------------------------------------------------------//

    //Try to log user in
    public void login(User user, GetLoginCallback loginCallback) {
        progressDialog.show();
        new loginAsynctask(user, loginCallback).execute();
    }

    //AsyncTask that try to log user in
    public class loginAsynctask extends AsyncTask<User, Void, String> {
        User user = new User();
        GetLoginCallback loginCallback;

        public loginAsynctask(User user, GetLoginCallback loginCallback) {
            this.user = user;
            this.loginCallback = loginCallback;
        }

        @Override
        protected String doInBackground(User... params) {
            ServerConnection connection = new ServerConnection();
            String authToken = null;
            HttpURLConnection urlConnection;
            JSONparser parser = new JSONparser();

            urlConnection = connection.openPostConnection("/login");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");

            try {

                urlConnection.connect();

                JSONObject json = parser.toLogin(user);
                OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
                out.write(json.toString());
                out.close();

                InputStream is = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is));

                StringBuffer buffer = new StringBuffer();

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String result = buffer.toString();
                authToken = parser.loginResponse(result);


            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return authToken;
        }

        @Override
        protected void onPostExecute(String authToken) {
            progressDialog.dismiss();
            loginCallback.done(authToken);
            super.onPostExecute(authToken);
        }
    }
}
