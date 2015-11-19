package com.example.omesquita.cadastrodefilmes.model;

import android.content.Intent;

import java.io.Serializable;

public class Filme implements Serializable {
    private Integer id;
    private String titulo;
    private String estudio;
    private Integer genero;
    private String classficacao;
    private Float avaliacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Float avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public Integer getGenero() {
        return genero;
    }

    public void setGenero(Integer genero) {
        this.genero = genero;
    }

    public String getClassficacao() {
        return classficacao;
    }

    public void setClassficacao(String classficacao) {
        this.classficacao = classficacao;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
