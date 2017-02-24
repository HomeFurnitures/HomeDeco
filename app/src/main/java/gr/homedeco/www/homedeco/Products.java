package gr.homedeco.www.homedeco;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class Products extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductsAdapter adapter;

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

        recyclerView = (RecyclerView) findViewById(R.id.rvProducts);
        adapter = new ProductsAdapter(returnedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Products.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
