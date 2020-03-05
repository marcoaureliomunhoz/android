package br.munhoz.camerapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by Marco Aurélio on 04/01/2017.
 */

public class ComandarCameraLoader extends AsyncTaskLoader<byte[]> {

    private byte[] imagem;
    private String urlBaseSnapshot;
    private int i = 0;

    public ComandarCameraLoader(Context context, String urlBase) {
        super(context);
        this.urlBaseSnapshot = urlBase;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (imagem != null) {
            deliverResult(imagem);
        } else {
            forceLoad();
        }
    }

    @Override
    public byte[] loadInBackground() {
        imagem = null;
        String url = this.urlBaseSnapshot + this.i++;
        return HttpClient.doGet(url);
    }

    //executa depois do loadInBackground()
    //chamado antes de enviar o resultado para a UI Thread
    @Override
    public void deliverResult(byte[] data) {
        if (isStarted() && !isReset()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad(); //tenta cancelar a tarefa corrente
    }

    //chamado caso o loader tenha sido cancelado pelo método reset()
    @Override
    protected void onReset() {
        super.onReset();
        //verifica se é necessário liberar os recursos
        imagem = null;
    }

    //chamado se a tarefa foi cancelada antes de ser concluída.
    //aqui devemos limpar/descartar adequadamente os recursos/resultados.
    @Override
    public void onCanceled(byte[] data) {
        super.onCanceled(data);
        imagem = null;
    }

    //chamado se a fonte de dados utilizada pelo loader foi alterada, neste caso a interface deve ser alterada para refletir os novos dados.
    //não é utilizado muito com web services, estude o CursorLoader para entender isso.
    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

}
