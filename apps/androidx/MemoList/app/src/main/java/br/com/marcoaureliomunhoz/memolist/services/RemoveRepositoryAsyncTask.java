package br.com.marcoaureliomunhoz.memolist.services;

import android.os.AsyncTask;

import br.com.marcoaureliomunhoz.memolist.models.Model;
import br.com.marcoaureliomunhoz.memolist.repositories.RepositoryTemplate;

public class RemoveRepositoryAsyncTask<T extends Model> extends AsyncTask<T, Void, Boolean> {

    private RepositoryTemplate<T> repository;
    private OnTerminateRepositoryAsyncTask<Boolean> onTerminateRepositoryAsyncTask;

    public RemoveRepositoryAsyncTask(RepositoryTemplate<T> repository, OnTerminateRepositoryAsyncTask<Boolean> onTerminateRepositoryAsyncTask) {
        this.repository = repository;
        this.onTerminateRepositoryAsyncTask = onTerminateRepositoryAsyncTask;
    }

    @Override
    protected Boolean doInBackground(T... models) {
        if (models.length > 0)
            repository.removeById(models[0].getId());
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        onTerminateRepositoryAsyncTask.OnTerminate(result);
    }
}
