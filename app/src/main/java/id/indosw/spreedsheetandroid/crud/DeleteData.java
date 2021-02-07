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


public class DeleteData extends AppCompatActivity {

    String id;
    private EditText uid1ET;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_data);
        Button delete = findViewById(R.id.delete_btn);
        uid1ET= findViewById(R.id.uid);

        delete.setOnClickListener(view -> {
            id=uid1ET.getText().toString();
            new DeleteDataActivity().execute();
        });
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("StaticFieldLeak")
    class DeleteDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        String result=null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(DeleteData.this);
            dialog.setTitle("Hey Wait Please...");
            dialog.setMessage("Deleting... ");
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
           Log.i(Controller.TAG,"IDVALUE"+id);
            JSONObject jsonObject = Controller.deleteData(id);
            Log.i(Controller.TAG, "Json obj "+jsonObject);

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
