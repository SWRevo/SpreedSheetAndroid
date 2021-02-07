package id.indosw.spreedsheetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import id.indosw.spreedsheetandroid.crud.SpreadsheetCrud;

public class HomeActivity extends AppCompatActivity {
    Button spreadSheetWithImage;
    Button spreadSheetCRUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        spreadSheetWithImage= findViewById(R.id.btn_spreadsheet_img);
        spreadSheetCRUD= findViewById(R.id.btn_crud_spreadsheet);
        spreadSheetWithImage.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SpreadsheetImage.class);
            startActivity(intent);
        });
        spreadSheetCRUD.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SpreadsheetCrud.class);
            startActivity(intent);
        });
    }
}
