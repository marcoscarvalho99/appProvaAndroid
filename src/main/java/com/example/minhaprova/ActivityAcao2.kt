package com.example.minhaprova

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.BD.Livro
import com.example.minhaprova.BD.LivroBDOpener
import com.example.minhaprova.databinding.ActivityAcao1Binding
import com.example.minhaprova.databinding.ActivityAcao2Binding

class ActivityAcao2 : AppCompatActivity() {
    lateinit var binding: ActivityAcao2Binding
     var nota :Float=0.0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao2)

        binding.ratingBarNota.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->


            nota= rating.toFloat()
        }


        binding.buttonSalvar.setOnClickListener(){

            val name:String=binding.editTextName.text.toString()
            val compra:String=binding.editTextCompra.text.toString()
            val descr:String=binding.editTextDescricao.text.toString()

            var c1 = Livro(0,name ,compra, descr,nota )
            val db = LivroBDOpener(this)
            db.insert(c1)

            setResult(Activity.RESULT_OK, intent)
            finish()

        }
        binding.buttonCancelar.setOnClickListener(){
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }

    }
}