package com.laioffer.tinnews.repository;

import android.os.Handler;
import android.os.Looper;

public abstract class MyAsync<Params, Progress, Result>{
    private Handler handler = new Handler(Looper.getMainLooper());
    protected void onPreExecute(){
    }
    protected void onProgressUpdate(Progress progress){

    }
    public void publishProgress(Progress progress){
        handler.post(new Runnable() {
            @Override
            public void run() {
                onProgressUpdate(progress);
            }
        });

    }
    protected abstract Result doInBackground(Params... param);
    protected abstract void onPostExecute(Result result);
    protected void execute(Params params){
        onPreExecute();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Result result = doInBackground(params);
                // need to handle in the main thread
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onPostExecute(result);
                    }
                });
            }
        }).start();
    }
    private static class FavoriteAsyncTast extends MyAsync{

        @Override
        protected Object doInBackground(Object[] param) {
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {

        }
    }

}
