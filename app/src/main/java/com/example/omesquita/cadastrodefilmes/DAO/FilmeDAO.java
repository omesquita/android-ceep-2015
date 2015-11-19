package com.example.omesquita.cadastrodefilmes.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.omesquita.cadastrodefilmes.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class FilmeDAO extends SQLiteOpenHelper{

    private static final String DATABASE = "filmes.db";
    private static final int VERSAO = 1;
    private static final String TABELA = "filmes";

    public FilmeDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABELA + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "titulo TEXT, " +
                "estudio TEXT, " +
                "genero INTEGER, " +
                "classificacao TEXT, " +
                "avaliacao FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private ContentValues preencherCtv(Filme filme){
        ContentValues valores = new ContentValues();
        valores.put("titulo", filme.getTitulo());
        valores.put("estudio", filme.getEstudio());
        valores.put("genero", filme.getGenero());
        valores.put("classificacao", filme.getClassficacao());
        valores.put("avaliacao", filme.getAvaliacao());
        return valores;
    }

    public void inserir(Filme filme){
        ContentValues valores = preencherCtv(filme);
        getWritableDatabase().insert(TABELA, "id", valores);
    }

    public void alterar(Filme filme){
        String whereClause = "id = ?";
        String whereArgs[] = {String.valueOf(filme.getId())};

        ContentValues valores = preencherCtv(filme);
        getWritableDatabase().update(TABELA, valores, whereClause, whereArgs);
    }

    public void excluir(Filme filme){
        String whereClause = "id = ?";
        String whereArgs[] = {String.valueOf(filme.getId())};

        getWritableDatabase().delete(TABELA, whereClause, whereArgs);
    }

    public List<Filme> getFilmes(){
        Cursor cursor;
        cursor = getReadableDatabase().query(TABELA, null, null, null, null, null, "titulo");

        ArrayList<Filme> filmes = new ArrayList<>();

        while(cursor.moveToNext()){
            Filme filme = new Filme();
            filme.setId(cursor.getInt(cursor.getColumnIndex("id")));
            filme.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            filme.setEstudio(cursor.getString(cursor.getColumnIndex("estudio")));
            filme.setGenero(cursor.getInt(cursor.getColumnIndex("genero")));
            filme.setClassficacao(cursor.getString(cursor.getColumnIndex("classificacao")));
            filme.setAvaliacao(cursor.getFloat(cursor.getColumnIndex("avaliacao")));
            filmes.add(filme);
        }

        return filmes;
    }
}
