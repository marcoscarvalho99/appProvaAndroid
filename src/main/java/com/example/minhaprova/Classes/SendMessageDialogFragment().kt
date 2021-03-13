package com.example.minhaprova.Classes

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.minhaprova.R

class SendMessageDialogFragment : DialogFragment() {

    //questão5
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.Importante)
            builder.setMessage(R.string.Cafe)
                    .setPositiveButton("Sim",
                            DialogInterface.OnClickListener { dialog, id ->
                                Toast.makeText(it.baseContext, R.string.Otimo, Toast.LENGTH_SHORT).show()
                            })
                    .setNegativeButton("Não",
                            DialogInterface.OnClickListener { dialog, id ->
                                Toast.makeText(it.baseContext, R.string.Proxima, Toast.LENGTH_SHORT).show()
                            })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}