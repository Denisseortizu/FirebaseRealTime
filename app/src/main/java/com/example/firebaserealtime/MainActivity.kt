package com.example.firebaserealtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private lateinit var database : DatabaseReference

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myDB = FirebaseDatabase.getInstance()
        database = myDB.reference
        val values: Map<String, Any> = HashMap()
        database.setValue("Hola Mundo!")
        database.child("hijo").child("nieto").setValue("Nieto")


        //database.child("usuarios").setValue("primer usuario")
        //database.child("usuarios").setValue("segundo usuario")
    }
}