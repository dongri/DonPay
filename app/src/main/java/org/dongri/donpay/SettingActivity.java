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

        setTitle("表示設定");

        ListView listView = (ListView)findViewById(R.id.player_settings);

        ArrayList<PlayerListItem> listItems = new ArrayList<>();

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.app_origami);
        PlayerListItem itemOrigami = new PlayerListItem(bmp, "origami");
        listItems.add(itemOrigami);

        Bitmap bmpLine = BitmapFactory.decodeResource(getResources(), R.drawable.app_line);
        PlayerListItem itemLine = new PlayerListItem(bmpLine, "line");
        listItems.add(itemLine);

        Bitmap bmpPayPay = BitmapFactory.decodeResource(getResources(), R.drawable.app_paypay);
        PlayerListItem itemPayPay = new PlayerListItem(bmpPayPay, "paypay");
        listItems.add(itemPayPay);

        Bitmap bmpRakuten = BitmapFactory.decodeResource(getResources(), R.drawable.app_rakuten);
        PlayerListItem itemRakuten = new PlayerListItem(bmpRakuten, "rakuten");
        listItems.add(itemRakuten);

        Bitmap bmpPayid = BitmapFactory.decodeResource(getResources(), R.drawable.app_payid);
        PlayerListItem itemPayid = new PlayerListItem(bmpPayid, "payid");
        listItems.add(itemPayid);

        Bitmap bmpDpay = BitmapFactory.decodeResource(getResources(), R.drawable.app_dpay);
        PlayerListItem itemDpay = new PlayerListItem(bmpDpay, "dpay");
        listItems.add(itemDpay);

        Bitmap bmpPixiv = BitmapFactory.decodeResource(getResources(), R.drawable.app_pixiv);
        PlayerListItem itemPixiv = new PlayerListItem(bmpPixiv, "pixiv");
        listItems.add(itemPixiv);

        Bitmap bmpAlipay = BitmapFactory.decodeResource(getResources(), R.drawable.app_alipay);
        PlayerListItem itemAlipay = new PlayerListItem(bmpAlipay, "alipay");
        listItems.add(itemAlipay);

        ArrayList<PlayerListItem> apps = new ArrayList<>();

        SharedPreferences preferenceService = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        for(int i=0; i<listItems.size(); i++) {
            PlayerListItem item = listItems.get(i);
            Boolean isView = preferenceService.getBoolean(String.valueOf(i), true);
            item.setVisible(isView);
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
