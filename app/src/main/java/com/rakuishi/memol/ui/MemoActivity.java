package com.rakuishi.memol.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toolbar;

import com.rakuishi.memol.R;
import com.rakuishi.memol.io.MemoManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;


public class MemoActivity extends Activity {

    private static final String TAG = MemoActivity.class.getSimpleName();
    private MemoAdapter mAdapter;

    @Bind(R.id.memo_listview) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        ButterKnife.bind(this);

        setActionBar((Toolbar) findViewById(R.id.toolbar));
        getActionBar().setTitle(R.string.app_name);

        mAdapter = new MemoAdapter(this, MemoManager.with(this).findAll(), true);
        mListView.setAdapter(mAdapter);
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

    @OnItemClick(R.id.memo_listview)
    void onItemClicked(int position) {
        startActivity(MemoEditActivity.create(this, mAdapter.getItem(position).getId()));
    }
}
