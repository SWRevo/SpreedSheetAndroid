package id.indosw.spreedsheetandroid.crud;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import id.indosw.spreedsheetandroid.R;

public class InsertData extends AppCompatActivity {

    String id;
    String name;
    private EditText uid1ET;
    private EditText nameET;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_data);
        Button insert = findViewById(R.id.insert_btn);
        uid1ET= findViewById(R.id.uid);
        nameET= findViewById(R.id.name);

        insert.setOnClickListener(view -> {
             id=uid1ET.getText().toString();
             name=nameET.getText().toString();
            new InsertDataActivity().execute();
        });
    }



    @SuppressWarnings("deprecation")
    @SuppressLint("StaticFieldLeak")
    class InsertDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        String result=null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(InsertData.this);
            dialog.setTitle("Hey Wait Please...");
            dialog.setMessage("Inserting your values..");
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = Controller.insertData(id,name);
            Log.i(Controller.TAG, "Json obj ");

            try {
                if (jsonObject != null) {
                     result=jsonObject.getString("result");
                }
            } catch (JSONException je) {
                Log.i(Controller.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
           Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
        }
    }
}
