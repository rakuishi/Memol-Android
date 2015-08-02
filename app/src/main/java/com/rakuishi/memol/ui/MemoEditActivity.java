package com.rakuishi.memol.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toolbar;

import com.rakuishi.memol.R;
import com.rakuishi.memol.io.MemoManager;
import com.rakuishi.memol.util.AbstractTextWatcher;
import com.rakuishi.memol.util.MenuItemUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rakuishi on 15/08/02.
 */
public class MemoEditActivity extends Activity {

    private static final String TAG = MemoEditActivity.class.getSimpleName();
    private MenuItem mDoneMenuItem;

    @Bind(R.id.memo_edit_edittext) EditText mEditText;

    public static Intent create(Context context) {
        return new Intent(context, MemoEditActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_edit);
        ButterKnife.bind(this);

        setActionBar((Toolbar) findViewById(R.id.toolbar));
        getActionBar().setDisplayHomeAsUpEnabled(true);

        mEditText.addTextChangedListener(new AbstractTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateMenuItem();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memo_edit, menu);
        mDoneMenuItem = menu.findItem(R.id.action_done);
        updateMenuItem();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_done:
                MemoManager.with(this).insert(mEditText.getText().toString());
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateMenuItem() {
        boolean enable = mEditText != null && !TextUtils.isEmpty(mEditText.getText().toString());
        MenuItemUtils.setEnable(mDoneMenuItem, enable);
    }
}
