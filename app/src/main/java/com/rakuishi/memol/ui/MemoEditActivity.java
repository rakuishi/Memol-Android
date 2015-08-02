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
import com.rakuishi.memol.io.Memo;
import com.rakuishi.memol.io.MemoManager;
import com.rakuishi.memol.util.AbstractTextWatcher;
import com.rakuishi.memol.util.BundleUtils;
import com.rakuishi.memol.util.MenuItemUtils;

import static com.rakuishi.memol.Config.EXTRA_ID;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rakuishi on 15/08/02.
 */
public class MemoEditActivity extends Activity {

    private static final String TAG = MemoEditActivity.class.getSimpleName();
    private MenuItem mDoneMenuItem;
    private Memo mMemo;

    @Bind(R.id.memo_edit_edittext) EditText mEditText;

    public static Intent create(Context context) {
        return new Intent(context, MemoEditActivity.class);
    }

    public static Intent create(Context context, long id) {
        Intent intent = new Intent(context, MemoEditActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_edit);
        ButterKnife.bind(this);

        long id = BundleUtils.getLong(getIntent().getExtras(), EXTRA_ID);
        mMemo = MemoManager.with(this).find(id);

        setActionBar((Toolbar) findViewById(R.id.toolbar));
        getActionBar().setTitle(mMemo == null ? R.string.title_memo_create : R.string.title_memo_update);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        updateView();
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
                if (mMemo == null) {
                    MemoManager.with(this).insert(mEditText.getText().toString());
                } else {
                    MemoManager.with(this).update(mMemo, mEditText.getText().toString());
                }
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateView() {
        if (mMemo != null) {
            mEditText.setText(mMemo.getContent());
        }

        mEditText.addTextChangedListener(new AbstractTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateMenuItem();
            }
        });
    }

    private void updateMenuItem() {
        boolean enable = mEditText != null && !TextUtils.isEmpty(mEditText.getText().toString());
        MenuItemUtils.setEnable(mDoneMenuItem, enable);
    }
}
