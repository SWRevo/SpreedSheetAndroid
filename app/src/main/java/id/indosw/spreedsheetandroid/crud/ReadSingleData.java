package id.indosw.spreedsheetandroid.crud;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import id.indosw.spreedsheetandroid.R;

public class ReadSingleData extends AppCompatActivity {

    String id;
    String name;
    private EditText uid1ET;
    private TextView id_l, name_l, id_v, name_v;
    View view;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_data);
        Button read = findViewById(R.id.insert_btn);
        uid1ET = findViewById(R.id.uid);
        id_l = findViewById(R.id.id_l);
        name_l = findViewById(R.id.name_l);
        id_v = findViewById(R.id.id_v);
        name_v = findViewById(R.id.name_v);
        view= this.getCurrentFocus();

        read.setOnClickListener(view -> {
            id = uid1ET.getText().toString();
            new ReadDataActivity().execute();
        });
    }


    @SuppressWarnings("deprecation")
    @SuppressLint("StaticFieldLeak")
    class ReadDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(ReadSingleData.this);
            dialog.setTitle("Hey Wait Please...");
            dialog.setMessage("Fetching your values");
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            Log.i(Controller.TAG, "IDVALUE" + id);
            JSONObject jsonObject = Controller.readData(id);
            Log.i(Controller.TAG, "Json obj " + jsonObject);

            try {
                if (jsonObject != null) {
                    JSONObject user = jsonObject.getJSONObject("user");
                    name = user.getString("name");
                }
            } catch (JSONException je) {
                Log.i(Controller.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();

            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            if (name != null) {
                id_l.setText("ID");
                name_l.setText("NAME");
                id_v.setText(id);
                name_v.setText(name);
            } else
                Toast.makeText(getApplicationContext(), "ID not found", Toast.LENGTH_LONG).show();
        }
    }
}