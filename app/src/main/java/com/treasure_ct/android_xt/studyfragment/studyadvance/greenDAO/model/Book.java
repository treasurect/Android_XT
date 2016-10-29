package com.treasure_ct.android_xt.studyfragment.studyadvance.greenDAO.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by treasure on 2016.10.29.
 */

public class Book {
    private long id;
    private String title;
    private String author;
    @Generated(hash = 1131735613)
    public Book(long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
    @Generated(hash = 1839243756)
    public Book() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
}
