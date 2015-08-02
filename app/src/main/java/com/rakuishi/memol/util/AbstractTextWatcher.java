package com.rakuishi.memol.util;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by rakuishi on 15/08/02.
 */
public abstract class AbstractTextWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void afterTextChanged(Editable editable) {}
}