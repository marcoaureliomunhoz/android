package br.com.marcoaureliomunhoz.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.marcoaureliomunhoz.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();

    public void salvar(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> clonar() {
        return new ArrayList<>(alunos);
    }
}
