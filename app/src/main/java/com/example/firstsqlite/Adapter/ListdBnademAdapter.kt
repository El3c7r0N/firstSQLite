package com.example.firstsqlite.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.firstsqlite.R
import com.example.firstsqlite.model.BnAdam
import kotlinx.android.synthetic.main.row_layout.view.*

class ListdBnademAdapter (internal var activity:Activity,
                          internal var lowel:List<BnAdam>,
                          internal var edt_id:EditText,
                          internal var edt_name:EditText,
                          internal var edt_email:EditText):BaseAdapter() {

    internal var inflater:LayoutInflater
    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val rowView:View
        rowView = inflater.inflate(R.layout.row_layout,null)

        rowView.txt_row_id.text = lowel[p0].id.toString()
        rowView.txt_row_name.text = lowel[p0].name.toString()
        rowView.txt_row_email.text = lowel[p0].email.toString()

        rowView.setOnClickListener {
            edt_id.setText(rowView.txt_row_id.text )
            edt_name.setText(rowView.txt_row_name.text )
            edt_email.setText(rowView.txt_row_email.text )

        }
        return rowView
    }

    override fun getItem(p0: Int): Any {
        return lowel[p0]
    }

    override fun getItemId(p0: Int): Long {
        return lowel[p0].id.toLong()
    }

    override fun getCount(): Int {
        return lowel.size
    }

}