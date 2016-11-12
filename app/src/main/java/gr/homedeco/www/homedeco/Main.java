package gr.homedeco.www.homedeco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Show Register Activity
    public void showRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    //Show Login Activity
    public void showLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    //Show Product Details Activity
    public void showProductDetails(View view) {
        Intent intent = new Intent(this, ProductDetails.class);
        intent.putExtra("productID", 1);
        startActivity(intent);
    }

    public void showProducts(View view) {
        Intent intent = new Intent(this, Products.class);
        startActivity(intent);
    }
}
