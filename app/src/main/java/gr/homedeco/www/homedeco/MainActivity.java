package gr.homedeco.www.homedeco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Show Register Activity
    public void showRegister(View view) {
        Intent intentRegister = new Intent(this, Register.class);
        startActivity(intentRegister);
    }

    //Show Login Activity
    public void showLogin(View view) {
        Intent intentRegister = new Intent(this, Login.class);
        startActivity(intentRegister);
    }
}
