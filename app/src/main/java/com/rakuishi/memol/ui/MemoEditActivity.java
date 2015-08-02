package com.rakuishi.memol.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.rakuishi.memol.R;

/**
 * Created by rakuishi on 15/08/02.
 */
public class MemoEditActivity extends Activity {

    private static final String TAG = MemoEditActivity.class.getSimpleName();

    public static Intent create(Context context) {
        return new Intent(context, MemoEditActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_edit);

        setActionBar((Toolbar) findViewById(R.id.toolbar));
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memo_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_done:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
