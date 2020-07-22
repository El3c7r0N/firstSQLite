package com.example.firstsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstsqlite.Adapter.ListdBnademAdapter
import com.example.firstsqlite.DBHelper.DBHelper
import com.example.firstsqlite.model.BnAdam
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var db:DBHelper
    internal  var lowelin:List<BnAdam> = ArrayList<BnAdam>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)
        refreshData()

        btn_add.setOnClickListener {
            val bnadem = BnAdam(edt_id.text.toString().toInt(),
                edt_name.text.toString(),
                edt_email.text.toString())
            db.addBnadem(bnadem)
            refreshData()
        }

        btn_update.setOnClickListener {
            val bnadem = BnAdam(edt_id.text.toString().toInt(),
                edt_name.text.toString(),
                edt_email.text.toString())
            db.updateBnadem(bnadem)
            refreshData()
        }
        btn_delete.setOnClickListener {
            val bnadem = BnAdam(edt_id.text.toString().toInt(),
                edt_name.text.toString(),
                edt_email.text.toString())
            db.deleteBnadem(bnadem)
            refreshData()
        }
    }

    private fun refreshData() {
        lowelin = db.midden
        val adapter = ListdBnademAdapter(this, lowelin, edt_id,edt_name,edt_email)
        list_midden.adapter = adapter
    }
}