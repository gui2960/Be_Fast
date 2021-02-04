package com.guilhermeluftlab.befast.models.endereco;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import com.guilhermeluftlab.befast.RegistroCompleto;

public class ZipCodeListener implements TextWatcher {
    private Context context;

    public ZipCodeListener(Context context){
        this.context = context;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        String zipCode = s.toString();
        if(s.length() == 8){
            new EnderecoRequest((RegistroCompleto) context).execute();
        }
    }
}
