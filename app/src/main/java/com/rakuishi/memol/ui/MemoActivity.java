package com.rakuishi.memol.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.rakuishi.memol.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MemoActivity extends Activity {

    private static final String TAG = MemoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        ButterKnife.bind(this);

        setActionBar((Toolbar) findViewById(R.id.toolbar));
        setTitle(R.string.app_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.memo_floating_action_button)
    void onFloatingActionButtonClicked() {
        startActivity(MemoEditActivity.create(this));
    }
}
