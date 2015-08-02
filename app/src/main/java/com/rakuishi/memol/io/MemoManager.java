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

    public Memo find(long id) {
        RealmResults<Memo> realmResults = mRealm.where(Memo.class).equalTo("id", id).findAll();
        return realmResults != null && realmResults.size() > 0 ? realmResults.first() : null;
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

    public void update(final Memo memo, final String content) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Memo realmMemo = realm.copyToRealm(memo);
                realmMemo.setContent(content);
                realmMemo.setUpdateAt(System.currentTimeMillis());
            }
        });
    }

    public void delete(final long id) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Memo> realmResults = mRealm.where(Memo.class).equalTo("id", id).findAll();
                realmResults.clear();
            }
        });
    }
}
