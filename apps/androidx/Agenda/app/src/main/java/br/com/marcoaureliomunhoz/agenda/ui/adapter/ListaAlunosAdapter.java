package br.com.marcoaureliomunhoz.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.marcoaureliomunhoz.agenda.R;
import br.com.marcoaureliomunhoz.agenda.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunosAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        update(alunos);
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        Aluno aluno = alunos.get(position);
        return aluno != null ? aluno.getId() : 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View viewItem = criarViewItem(viewGroup);
        Aluno aluno = alunos.get(position);
        setViewItemValues(viewItem, aluno);
        return viewItem;
    }

    private void setViewItemValues(View viewItem, Aluno aluno) {
        TextView textNome = viewItem.findViewById(R.id.item_aluno_nome);
        textNome.setText(aluno.getNome());

        TextView textTelefone = viewItem.findViewById(R.id.item_aluno_telefone);
        textTelefone.setText(aluno.getTelefone());
    }

    private View criarViewItem(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, viewGroup, false);
    }

    public void update(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
