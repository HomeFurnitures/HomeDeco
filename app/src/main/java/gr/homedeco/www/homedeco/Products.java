package gr.homedeco.www.homedeco;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class Products extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        getProducts();
    }


    private void getProducts() {

        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.fetchProductDataInBackground(0, new GetProductCallback() {
            @Override
            public void done(List<Product> returnedList) {
                populateProductsList(returnedList);
            }
        });
    }

    private void populateProductsList(List<Product> returnedList) {


        ListAdapter myAdapter = new CustomProductsAdapter(this, returnedList);
        ListView friendListView = (ListView) findViewById(R.id.lvProducts);
        friendListView.setAdapter(myAdapter);
        friendListView.setItemsCanFocus(true);
    }
}
