package com.example.easypass

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easypass.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.example_item.view.*

class MainActivity : AppCompatActivity(), Adapter.OnItemClickListener {
    private lateinit var exampledata: ArrayList<passItem>
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = this
        var db = PassDBHelper(context)

//        val entry1 = passItem("boootan", "bruh123", "facebook")
//        db.insertData(entry1)
//        val entry2 = passItem("maftean", "magus", "instagram")
//        db.insertData(entry2)
        val exampledata = db.readData()
        recycler_view.adapter = Adapter(exampledata, this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        val addButton = findViewById<FloatingActionButton>(R.id.fab)

        addButton.setOnClickListener() {
            val intent = Intent(this, addEntry::class.java)
            startActivity(intent)
            recycler_view.adapter?.notifyDataSetChanged()
        }

//        recycler_view.setOnClickListener() {
//            onItemClick()
     //   }
    }

    override fun onResume() {
        super.onResume()
        recycler_view.adapter?.notifyDataSetChanged()
    }



    override fun onItemClick(v: View, position: Int) {
        val intent = Intent(this, viewEntry::class.java)
        intent.putExtra("position", position)
        println(position)
        startActivity(intent)
    }


}