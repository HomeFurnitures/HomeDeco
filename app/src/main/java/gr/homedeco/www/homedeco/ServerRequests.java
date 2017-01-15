package gr.homedeco.www.homedeco;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
    private ProgressDialog progressDialog;
    private BufferedReader reader = null;
    private LocalDatabase localDatabase;
    public HttpURLConnection urlConnection2;


    public ServerRequests(Context context) {
        localDatabase = new LocalDatabase(context);
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Φόρτωση");
        progressDialog.setMessage("Παρακαλώ περιμένετε...");
    }

//------------------------------------------------------------------------------------------------//
//                                    PRODUCTS
//------------------------------------------------------------------------------------------------//

    // Fetches Product's data from the server
    public User fetchProductDataInBackground(int productID, GetProductCallback productCallback) {
        progressDialog.show();
        new FetchProductDataAsyncTask(productID, productCallback).execute();
        return null;
    }

    // AsyncTask that get User's data from the server
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

    // Try to log user in
    public void login(User user, GetLoginCallback loginCallback) {
        progressDialog.show();
        new loginAsyncTask(user, loginCallback).execute();
    }

    //AsyncTask that try to log user in
    public class loginAsyncTask extends AsyncTask<User, Void, String> {
        User user = new User();
        GetLoginCallback loginCallback;

        public loginAsyncTask(User user, GetLoginCallback loginCallback) {
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

            try {

                urlConnection.connect();

                JSONObject json = parser.toLogin(user);
                OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
                out.write(json.toString());
                out.close();

                InputStream is = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is));

                StringBuilder strBuilder = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    strBuilder.append(line);
                }

                String result = strBuilder.toString();
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

//------------------------------------------------------------------------------------------------//
//                                    USER
//------------------------------------------------------------------------------------------------//

    // Get user's details
    public void getUserDetails(GetUserDetailsCallback userDetailsCallback) {
        progressDialog.show();
        new getUserDetailsAsyncTask(userDetailsCallback).execute();
    }

    // AsyncTask that try to log user in
    public class getUserDetailsAsyncTask extends AsyncTask<Void, Void, User> {
        GetUserDetailsCallback userDetailsCallback;

        public getUserDetailsAsyncTask(GetUserDetailsCallback userDetailsCallback) {
            this.userDetailsCallback = userDetailsCallback;
        }

        @Override
        protected User doInBackground(Void... params) {
            ServerConnection connection = new ServerConnection();
            User returnedUser = new User();
            HttpURLConnection urlConnection;
            JSONparser parser = new JSONparser();

            urlConnection = connection.openGetConnection("/user/self");
            urlConnection.setRequestProperty("android", "true");

            try {

                urlConnection.connect();

                int status = urlConnection.getResponseCode();
                System.out.println(status);

                InputStream is = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is));

                StringBuilder strBuilder = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    strBuilder.append(line);
                }

                String result = strBuilder.toString();
                Log.d("Result apo self", result);
                returnedUser = parser.toUser(result);


            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser) {
            progressDialog.dismiss();
            userDetailsCallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }
}