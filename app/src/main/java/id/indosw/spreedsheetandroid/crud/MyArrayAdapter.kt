package id.indosw.spreedsheetandroid.crud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import id.indosw.spreedsheetandroid.R

class MyArrayAdapter(context: Context, objects: List<MyDataModel>)
    : ArrayAdapter<MyDataModel>(context, 0, objects) {
    private var modelList: List<MyDataModel> = objects
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    override fun getItem(position: Int): MyDataModel {
        return modelList[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val vh: ViewHolder
        if (convertView == null) {
            val view = mInflater.inflate(R.layout.layout_row_view, parent, false)
            vh = ViewHolder.create(view as RelativeLayout)
            view.setTag(vh)
        } else {
            vh = convertView.tag as ViewHolder
        }
        val item = getItem(position)
        vh.textViewId.text = item.id
        vh.textViewName.text = item.name
        return vh.rootView
    }

    private class ViewHolder private constructor(
        val rootView: RelativeLayout,
        val textViewName: TextView,
        val textViewId: TextView
    ) {
        companion object {
            fun create(rootView: RelativeLayout): ViewHolder {
                val textViewId = rootView.findViewById<TextView>(R.id.textViewId)
                val textViewName = rootView.findViewById<TextView>(R.id.textViewName)
                return ViewHolder(rootView, textViewName, textViewId)
            }
        }
    }

}