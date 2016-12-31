package gr.homedeco.www.homedeco;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private CheckBox cbRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        cbRememberMe = (CheckBox) findViewById(R.id.cbRememberMe);
    }

    public void logIn(View view) {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        User userToLogin = new User(username, password);

        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.login(userToLogin, new GetLoginCallback() {
            @Override
            public void done(String response) {

            }
        });
    }
}
