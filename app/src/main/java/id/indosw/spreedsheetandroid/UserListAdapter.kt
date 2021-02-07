package id.indosw.spreedsheetandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class UserListAdapter extends ArrayAdapter<String> {
    private final String[] uId;
    private final String[] uNames;
    private final String[] uImages;
    private final Activity context;

    public UserListAdapter(Activity context, String[] uId, String[] uNames, String[] uImages) {
        super(context, R.layout.list_row, uId);
        this.context = context;
        this.uId = uId;
        this.uNames = uNames;
        this.uImages = uImages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"})
        View listViewItem = inflater.inflate(R.layout.list_row, null, true);
        TextView textViewId = listViewItem.findViewById(R.id.tv_uid);
        TextView textViewName = listViewItem.findViewById(R.id.tv_uname);
        ImageView iv = listViewItem.findViewById(R.id.imageView3);
        textViewId.setText(uId[position]);
        textViewName.setText(uNames[position]);
        //Uri uri = Uri.parse(uImages[position]);
        //Uri uri = Uri.parse("https://drive.google.com/uc?id=0B___GhMLUVtOY09SbDU5cDU2T1U");
        //draweeView.setImageURI(uri);
        Picasso.get().load(uImages[position]).into(iv);
        return listViewItem;
    }
}