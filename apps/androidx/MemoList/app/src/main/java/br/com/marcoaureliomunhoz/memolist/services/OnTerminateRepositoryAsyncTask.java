package br.com.marcoaureliomunhoz.memolist.services;

public interface OnTerminateRepositoryAsyncTask<TResult> {
    void OnTerminate(TResult result);
}
