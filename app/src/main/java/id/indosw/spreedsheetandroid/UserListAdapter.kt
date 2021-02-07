package id.indosw.spreedsheetandroid

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class UserListAdapter(
    private val context: Activity,
    private val uId: Array<String?>,
    private val uNames: Array<String?>,
    private val uImages: Array<String?>
) : ArrayAdapter<String?>(
    context, R.layout.list_row, uId
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        @SuppressLint("ViewHolder", "InflateParams") val listViewItem =
            inflater.inflate(R.layout.list_row, null, true)
        val textViewId = listViewItem.findViewById<TextView>(R.id.tv_uid)
        val textViewName = listViewItem.findViewById<TextView>(R.id.tv_uname)
        val iv = listViewItem.findViewById<CircleImageView>(R.id.imageView3)
        textViewId.text = uId[position]
        textViewName.text = uNames[position]
        //Uri uri = Uri.parse(uImages[position]);
        //Uri uri = Uri.parse("https://drive.google.com/uc?id=0B___GhMLUVtOY09SbDU5cDU2T1U");
        //draweeView.setImageURI(uri);
        Picasso.get().load(uImages[position]).into(iv)
        return listViewItem
    }
}