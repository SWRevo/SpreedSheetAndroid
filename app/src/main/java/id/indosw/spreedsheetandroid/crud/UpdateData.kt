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

public class UpdateData extends AppCompatActivity {

    String id;
    String name;
    private EditText uid1ET;
    private EditText nameET;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_data);
        Button update = findViewById(R.id.update_btn1);
        uid1ET= findViewById(R.id.uid);
        nameET= findViewById(R.id.name);

        update.setOnClickListener(view -> {
             id=uid1ET.getText().toString();
             name=nameET.getText().toString();
            new UpdateDataActivity().execute();
        });
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("StaticFieldLeak")
    class UpdateDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int x;
        String result=null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateData.this);
            dialog.setTitle("Hey Wait Please..."+x);
            dialog.setMessage("I am getting your JSON");
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = Controller.updateData(id,name);
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
