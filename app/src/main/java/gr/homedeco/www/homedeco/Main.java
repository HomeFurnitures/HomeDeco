package gr.homedeco.www.homedeco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
