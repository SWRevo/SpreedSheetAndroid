package id.indosw.spreedsheetandroid.crud;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import id.indosw.spreedsheetandroid.R;

public class ReadAllData extends AppCompatActivity {

    private ArrayList<MyDataModel> list;
    private MyArrayAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_all);
        Button readAll = findViewById(R.id.readAll_btn1);
        list = new ArrayList<>();
        adapter = new MyArrayAdapter(this, list);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        readAll.setOnClickListener(view -> new ReadData1().execute());
    }


    @SuppressWarnings("deprecation")
    @SuppressLint("StaticFieldLeak")
    class ReadData1 extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            x = list.size();
            if (x == 0)
                jIndex = 0;
            else
                jIndex = x;
            dialog = new ProgressDialog(ReadAllData.this);
            dialog.setTitle("Hey Wait Please..." + x);
            dialog.setMessage("Fetching all the Values");
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = Controller.readAllData();
            try {
                if (jsonObject != null) {
                    if (jsonObject.length() > 0) {
                        JSONArray array = jsonObject.getJSONArray("records");
                        int lenArray = array.length();
                        if (lenArray > 0) {
                            for (; jIndex < lenArray; jIndex++) {
                                MyDataModel model = new MyDataModel();
                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String id = innerObject.getString("id");
                                String name = innerObject.getString("username");
                                //  String image = innerObject.getString(Keys.KEY_IMAGE);
                                //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                                //String phone = phoneObject.getString(Keys.KEY_MOBILE);
                                model.setName(name);
                                model.setCountry(id);
                                //model.setImage(image);
                                list.add(model);
                            }
                        }
                    }
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
            if (list.size() > 0) {
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
            }
        }
    }
}



