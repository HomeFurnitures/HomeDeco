package gr.homedeco.www.homedeco;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    private EditText etUsername, etPassword , etFirstName, etLastname, etEmail, etAddress,
                    etAddressNumber, etTK, etCity, etState, etCountry, etPhone, etMobilePhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = (EditText) findViewById(R.id.etRegUsername);
        etPassword = (EditText) findViewById(R.id.etRegPassword);
        etFirstName = (EditText) findViewById(R.id.etRegFirstName);
        etLastname = (EditText) findViewById(R.id.etRegLastName);
        etEmail = (EditText) findViewById(R.id.etRegEmail);
        etAddress = (EditText) findViewById(R.id.etRegAddress);
        etAddressNumber  = (EditText) findViewById(R.id.etRegAddressNumber);
        etTK = (EditText) findViewById(R.id.etRegTK);
        etCity = (EditText) findViewById(R.id.etRegCity);
        etState = (EditText) findViewById(R.id.etRegState);
        etCountry = (EditText) findViewById(R.id.etRegCountry);
        etPhone = (EditText) findViewById(R.id.etRegPhone);
        etMobilePhone = (EditText) findViewById(R.id.etRegMobilePhone);
    }

    public void registerUser(View view) {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String firstName = etFirstName.getText().toString();
        String lastName = etLastname.getText().toString();
        String email = etEmail.getText().toString();
        String address = etAddress.getText().toString();
        int addressNumber = Integer.parseInt(etAddressNumber.getText().toString());
        int TK = Integer.parseInt(etTK.getText().toString());
        String city = etCity.getText().toString();
        String state = etState.getText().toString();
        String country = etCountry.getText().toString();
        String phone = etPhone.getText().toString();
        String mobilePhone = etMobilePhone.getText().toString();

    }
}