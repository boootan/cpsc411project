package com.example.easypass

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.easypass.databinding.ActivityViewEntryBinding
import kotlinx.android.synthetic.main.activity_view_entry.*

class viewEntry : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityViewEntryBinding

    private lateinit var viewUserText: TextView
    private lateinit var viewPlatformText: TextView
    private lateinit var viewPassText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_entry).toString()
        binding = ActivityViewEntryBinding.inflate(layoutInflater)

        val context = this
        var db = PassDBHelper(context)
        viewUserText = findViewById(R.id.userView2)
        viewPlatformText = findViewById(R.id.platformView1)
        viewPassText = findViewById(R.id.passView2)


        var dbData = db.readData()
        val position = intent.getIntExtra("position", 0)
        val currentItem = dbData[position]
        viewUserText.text = currentItem.user.toString()
        viewPlatformText.text = currentItem.platform.toString()
        viewPassText.text = currentItem.pass.toString()

        val deleteButton = findViewById<Button>(R.id.deleteButton)
        deleteButton.setOnClickListener() {
            db.deleteData(position)
            finish()
        }



        setSupportActionBar(findViewById(R.id.toolbar3))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



    }


}