package gr.homedeco.www.homedeco;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class PrivateMessage extends AppCompatActivity {

    private EditText etMessage;
    private ListView lvPrivateChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_message);

        etMessage = (EditText) findViewById(R.id.etMessage);
        lvPrivateChat = (ListView) findViewById(R.id.lvPrivateChat);
    }

    public void sendPrivateMessage(View view) {

        String message = etMessage.getText().toString();
    }
}
