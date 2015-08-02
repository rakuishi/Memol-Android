package com.rakuishi.memol.io;

import io.realm.RealmObject;

/**
 * Created by rakuishi on 15/08/02.
 */
public class Memo extends RealmObject {

    private long id;
    private String content;
    private long updateAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }
}
