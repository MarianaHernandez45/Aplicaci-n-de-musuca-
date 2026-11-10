package com.example.music_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {

private lateinit var etCorreo : EditText
private lateinit var etPass : EditText
private lateinit var btnInicio : Button

    private lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val tvCrearCuenta = findViewById<TextView>(R.id.CrearCuenta)

        etCorreo = findViewById(R.id.etCorreo)
        etPass = findViewById(R.id.etPassword)
        btnInicio = findViewById(R.id.btnIngresar)

        dbHelper = DatabaseHelper(this)

        //evento para iniciar secion

        btnInicio.setOnClickListener {
            //Agregagr metodo de inici de secion 
        }

        tvCrearCuenta.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // EVENTO PARA INICAR SESION
        private fun iniciarSesion(){

//acceder a lo que el usaurio escrube
            val correo = etCorreo.text.toString().trim()
            val pass = etPass.text.toString().trim()
            if (correo.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Rellena todo los campos", Toast.LENGTH_SHORT).show()

//return para salirse

                return
            }

// LLAMAR AL METODO DE INICIO DE SESION EN LA BASE DE DATOS (devolvera un boleano)
            val resultado = dbHelper.iniciarSecion(correo, pass)
            if (resultado) {

//si la sesion es correcta
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()

// EVENTO PARA INICAR SESION
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)

//CERRAR LA PANTALLA ACTUAL
                finish()
            } else {
                Toast.makeText(this, "Datos incoreectos", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
