package org.dongri.donpay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PlayerListAdapter extends ArrayAdapter<PlayerListItem> {

    private int mResource;
    private List<PlayerListItem> mItems;
    private LayoutInflater mInflater;

    /**
     * コンストラクタ
     * @param context コンテキスト
     * @param resource リソースID
     * @param items リストビューの要素
     */
    public PlayerListAdapter(Context context, int resource, List<PlayerListItem> items) {
        super(context, resource, items);

        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        } else {
            view = mInflater.inflate(mResource, null);
        }

        final MainActivity activity = (MainActivity)getContext();

        final PlayerListItem item = mItems.get(position);

        ImageView appicon = (ImageView)view.findViewById(R.id.appicon);
        appicon.setImageBitmap(item.getThumbnail());

        appicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mIndex = item.getIndex();
                SharedPreferences preferenceService = PreferenceManager.getDefaultSharedPreferences(getContext());
                Boolean isSound = preferenceService.getBoolean("sound" + String.valueOf(mIndex), false);
                if (isSound) {
                    activity.playSound(item.getName());
                }
                postLaunch(item.getTitle());
                switch (item.getTitle()) {
                    case "origamipay":
                        try {
                            final Intent intent = new Intent(Intent.ACTION_MAIN); //act
                            intent.setAction("android.intent.category.LAUNCHER"); // cat
                            intent.setClassName("co.origami.android",
                                    "co.origami.android.LaunchActivity");
                            if (isSound) {
                                // 3秒後に処理を実行する
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        getContext().startActivity(intent);
                                    }
                                }, 2000);
                            } else {
                                getContext().startActivity(intent);
                            }
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=co.origami.android");
                        }
                        break;
                    case "linepay":
                        try {
                            Intent intent = new Intent(Intent.ACTION_MAIN); //act
                            intent.setAction("android.intent.category.LAUNCHER"); // cat
                            intent.setClassName("com.linepaycorp.talaria",
                                    "com.linepaycorp.talaria.biz.main.MainActivity");
                            getContext().startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=com.linepaycorp.talaria");
                        }
                        break;
                    case "paypay":
                        try {
                            Intent intent = new Intent(Intent.ACTION_MAIN); //act
                            intent.setAction("android.intent.category.LAUNCHER"); // cat
                            intent.setClassName("jp.ne.paypay.android.app",
                                    "jp.ne.paypay.android.app.MainActivity");
                            getContext().startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=jp.ne.paypay.android.app");
                        }
                        break;
                    case "rakutenpay":
                        try {
                            Intent intent = new Intent(Intent.ACTION_MAIN); //act
                            intent.setAction("android.intent.category.LAUNCHER"); // cat
                            intent.setClassName("jp.co.rakuten.pay",
                                    "jp.co.rakuten.wallet.activities.StartActivity");
                            getContext().startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=jp.co.rakuten.pay");
                        }
                        break;
                    case "merpay":
                        try {
                            Intent intent = new Intent(Intent.ACTION_MAIN); //act
                            intent.setAction("android.intent.category.LAUNCHER"); // cat
                            intent.setClassName("com.kouzoh.mercari",
                                    "com.kouzoh.mercari.activity.SplashActivity");
                            getContext().startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=com.kouzoh.mercari");
                        }
                        break;
                    case "dpay":
                        try {
                            Intent intent = new Intent(Intent.ACTION_MAIN); //act
                            intent.setAction("android.intent.category.LAUNCHER"); // cat
                            intent.setClassName("com.nttdocomo.keitai.payment",
                                    "com.nttdocomo.keitai.payment.activity.DPYSplashActivity");
                            getContext().startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=com.nttdocomo.keitai.payment");
                        }
                        break;
                    case "yuchopay":
                        try {
                            Intent intent = new Intent(Intent.ACTION_MAIN); //act
                            intent.setAction("android.intent.category.LAUNCHER"); // cat
                            intent.setClassName("jp.japanpost.jp_bank.YuchoPayapp",
                                    "jp.japanpost.jp_bank.YuchoPayapp.application.RootActivity");
                            getContext().startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=jp.japanpost.jp_bank.YuchoPayapp");
                        }
                        break;
                    case "famipay":
                        try {
                            Intent intent = new Intent(Intent.ACTION_MAIN); //act
                            intent.setAction("android.intent.category.LAUNCHER"); // cat
                            intent.setClassName("jp.co.family.familymart_app",
                                    "jp.co.family.familymart.presentation.splash.SplashActivity");
                            getContext().startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=jp.co.family.familymart_app");
                        }
                        break;
                    case "payidpay":
                        Intent intentPayid = new Intent(Intent.ACTION_MAIN); //act
                        intentPayid.setAction("android.intent.category.LAUNCHER"); // cat
                        intentPayid.setClassName("pay.jp.payid",
                                "pay.jp.payid.views.portal.SplashActivity");
                        try {
                            getContext().startActivity(intentPayid);
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=pay.jp.payid");
                        }
                        break;
                    case "pixivpay":
                        Intent intentPixiv = new Intent(Intent.ACTION_MAIN); //act
                        intentPixiv.setAction("android.intent.category.LAUNCHER"); // cat
                        intentPixiv.setClassName("jp.pxv.pay",
                                "jp.pxv.serval.view.activity.RoutingActivity");
                        try {
                            getContext().startActivity(intentPixiv);
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=jp.pxv.pay");
                        }
                        break;
                    case "kyashpay":
                        try {
                            Intent intent = new Intent(Intent.ACTION_MAIN); //act
                            intent.setAction("android.intent.category.LAUNCHER"); // cat
                            intent.setClassName("co.kyash",
                                    "co.kyash.ui.splash.SplashActivity");
                            getContext().startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=co.kyash");
                        }
                        break;
                    case "googlepay":
                        try {
                            Intent intent = new Intent(Intent.ACTION_MAIN); //act
                            intent.setAction("android.intent.category.LAUNCHER"); // cat
                            intent.setClassName("com.google.android.apps.walletnfcrel",
                                    "com.google.commerce.tapandpay.android.cardlist.CardListActivity");
                            getContext().startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=com.google.android.apps.walletnfcrel");
                        }
                        break;
                    case "alipay":
                        try {
                            getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("alipays://platformapi/startapp")));
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=com.eg.android.AlipayGphone");
                        }
                        break;
                    case "wechatpay":
                        try {
                            Intent intent = new Intent(Intent.ACTION_MAIN); //act
                            intent.setAction("android.intent.category.LAUNCHER"); // cat
                            intent.setClassName("com.tencent.mm",
                                    "com.tencent.mm.ui.LauncherUI");
                            getContext().startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=com.tencent.mm");
                        }
                        break;
                }
            }
        });

        //LinearLayout appicon = (LinearLayout)view.findViewById(R.id.appicon);

        TextView campaignTitle = (TextView)view.findViewById(R.id.campaign_title);
        campaignTitle.setText(item.getCampaignTitle());

        TextView campaignTime = (TextView)view.findViewById(R.id.campaign_time);
        String st = item.getCampaignStartTime();
        String et = item.getCampaignEndTime();
        String t = "";
        if (st != "") {
            t = st + " ~ " + et;
        } else {
            if (campaignTitle.getText().equals(getContext().getString(R.string.no_campaign))) {
                t = "";
            } else if (!campaignTitle.getText().equals("")){
                t = getContext().getString(R.string.indefinite_time);
            }
        }
        campaignTime.setText(t);

        campaignTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra( "campaign_id", item.getCampaignID());
                getContext().startActivity(intent);
            }
        });

        return view;
    }

    private void toPlayStore(String url) {
        Uri uri = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW,uri);
        getContext().startActivity(i);
    }

    private void postLaunch(String appName) {
        String uri = "https://pay.hackerth.com/api/launch?app_name=" + appName + "&platform=android";

        AsyncJsonLoader asyncJsonLoader = new AsyncJsonLoader(new AsyncJsonLoader.AsyncCallback() {
            // 実行前
            public void preExecute() {
            }
            // 実行後
            public void postExecute(JSONObject result) {
                Log.d("Launch", "finished");
            }

            public void progressUpdate(int progress) {
            }

            public void cancel() {
            }
        });

        asyncJsonLoader.execute(uri);

    }
}
