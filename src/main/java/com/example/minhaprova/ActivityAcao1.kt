package com.example.minhaprova

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.databinding.ActivityAcao1Binding
import com.google.android.material.snackbar.Snackbar

class ActivityAcao1 : AppCompatActivity() {
    lateinit var binding:ActivityAcao1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acao1)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao1)

        //resposta2
        binding.buttonOk.setOnClickListener(){
            if (binding.editTextTextPersonName.text.toString()=="") {
                Toast.makeText(this, R.string.Invalido, Toast.LENGTH_SHORT).show()
            }
            else{

                val bundle = Bundle()
                bundle.putString("texto", binding.editTextTextPersonName.text.toString())
                intent.putExtras(bundle)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
        //resposta2
        binding.buttonCancelar.setOnClickListener(){

            setResult(Activity.RESULT_CANCELED, intent)
            finish()

        }
    }
}