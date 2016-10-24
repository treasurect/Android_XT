package com.treasure_ct.android_xt.studyfragment.simplecontrols.autocomplete;

/**
 * Created by treasure on 2016.08.28.
 */
public class AutoEntry {
    private String text;
    private String pinyin;

    public AutoEntry(String text, String pinyin) {
        this.text = text;
        this.pinyin = pinyin;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public String toString() {
        return text+"("+pinyin+ ")";
    }
}
