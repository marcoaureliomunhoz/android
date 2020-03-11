package br.com.marcoaureliomunhoz.ceep;

import java.util.ArrayList;
import java.util.Collections;
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

    public void remover(int posicao) {
        notas.remove(posicao);
    }

    public void mudarOrdem(int posicaoOrigem, int posicaoDestino) {
        if (posicaoOrigem < posicaoDestino) {
            for (int i = posicaoOrigem; i < posicaoDestino; i++) {
                Collections.swap(notas, i, i + 1);
            }
        } else {
            for (int i = posicaoOrigem; i > posicaoDestino; i--) {
                Collections.swap(notas, i, i - 1);
            }
        }
    }

    public void trocarPosicao(int posicaoOrigem, int posicaoDestino) {
        Collections.swap(notas, posicaoOrigem, posicaoDestino);
    }
}
