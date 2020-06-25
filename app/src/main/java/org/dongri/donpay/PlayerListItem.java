package org.dongri.donpay;

import android.graphics.Bitmap;

public class PlayerListItem {

    private Bitmap mThumbnail = null;
    private String mTitle = null;
    private int mIndex = 0;
    private String mName = null;
    private Boolean mVisible = true;
    private Boolean mSound = false;
    private String mCampaignTitle = null;
    private String mCampaignStartTime = "";
    private String mCampaignEndTime = "";
    private String mCampaignID = null;

    /**
     * 空のコンストラクタ
     */
    public PlayerListItem() {};

    /**
     * コンストラクタ
     * @param thumbnail サムネイル画像
     * @param title タイトル
     */
    public PlayerListItem(Bitmap thumbnail, String title, int index, String name) {
        mThumbnail = thumbnail;
        mTitle = title;
        mIndex = index;
        mName = name;
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

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int mIndex) {
        this.mIndex = mIndex;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public Boolean getVisible() {
        return mVisible;
    }

    public void setVisible(Boolean mVisible) {
        this.mVisible = mVisible;
    }

    public Boolean getSound() {
        return mSound;
    }

    public void setSound(Boolean mSound) {
        this.mSound = mSound;
    }

    public String getCampaignTitle() {
        return mCampaignTitle;
    }

    public void setCampaignTitle(String mCampaignTitle) {
        this.mCampaignTitle = mCampaignTitle;
    }

    public String getCampaignStartTime() {
        return mCampaignStartTime;
    }

    public void setCampaignStartTime(String mCampaignStartTime) {
        this.mCampaignStartTime = mCampaignStartTime;
    }

    public String getCampaignEndTime() {
        return mCampaignEndTime;
    }

    public void setCampaignEndTime(String mCampaignEndTime) {
        this.mCampaignEndTime = mCampaignEndTime;
    }

    public String getCampaignID() {
        return mCampaignID;
    }

    public void setCampaignID(String mCampaignID) {
        this.mCampaignID = mCampaignID;
    }
}
