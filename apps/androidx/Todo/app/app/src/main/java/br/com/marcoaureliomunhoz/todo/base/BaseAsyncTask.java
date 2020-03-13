package br.com.marcoaureliomunhoz.todo.base;

import android.os.AsyncTask;

public class BaseAsyncTask<T> extends AsyncTask<Void, Void, T> {

    public BaseAsyncTask(OnExecuteAsyncTask<T> onExecuteAsyncTask, OnTerminateAsyncTask<T> onTerminateAsyncTask) {
        this.onExecuteAsyncTask = onExecuteAsyncTask;
        this.onTerminateAsyncTask = onTerminateAsyncTask;
    }

    private final OnExecuteAsyncTask<T> onExecuteAsyncTask;
    private final OnTerminateAsyncTask<T> onTerminateAsyncTask;

    @Override
    protected T doInBackground(Void... voids) {
        return onExecuteAsyncTask.onExecute();
    }

    @Override
    protected void onPostExecute(T result) {
        onTerminateAsyncTask.OnTerminate(result);
    }
}
