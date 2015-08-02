package com.rakuishi.memol.io;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by rakuishi on 15/08/02.
 */
public class MemoManager {

    private static final String TAG = MemoManager.class.getSimpleName();
    private final Realm mRealm;

    private MemoManager(Context context) {
        mRealm = Realm.getInstance(context);
    }

    public static MemoManager with(Context context) {
        return new MemoManager(context);
    }

    public RealmResults<Memo> findAll() {
        return mRealm.where(Memo.class).findAll();
    }

    public void insert(final String content) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Memo memo = realm.createObject(Memo.class);
                memo.setId(realm.where(Memo.class).maximumInt("id") + 1);
                memo.setContent(content);
                memo.setUpdateAt(System.currentTimeMillis());
            }
        });
    }
}
