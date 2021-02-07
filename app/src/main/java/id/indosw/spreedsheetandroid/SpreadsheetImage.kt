package id.indosw.spreedsheetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SpreadsheetImage extends AppCompatActivity {

    Button addUser;
    Button viewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spreadsheet_image);
        addUser= findViewById(R.id.btn_add_user);
        viewUser= findViewById(R.id.btn_list_user);
        addUser.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddUser.class);
            startActivity(intent);
        });
        viewUser.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), UserList.class);
            startActivity(intent);
        });
    }
}
