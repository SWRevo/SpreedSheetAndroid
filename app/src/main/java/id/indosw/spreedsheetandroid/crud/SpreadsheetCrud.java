package id.indosw.spreedsheetandroid.crud;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.indosw.spreedsheetandroid.R;

public class SpreadsheetCrud extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spreadsheet_crud);
        Button read = findViewById(R.id.read_btn);
        Button readAll = findViewById(R.id.read_all_btn);
        Button insert = findViewById(R.id.insert_btn);
        Button update = findViewById(R.id.update_btn);
        Button delete = findViewById(R.id.delete_btn);

        readAll.setOnClickListener(view -> {
            if (InternetConnection.checkConnection(getApplicationContext())) {
                Intent intent=new Intent(getApplicationContext(),ReadAllData.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
            }
        });

        insert.setOnClickListener(view -> {
            if (InternetConnection.checkConnection(getApplicationContext())) {
                Intent intent=new Intent(getApplicationContext(),InsertData.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
            }
        });


        update.setOnClickListener(view -> {
            if (InternetConnection.checkConnection(getApplicationContext())) {
                Intent intent=new Intent(getApplicationContext(),UpdateData.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
            }
        });


        read.setOnClickListener(view -> {
            if (InternetConnection.checkConnection(getApplicationContext())) {
                Intent intent=new Intent(getApplicationContext(),ReadSingleData.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
            }
        });

        delete.setOnClickListener(view -> {
            if (InternetConnection.checkConnection(getApplicationContext())) {
                Intent intent=new Intent(getApplicationContext(),DeleteData.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }
}
