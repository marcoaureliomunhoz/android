package br.munhoz.camerapp;

/**
 * Created by Marco Aurélio on 03/01/2017.
 */

import android.view.View;

public interface IRecyclerViewOnClickListener {

    public void onClick(View view, int position);
    public void onLongClick(View view, int position);
    public void onMenuAlterarClick(View view, int position);
    public void onMenuExcluirClick(View view, int position);

}
