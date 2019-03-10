package org.dongri.donpay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void setApps() {
        ListView listView = (ListView)findViewById(R.id.player_listview);
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
            Boolean isView = preferenceService.getBoolean(String.valueOf(i), true);
            if (isView) {
                apps.add(listItems.get(i));
            }
        }
        PlayerListAdapter adapter = new PlayerListAdapter(this, R.layout.playerlist, apps);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingButton:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    public void onStart(){
        super.onStart();
        setApps();
    }
}
