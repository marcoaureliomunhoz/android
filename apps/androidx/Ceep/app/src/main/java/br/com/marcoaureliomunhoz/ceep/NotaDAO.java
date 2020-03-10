package br.com.marcoaureliomunhoz.ceep;

import java.util.ArrayList;
import java.util.List;

public class NotaDAO {

    private static final List<Nota> notas = new ArrayList<>();
    private static int proximoId = 1;

    public List<Nota> clonar() {
        return new ArrayList<>(notas);
    }

    public void salvar(Nota nota) {
        if (nota.getId() > 0)
            editar(nota);
        else
            inserir(nota);
    }

    private void inserir(Nota nota) {
        nota.setId(proximoId);
        notas.add(nota);
        proximoId++;
    }

    private void editar(Nota nota) {
        int posicao = notas.indexOf(nota);
        if (posicao >= 0) notas.set(posicao, nota);
    }

    public void remover(Nota nota) {
        int posicao = notas.indexOf(nota);
        if (posicao >= 0) notas.remove(posicao);
    }
}
