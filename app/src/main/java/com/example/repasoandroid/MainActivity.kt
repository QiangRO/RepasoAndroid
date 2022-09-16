package com.example.repasoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.*
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var txtMensaje: TextView
    private lateinit var btnProcesar: Button
    private lateinit var etNombre: EditText
    private lateinit var etMensaje: EditText
    private lateinit var swConcatenar: Switch
    private var mensaje: String? = null
    private var mensaje2: String? = null

    private lateinit var tts:TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarViews()
        mensaje="Don't worry be happy"
        mensaje2 = ""
        //Las llaves representan una funcion que recibe otra funcion
        //orden superior
        //Funcion anonima de una interfaz
        btnProcesar.setOnClickListener{ speakMessage()}
        tts = TextToSpeech(this, this)
    }
    private fun inicializarViews(){
        txtMensaje = findViewById(R.id.txtMensaje)
        btnProcesar = findViewById(R.id.btnProcesar)
        etNombre = findViewById(R.id.etNombre)
        etMensaje = findViewById(R.id.etMensaje)
        swConcatenar = findViewById(R.id.swConcatenar)
        /* Otra forma de inicializar, pero de forma directa
        findViewById<TextView>(R.id.txtMensaje)
        findViewById<TextView>(R.id.btnProcesar)*/
        /*This 1: Contexto de pantalla
        * This 2: Busca si en la clase actual esta implementada la regla de juego*/
    }
    private fun speakMessage(){
        if (swConcatenar.isChecked){
            mensaje2 = etMensaje.text.toString()+etNombre.text.toString()
            tts.speak(mensaje2, TextToSpeech.QUEUE_FLUSH, null, "")
        }
        else{
            txtMensaje.text =mensaje
            tts.speak(mensaje, TextToSpeech.QUEUE_FLUSH, null, "")
        }
    }

    override fun onInit(status: Int) {
        var respuesta = if (status == TextToSpeech.SUCCESS){
            //tts.language = Locale.US
            //cambiar idioma a español
            tts.language = Locale("ES","MEX")
            //tts.language = Locale.CHINESE
            "Todo ha salido bien"
        }else "Algo ha fallado, prueba mas tarde"
        Toast.makeText(this,respuesta, Toast.LENGTH_SHORT).show()
    }
}