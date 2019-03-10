package org.dongri.donpay;

import android.graphics.Bitmap;

public class PlayerListItem {

    private Bitmap mThumbnail = null;
    private String mTitle = null;
    private Boolean mVisible = true;

    /**
     * 空のコンストラクタ
     */
    public PlayerListItem() {};

    /**
     * コンストラクタ
     * @param thumbnail サムネイル画像
     * @param title タイトル
     */
    public PlayerListItem(Bitmap thumbnail, String title) {
        mThumbnail = thumbnail;
        mTitle = title;
    }

    /**
     * サムネイル画像を設定
     * @param thumbnail サムネイルZ画像
     */
    public void setThumbnail(Bitmap thumbnail) {
        mThumbnail = thumbnail;
    }

    /**
     * タイトルを設定
     * @param title タイトル
     */
    public void setTitle(String title) {
        mTitle = title;
    }

    /**
     * サムネイル画像を取得
     * @return サムネイル画像
     */
    public Bitmap getThumbnail() {
        return mThumbnail;
    }

    /**
     * タイトルを取得
     * @return タイトル
     */
    public String getTitle() {
        return mTitle;
    }

    public Boolean getVisible() {
        return mVisible;
    }

    public void setVisible(Boolean mVisible) {
        this.mVisible = mVisible;
    }

}
