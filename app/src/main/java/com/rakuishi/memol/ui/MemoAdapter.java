package com.rakuishi.memol.ui;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.rakuishi.memol.R;
import com.rakuishi.memol.io.Memo;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by rakuishi on 15/08/02.
 */
public class MemoAdapter extends RealmBaseAdapter<Memo> implements ListAdapter {

    private LayoutInflater mLayoutInflater;

    public MemoAdapter(Context context, RealmResults<Memo> results, boolean automaticUpdate) {
        super(context, results, automaticUpdate);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_memo, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Memo memo = realmResults.get(position);
        holder.contentTextView.setText(memo.getContent());
        holder.pubdateTextView.setText(String.valueOf(DateFormat.format("yyyy/MM/dd kk:mm", memo.getUpdateAt())));

        return convertView;
    }

    public static class ViewHolder {
        @Bind(R.id.row_memo_content_textview) TextView contentTextView;
        @Bind(R.id.row_memo_pubdate_textview) TextView pubdateTextView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
