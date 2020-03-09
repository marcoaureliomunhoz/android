package br.com.marcoaureliomunhoz.agenda;

import android.app.Application;

import br.com.marcoaureliomunhoz.agenda.dao.AlunoDAO;

public class AgendaApplication extends Application {

    public static final AlunoDAO alunoDAO = new AlunoDAO();

}
