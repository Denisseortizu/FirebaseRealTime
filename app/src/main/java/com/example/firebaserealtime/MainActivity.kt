package com.example.firebaserealtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        writeNewUser("001", "nombre2", "email2@correo.com")
        //database.child("usuarios").setValue("primer usuario")
        //database.child("usuarios").setValue("segundo usuario")
    }

    fun getUser(userID: String){
        database.child("users").child(userID).get().addOnSuccessListener { record ->
            Log.d("ValoresDeFireBase", "Got value ${record.value}")
        }.addOnFailureListener{ record ->
            Log.d("VlauesDeFireBase", "Error: $record")
        }
    }

    fun writeNewUser(userID: String, name: String, email: String){
        val user = User(name, email)
        database.child("users").child(userID).setValue(user)
    }
}
class User(name: String, email: String){
    val nombre = name
    val correo = email
}