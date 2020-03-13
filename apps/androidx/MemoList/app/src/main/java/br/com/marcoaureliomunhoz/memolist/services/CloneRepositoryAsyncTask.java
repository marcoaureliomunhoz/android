package br.com.marcoaureliomunhoz.memolist.services;

import android.os.AsyncTask;

import java.util.List;

import br.com.marcoaureliomunhoz.memolist.models.Model;
import br.com.marcoaureliomunhoz.memolist.repositories.RepositoryTemplate;

public class CloneRepositoryAsyncTask<T extends Model> extends AsyncTask<Void, Void, List<T>> {

    private RepositoryTemplate<T> repository;
    private OnTerminateRepositoryAsyncTask<List<T>> onTerminateRepositoryAsyncTask;

    public CloneRepositoryAsyncTask(RepositoryTemplate<T> repository, OnTerminateRepositoryAsyncTask<List<T>> onTerminateRepositoryAsyncTask) {
        this.repository = repository;
        this.onTerminateRepositoryAsyncTask = onTerminateRepositoryAsyncTask;
    }

    @Override
    protected List<T> doInBackground(Void... voids) {
        return repository.clone();
    }

    @Override
    protected void onPostExecute(List<T> result) {
        onTerminateRepositoryAsyncTask.OnTerminate(result);
    }
}
