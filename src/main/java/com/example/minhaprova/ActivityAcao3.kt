package com.example.minhaprova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Trace.isEnabled
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.BD.Livro
import com.example.minhaprova.BD.LivroBDOpener
import com.example.minhaprova.databinding.ActivityAcao2Binding
import com.example.minhaprova.databinding.ActivityAcao3Binding

class ActivityAcao3 : AppCompatActivity() {
    var cont:Int=1;


    lateinit var binding: ActivityAcao3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_acao3)
        val db = LivroBDOpener(this)
        fecharBt1()
        fecharBtn2()

        var livro= db.findById(cont)
        exibirTela(livro)

        binding.buttonProximo.setOnClickListener(){
             contadorProximo()
            var livro= db.findById(cont)
            exibirTela(livro)
            fecharBtn2()
            fecharBt1()

        }
        binding.buttonAnterior.setOnClickListener {
        contadorAnterio()
            var livro= db.findById(cont)
            exibirTela(livro)
            fecharBt1()
            fecharBtn2()
        }

    }
    fun contadorProximo(){
        if (quantidadeMaxima()>cont)
        cont++
    }
    fun contadorAnterio(){
        if (cont>1){
            cont--
        }
    }
    fun quantidadeMaxima():Int{
        var max=0
        val db = LivroBDOpener(this)
        var livros = db.findAll()

        livros.forEach {
        max++;

       }

        return max
    }
    fun fecharBt1(){
        if(cont==1 ){
            binding.buttonAnterior.isEnabled=(false)
        }
        if(cont>1){
            binding.buttonAnterior.isEnabled=true
        }
    }
    fun fecharBtn2(){
        if(cont==quantidadeMaxima()){
            binding.buttonProximo.isEnabled=(false)

        }
        if(cont<quantidadeMaxima()){
            binding.buttonProximo.isEnabled=true
        }
    }
    fun exibirTela(livro:Livro){
        binding.textViewAno.text=livro.ano.toString()
        binding.textViewTitulo.text=livro.nome.toString()
        binding.textViewAutor.text=livro.autor.toString()
        binding.textViewNota.text=livro.nota.toString()

    }
}