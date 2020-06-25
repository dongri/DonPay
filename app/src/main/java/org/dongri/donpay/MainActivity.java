package org.dongri.donpay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    //private AudioManager audioManager;

    private ArrayList<PlayerListItem> listItems = new ArrayList<>();

    private ListView listView;

    @Override
    public void onInit(int status) {
        if (TextToSpeech.SUCCESS == status) {
            Locale locale = Locale.JAPANESE;
            tts.setLanguage(locale);
        } else {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tts = new TextToSpeech(this, this);

        setContentView(R.layout.activity_main);
    }

    public void setApps() {

        listItems.clear();

        listView = (ListView)findViewById(R.id.player_listview);

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

        String uri = "https://pay.hackerth.com/api/campaign";

        AsyncJsonLoader asyncJsonLoader = new AsyncJsonLoader(new AsyncJsonLoader.AsyncCallback() {
            // 実行前
            public void preExecute() {
            }
            // 実行後
            public void postExecute(JSONObject result) {
                if (result == null) {
                    //showLoadError(); // エラーメッセージを表示
                    return;
                }
                try {
                    ArrayList<String> list = new ArrayList<>();
                    JSONArray eventArray = result.getJSONArray("result");
                    System.out.println(eventArray);

                    PlayerListAdapter adapter = (PlayerListAdapter) listView.getAdapter();


                    for (int x = 0; x < adapter.getCount(); x++) {
                        //PlayerListItem item = listItems.get(x);
                        PlayerListItem playerListItem = adapter.getItem(x);
                        playerListItem.setCampaignTitle(getString(R.string.no_campaign));
                        adapter.notifyDataSetChanged();
                        for (int i = 0; i < eventArray.length(); i++) {
                            JSONObject eventObj = eventArray.getJSONObject(i);
                            JSONObject player = eventObj.getJSONObject("player");
                            if (!eventObj.isNull("campaign")) {
                                JSONObject campaign = eventObj.getJSONObject("campaign");
                                String label = player.getString("label");
                                if (playerListItem.getTitle().equals(label)) {
                                    String campaignID = campaign.getString("id");
                                    playerListItem.setCampaignID(campaignID);

                                    String title = campaign.getString("title");
                                    String startTime = campaign.getString("start_time");
                                    String endTime = campaign.getString("end_time");

                                    startTime = startTime.replace("T", " ");
                                    startTime = startTime.replace(":00Z", "");
                                    endTime = endTime.replace("T", " ");
                                    endTime = endTime.replace(":59Z", "");

                                    playerListItem.setCampaignTitle(title);

                                    if (startTime != "null") {
                                        playerListItem.setCampaignStartTime(startTime);
                                    }
                                    if (endTime != "null") {
                                        playerListItem.setCampaignEndTime(endTime);
                                    }
                                }
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    //showLoadError(); // エラーメッセージを表示
                }
            }

            public void progressUpdate(int progress) {
            }

            public void cancel() {
            }
        });

        asyncJsonLoader.execute(uri);

        //audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0);
    }

    public void playSound(String text) {
        tts.speak(text + "でお願いします", TextToSpeech.QUEUE_FLUSH, null);
    }
}
