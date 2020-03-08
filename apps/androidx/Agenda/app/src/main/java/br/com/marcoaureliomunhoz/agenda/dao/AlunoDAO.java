package br.com.marcoaureliomunhoz.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.marcoaureliomunhoz.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private final static List<String> errosDominio = new ArrayList<>();
    private static int proximoAlunoId = 1;

    public List<Aluno> clonar() {
        return new ArrayList<>(alunos);
    }

    public List<String> salvar(Aluno aluno) {
        validarDominio(aluno);

        if (errosDominio.isEmpty()) persistir(aluno);

        return errosDominio;
    }

    private void validarDominio(Aluno aluno) {
        errosDominio.clear();

        if (aluno.getNome().isEmpty()) {
            errosDominio.add(Constantes.INFORME_O_NOME);
        }
    }

    private void persistir(Aluno aluno) {
        if (aluno.getId() > 0)
            editar(aluno);
        else
            inserir(aluno);
    }

    private void inserir(Aluno aluno) {
        aluno.setId(proximoAlunoId);
        alunos.add(aluno);
        proximoAlunoId++;
    }

    private void editar(Aluno aluno) {
        int posicao = alunos.indexOf(aluno);
        if (posicao >= 0) alunos.set(posicao, aluno);
    }

    public void remover(Aluno aluno) {
        int posicao = alunos.indexOf(aluno);
        if (posicao >= 0) alunos.remove(posicao);
    }
}
