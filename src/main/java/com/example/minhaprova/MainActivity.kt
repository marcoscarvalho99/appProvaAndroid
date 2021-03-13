package com.example.minhaprova

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.BD.Livro
import com.example.minhaprova.BD.LivroBDOpener
import com.example.minhaprova.Classes.SendMessageDialogFragment
import com.example.minhaprova.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var text:String
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        //resposta4
        exibirBoaVinda()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


//resposta6
        var c1 = Livro(0, "livro1", "sim", "1996", 2.2f)
        var c2 = Livro(0, "livro2", "sim", "1995", 2.2f)
        val db = LivroBDOpener(this)
        db.insert(c1)
        db.insert(c2)
//        c1=db.findById(4)
//        Toast.makeText(this,c1.ano.toString(),Toast.LENGTH_LONG).show()


//        var livros = db.findAll()
//        livros.forEach {
//            Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()
//            Log.i("AULABANCO", it.toString()) }




//resposta2
        binding.button1.setOnClickListener {
            var intent = Intent(this, ActivityAcao1::class.java)
            startActivityForResult(intent,1)

        }
        //resposta5
        binding.button2.setOnClickListener(){

            val dialog = SendMessageDialogFragment()
            dialog.isCancelable = false
            dialog.show(supportFragmentManager,"messege")
        }
//resposta7
        binding.button3.setOnClickListener(){
            var intent = Intent(this, ActivityAcao2::class.java)
            startActivityForResult(intent,3)
        }

        //resposta8
        binding.button4.setOnClickListener(){

            var intent = Intent(this, ActivityAcao3::class.java)
            startActivityForResult(intent,4)
        }
    }

//resposta2 foi necessario usar para sua elaboração
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            Activity.RESULT_CANCELED -> {
                Snackbar.make(binding.view,"cancelado",Snackbar.LENGTH_LONG).show()
            }
            Activity.RESULT_OK -> {
                when(requestCode){
                    1 ->{
                        val parametro:Bundle? = data?.extras

                        val texto=parametro?.getString("texto")

                        binding.textView1.text=texto
                    }
                    //resposta7
                    3 ->{
                      binding.textView2.text="cadastrado"
                    }
                }

            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //resposta3
        outState.putString("texto",binding.textView1.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //resposta3
        binding.textView1.text=savedInstanceState.getString("texto");

    }

    private fun exibirBoaVinda() {
        val pref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        if (pref.getBoolean("boasVindas", true)) {
            Toast.makeText(this, R.string.Bemvindo, Toast.LENGTH_SHORT).show()
            pref.edit().putBoolean("boasVindas", false).apply()
        }
    }
}