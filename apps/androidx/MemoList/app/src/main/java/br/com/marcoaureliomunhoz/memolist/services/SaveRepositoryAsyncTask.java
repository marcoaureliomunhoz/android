package br.com.marcoaureliomunhoz.memolist.services;

import android.os.AsyncTask;

import br.com.marcoaureliomunhoz.memolist.models.Model;
import br.com.marcoaureliomunhoz.memolist.repositories.RepositoryTemplate;

public class SaveRepositoryAsyncTask<T extends Model> extends AsyncTask<T, Void, T> {

    private RepositoryTemplate<T> repository;
    private OnTerminateRepositoryAsyncTask<T> onTerminateRepositoryAsyncTask;

    public SaveRepositoryAsyncTask(RepositoryTemplate<T> repository, OnTerminateRepositoryAsyncTask<T> onTerminateRepositoryAsyncTask) {
        this.repository = repository;
        this.onTerminateRepositoryAsyncTask = onTerminateRepositoryAsyncTask;
    }

    @Override
    protected T doInBackground(T... models) {
        T model = models[0];
        if (model == null) return null;
        if (model.isValid()) repository.save(model);
        return model;
    }

    @Override
    protected void onPostExecute(T result) {
        onTerminateRepositoryAsyncTask.OnTerminate(result);
    }
}
