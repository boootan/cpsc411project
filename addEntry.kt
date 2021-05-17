package com.example.easypass

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.easypass.databinding.ActivityAddEntryBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.math.BigInteger
import java.security.MessageDigest

class addEntry : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAddEntryBinding

    private lateinit var textInputUser: AppCompatEditText
    private lateinit var textInputPlatform: AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_entry)
        setSupportActionBar(findViewById(R.id.toolbar2))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val context = this
        var db = PassDBHelper(context)
        textInputUser = findViewById(R.id.editTextUserName)
        textInputPlatform = findViewById(R.id.editPlatform)

    }

    fun validateUser(): Boolean {
        var username = textInputUser.text
        if (username != null) {
            if (username.isEmpty()) {
                textInputUser.setError("User field cannot be empty")
                return false;
            }
            else {
                textInputUser.setError(null)
                return true;
            }
        }
        else {
            return false
        }
    }

    fun validatePlatform(): Boolean {
        var username = textInputPlatform.text
        if (username != null) {
            if (username.isEmpty()) {
                textInputPlatform.setError("Platform field cannot be empty")
                return false;
            }
            else {
                textInputPlatform.setError(null)
                return true;
            }
        }
        else {
            return false
        }
    }



    @RequiresApi(Build.VERSION_CODES.N)
    fun generatePass(view: View){
        val context = this
        val seed = seedGenerator()
        val symbols = ("()!@#$%&*^").toCharArray()
        val capitalLetters = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray()
        var loopCount = 1
        val symbolCount = 4


        var hashed = md5(seed)
        var hashedPass = hashed.take(7)
        while (loopCount <= symbolCount) {
            hashedPass += symbols[(0..9).random()]
            hashedPass += capitalLetters[(0..25).random()]
            loopCount++
        }



        val newEntry = passItem()
        if (validateUser()) {
            newEntry.user = textInputUser.text.toString()
        }
        if (validatePlatform()) {
            newEntry.platform = textInputPlatform.text.toString()
        }
        newEntry.pass = hashedPass
        var db = PassDBHelper(context)
        db.insertData(newEntry)
        Toast.makeText(this, "Password Generated: $hashedPass", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32,'0')
    }

    fun seedGenerator(): String {
        val source = ('a'.. 'z') + ('A'..'Z') + ('0'..'9')
        var result = ""
        val min = 0
        val max = 61
        var loopCount = 0
        val seedLength = 10
        while(loopCount <= seedLength) {
            val randomIndex = (0..62).random()
            result += source[randomIndex.toInt()]
            loopCount++
        }


        return result
    }


}