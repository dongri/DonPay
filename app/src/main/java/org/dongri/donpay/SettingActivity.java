package org.dongri.donpay;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setTitle("設定");

        ListView listView = (ListView)findViewById(R.id.player_settings);

        ArrayList<PlayerListItem> listItems = new ArrayList<>();

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.app_origami);
        PlayerListItem itemOrigami = new PlayerListItem(bmp, "origamipay", 0, "おりがみぺい");
        listItems.add(itemOrigami);

        Bitmap bmpLine = BitmapFactory.decodeResource(getResources(), R.drawable.app_linepay);
        PlayerListItem itemLine = new PlayerListItem(bmpLine, "linepay", 1, "らいんぺい");
        listItems.add(itemLine);

        Bitmap bmpPayPay = BitmapFactory.decodeResource(getResources(), R.drawable.app_paypay);
        PlayerListItem itemPayPay = new PlayerListItem(bmpPayPay, "paypay", 2, "ぺいぺい");
        listItems.add(itemPayPay);

        Bitmap bmpRakuten = BitmapFactory.decodeResource(getResources(), R.drawable.app_rakuten);
        PlayerListItem itemRakuten = new PlayerListItem(bmpRakuten, "rakutenpay", 3, "らくてんぺい");
        listItems.add(itemRakuten);

        Bitmap bmpMerpay = BitmapFactory.decodeResource(getResources(), R.drawable.app_merpay);
        PlayerListItem itemMerpay = new PlayerListItem(bmpMerpay, "merpay", 4, "めるぺい");
        listItems.add(itemMerpay);

        Bitmap bmpDpay = BitmapFactory.decodeResource(getResources(), R.drawable.app_dpay);
        PlayerListItem itemDpay = new PlayerListItem(bmpDpay, "dpay", 5, "ディばらい");
        listItems.add(itemDpay);

        Bitmap bmpYucho = BitmapFactory.decodeResource(getResources(), R.drawable.app_yucho);
        PlayerListItem itemYucho = new PlayerListItem(bmpYucho, "yuchopay", 6, "ゆうちょぺい");
        listItems.add(itemYucho);

        Bitmap bmpFamily = BitmapFactory.decodeResource(getResources(), R.drawable.app_famipay);
        PlayerListItem itemFamily = new PlayerListItem(bmpFamily, "famipay", 7, "ファミペイ");
        listItems.add(itemFamily);

        Bitmap bmpPayid = BitmapFactory.decodeResource(getResources(), R.drawable.app_payid);
        PlayerListItem itemPayid = new PlayerListItem(bmpPayid, "payidpay", 8, "ペイアイティ");
        listItems.add(itemPayid);

        Bitmap bmpPixiv = BitmapFactory.decodeResource(getResources(), R.drawable.app_pixiv);
        PlayerListItem itemPixiv = new PlayerListItem(bmpPixiv, "pixivpay", 9, "ピクシブ");
        listItems.add(itemPixiv);

        Bitmap bmpKyash = BitmapFactory.decodeResource(getResources(), R.drawable.app_kyash);
        PlayerListItem itemKyash = new PlayerListItem(bmpKyash, "kyashpay", 10, "キャッシュ");
        listItems.add(itemKyash);

        Bitmap bmpGoogle = BitmapFactory.decodeResource(getResources(), R.drawable.app_google);
        PlayerListItem itemGoogle = new PlayerListItem(bmpGoogle, "googlepay", 11, "グーグルぺい");
        listItems.add(itemGoogle);

        Bitmap bmpAlipay = BitmapFactory.decodeResource(getResources(), R.drawable.app_alipay);
        PlayerListItem itemAlipay = new PlayerListItem(bmpAlipay, "alipay", 12, "アリペイ");
        listItems.add(itemAlipay);

        Bitmap bmpWechat = BitmapFactory.decodeResource(getResources(), R.drawable.app_wechat);
        PlayerListItem itemWechat = new PlayerListItem(bmpWechat, "wechatpay", 13, "ウィチャットペイ");
        listItems.add(itemWechat);

        ArrayList<PlayerListItem> apps = new ArrayList<>();

        SharedPreferences preferenceService = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        for(int i=0; i<listItems.size(); i++) {
            PlayerListItem item = listItems.get(i);
            Boolean isView = preferenceService.getBoolean(String.valueOf(i), true);
            item.setVisible(isView);
            Boolean isSound = preferenceService.getBoolean("sound"+String.valueOf(i), false);
            item.setSound(isSound);
            apps.add(item);
        }

        PlayerSettingAdapter adapter = new PlayerSettingAdapter(this, R.layout.playersetting, apps);
        listView.setAdapter(adapter);

    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode==KeyEvent.KEYCODE_BACK){
//
//        }
//        return false;
//    }


}
