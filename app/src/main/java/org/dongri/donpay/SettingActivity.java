package org.dongri.donpay;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ListView listView = (ListView)findViewById(R.id.player_listview);

        ArrayList<PlayerListItem> listItems = new ArrayList<>();

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.app_origami);
        PlayerListItem item = new PlayerListItem(bmp, "origami");
        listItems.add(item);

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

        PlayerListAdapter adapter = new PlayerListAdapter(this, R.layout.playerlist, listItems);
        listView.setAdapter(adapter);

    }


}
