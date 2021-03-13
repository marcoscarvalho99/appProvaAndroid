package com.example.minhaprova.BD


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log

class LivroBDOpener (context : Context) : SQLiteOpenHelper(context, LivroContrato.DATABASE_NAME, null, LivroContrato.DATA_BASE_VERSION) {

    val TAG = "sql"
    val SQL_CREATE_TABLE =
            "CREATE TABLE ${LivroContrato.LivroEntry.TABLE_NAME}" +
                    "( ${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                    " ${LivroContrato.LivroEntry.NOME} TEXT," +
                    " ${LivroContrato.LivroEntry.ANO} TEXT," +
                    " ${LivroContrato.LivroEntry.AUTOR} TEXT," +
                    " ${LivroContrato.LivroEntry.NOTA} FLOAT" +
                    ")"
    val SQL_DROP_TABLE =
            "DROP TABLE ${LivroContrato.LivroEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        Log.i(TAG, "Banco de dados Criado")
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db.execSQL(SQL_DROP_TABLE)
            db.execSQL(SQL_CREATE_TABLE)
        }
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db.execSQL(SQL_DROP_TABLE)
            db.execSQL(SQL_CREATE_TABLE)
        }
    }

    fun insert(c: Livro) {
        var banco: SQLiteDatabase = writableDatabase
        try {

            var values = ContentValues()
            values.put(LivroContrato.LivroEntry.NOME, c.nome)
            values.put(LivroContrato.LivroEntry.AUTOR, c.autor)
            values.put(LivroContrato.LivroEntry.ANO, c.ano)
            values.put(LivroContrato.LivroEntry.NOTA, c.nota)

            banco.insert(LivroContrato.LivroEntry.TABLE_NAME, null, values)
            //banco.insert(CarroContrato.CarroEntry.TABLE_NAME, CarroContrato.CarroEntry.NOME, values)

        } finally {
            banco.close()
        }
    }

    fun update(c: Livro) {
        var banco: SQLiteDatabase = writableDatabase
        try {

            var values = ContentValues()
            values.put(LivroContrato.LivroEntry.NOME, c.nome)
            values.put(LivroContrato.LivroEntry.AUTOR, c.autor)
            values.put(LivroContrato.LivroEntry.ANO, c.ano)
            values.put(LivroContrato.LivroEntry.NOTA, c.nota)

            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${c.id}")

            banco.update(LivroContrato.LivroEntry.TABLE_NAME, values, selection, whereArgs)

        } finally {
            banco.close()
        }
    }

    fun delete(c: Livro) {
        var banco: SQLiteDatabase = writableDatabase
        try {

            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${c.id}")
            Log.i("AULABANCO", "Delete livro id = ${c.id}")
            banco.delete(LivroContrato.LivroEntry.TABLE_NAME, selection, whereArgs)

        } finally {
            banco.close()
        }
    }

    fun findByName(nome: String): Livro {
        var banco: SQLiteDatabase = readableDatabase
        try {

            var selection = "${LivroContrato.LivroEntry.NOME} = ?"
            var whereArgs = arrayOf("${nome}")
            val cursor: Cursor = banco.query(LivroContrato.LivroEntry.TABLE_NAME, null, selection, whereArgs, null, null, null, null)

            cursor.moveToFirst()

            var livro = Livro()
            livro.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            livro.nome = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.NOME))
            livro.autor = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.AUTOR))
            livro.ano = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.ANO))
            livro.nota = cursor.getFloat(cursor.getColumnIndex(LivroContrato.LivroEntry.NOTA))

            return livro

        } finally {
            banco.close()
        }
    }

    fun findById(id: Int): Livro {
        var banco: SQLiteDatabase = readableDatabase
        try {

            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${id}")
            val cursor: Cursor = banco.query(LivroContrato.LivroEntry.TABLE_NAME, null, selection, whereArgs, null, null, null, null)

            cursor.moveToFirst()

            var livro = Livro()
            livro.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            livro.nome = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.NOME))
            livro.autor = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.AUTOR))
            livro.ano = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.ANO))
            livro.nota = cursor.getFloat(cursor.getColumnIndex(LivroContrato.LivroEntry.NOTA))


            return livro

        } finally {
            banco.close()
        }
    }

    fun findAll(): ArrayList<Livro> {
        var banco: SQLiteDatabase = readableDatabase
        try {

            val cursor: Cursor = banco.query(LivroContrato.LivroEntry.TABLE_NAME, null, null, null, null, null, null, null)

            var listaLivros = ArrayList<Livro>()

            while (cursor.moveToNext()) {
                var livro = Livro()
                livro.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
                livro.nome = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.NOME))
                livro.autor = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.AUTOR))
                livro.ano = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.ANO))
                livro.nota = cursor.getFloat(cursor.getColumnIndex(LivroContrato.LivroEntry.NOTA))

                listaLivros.add(livro)
            }

            return listaLivros

        } finally {
            banco.close()
        }
    }
}
