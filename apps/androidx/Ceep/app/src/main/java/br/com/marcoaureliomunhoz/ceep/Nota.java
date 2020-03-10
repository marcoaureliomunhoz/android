package br.com.marcoaureliomunhoz.ceep;

import java.io.Serializable;

class Nota implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private int id;
    private String titulo;
    private String descricao;

    public Nota(String titulo, String descricao) {
        this.id = 0;
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
