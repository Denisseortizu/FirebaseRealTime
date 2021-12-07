package com.example.firebaserealtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONObject

private lateinit var database : DatabaseReference

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myDB = FirebaseDatabase.getInstance()
        database = myDB.reference
        val values: Map<String, Any> = HashMap()

        //writeNewUser("001", "nombre2", "email2@correo.com")
        //database.child("usuarios").setValue("primer usuario")
        //database.child("usuarios").setValue("segundo usuario")
        val etId = findViewById<EditText>(R.id.etUsuarioId)
        val etNombre = findViewById<EditText>(R.id.etUsuarioN)
        val etCorreo = findViewById<EditText>(R.id.etUsuarioE)
        val btnSend = findViewById<Button>(R.id.btnSet)
        btnSend.setOnClickListener{
            writeNewUser(etId.text.toString(), etNombre.text.toString(), etCorreo.text.toString())
            etId.text.clear()
            etNombre.text.clear()
            etCorreo.text.clear()
        }
    }

    fun getUser(userID: String){
        database.child("usuarios").child(userID).get().addOnSuccessListener { record ->
            val json = JSONObject(record.value.toString())
            Log.d("Crudo", "Crudo: ${record.value}")
            Log.d("ValoresDeFireBase", "Got value ${json.getString("nombre")} correo:${json.getString("correo")}")
        }
    }

    fun writeNewUser(userID: String, name: String, email: String){
        val user = User(name, email)
        database.child("usersuarios").child(userID).setValue(user)
    }
    fun removeUser(userID: String){
        database.child("usuarios").child(userID).removeValue()
    }
}
class User(name: String, email: String){
    val nombre = name
    val correo = email
}