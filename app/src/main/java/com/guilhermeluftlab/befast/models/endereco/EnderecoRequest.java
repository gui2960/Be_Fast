package com.guilhermeluftlab.befast.models.endereco;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.guilhermeluftlab.befast.RegistroCompleto;
import com.guilhermeluftlab.befast.models.Endereco;

import java.lang.ref.WeakReference;

public class EnderecoRequest extends AsyncTask<Void, Void, Endereco> {
    private WeakReference<RegistroCompleto> activityWeakReference;

    public EnderecoRequest(RegistroCompleto registroCompleto){
        this.activityWeakReference = new WeakReference<RegistroCompleto>(registroCompleto);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(activityWeakReference.get() != null)
            activityWeakReference.get().lockFields(true);
    }

    @Override
    protected Endereco doInBackground(Void... voids) {
        try {
            String jsonString = JsonRequest.request(activityWeakReference.get().getUriZipCode());
            Gson gson = new Gson();
            return gson.fromJson(jsonString, Endereco.class);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Endereco endereco) {
        super.onPostExecute(endereco);
        if(activityWeakReference.get() != null) {
            activityWeakReference.get().lockFields(false);

            if(endereco != null){
                activityWeakReference.get().setDataViews(endereco);
            }
        }

    }
}
