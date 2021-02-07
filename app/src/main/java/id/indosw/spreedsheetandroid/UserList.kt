package id.indosw.spreedsheetandroid;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static id.indosw.spreedsheetandroid.Configuration.LIST_USER_URL;

public class UserList extends AppCompatActivity {

    private ListView listView;
    UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
        listView = findViewById(R.id.list_view);
        sendRequest();
    }
    private void sendRequest(){
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(LIST_USER_URL,
                response -> {
                    //Log.e("null","ser image"+response);
                    showJSON(response);
                    loading.dismiss();
                },
                error -> Toast.makeText(UserList.this,error.getMessage(),Toast.LENGTH_LONG).show());
        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String json){
        JsonParser pj = new JsonParser(json);
        pj.parseJSON();
        //Log.e("uImage","ser image"+JsonParserKt.uImages[1]);
        userListAdapter = new UserListAdapter(this, JsonParser.uIds,JsonParser.uNames,JsonParser.uImages);
        listView.setAdapter(userListAdapter);
    }
}