package org.dongri.donpay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

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

        final PlayerListItem item = mItems.get(position);

        ImageView thumbnail = (ImageView)view.findViewById(R.id.appicon);
        thumbnail.setImageBitmap(item.getThumbnail());

        LinearLayout buttonScan = (LinearLayout)view.findViewById(R.id.button_scan);
        if (item.getTitle().equals("dpay")) {
            buttonScan.setVisibility(View.GONE);
        } else {
            buttonScan.setVisibility(View.VISIBLE);
        }
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (item.getTitle()) {
                    case "origami":
                        try {
                            getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("origami://payment")));
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=co.origami.android");
                        }
                        break;
                    case "line":
                        try {
                            getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("line://nv/QRCodeReader")));
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=jp.naver.line.android");
                        }
                        break;
                    case "paypay":
                        try {
                            getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("paypay://wallet/topup")));
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=jp.ne.paypay.android.app");
                        }
                        break;
                    case "rakuten":
                        Intent intent = new Intent(Intent.ACTION_MAIN); //act
                        intent.setAction("android.intent.category.LAUNCHER"); // cat
                        intent.setClassName("jp.co.rakuten.pay",
                                "jp.co.rakuten.wallet.activities.StartActivity");
                        try {
                            getContext().startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=jp.co.rakuten.pay");
                        }
                        break;
                    case "payid":
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
                    case "pixiv":
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
                    case "alipay":
                        try {
                            getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("alipays://platformapi/startapp?appId=10000007&source=nougat_shortcut_scan")));
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=com.eg.android.AlipayGphone");
                        }
                        break;
                }
            }
        });

        LinearLayout buttonQRcode = (LinearLayout)view.findViewById(R.id.button_barcode);
        if (item.getTitle().equals("payid") || item.getTitle().equals("pixiv")) {
            buttonQRcode.setVisibility(View.GONE);
        } else {
            buttonQRcode.setVisibility(View.VISIBLE);
        }
        buttonQRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (item.getTitle()) {
                    case "origami":
                        try {
                            getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("origami://payment_barcode")));
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=co.origami.android");
                        }
                        break;
                    case "line":
                        try {
                            getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("line://pay/generateQR")));
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=jp.naver.line.android");
                        }
                        break;
                    case "paypay":
                        try {
                            getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("paypay://wallet/topup")));
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=jp.ne.paypay.android.app");
                        }
                        break;
                    case "rakuten":
                        Intent intent = new Intent(Intent.ACTION_MAIN); //act
                        intent.setAction("android.intent.category.LAUNCHER"); // cat
                        intent.setClassName("jp.co.rakuten.pay",
                                "jp.co.rakuten.wallet.activities.StartActivity");
                        try {
                            getContext().startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=jp.co.rakuten.pay");
                        }
                        break;
                    case "dpay":
                        Intent intentDpay = new Intent(Intent.ACTION_MAIN); //act
                        intentDpay.setAction("android.intent.category.LAUNCHER"); // cat
                        intentDpay.setClassName("com.nttdocomo.keitai.payment",
                                "com.nttdocomo.keitai.payment.activity.DPYSplashActivity");
                        try {
                            getContext().startActivity(intentDpay);
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=com.nttdocomo.keitai.payment");
                        }
                        break;
                    case "alipay":
                        try {
                            getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("alipays://platformapi/startapp?appId=20000056&source=nougat_shortcut_barcode")));
                        } catch (ActivityNotFoundException e) {
                            toPlayStore("https://play.google.com/store/apps/details?id=com.eg.android.AlipayGphone");
                        }
                        break;
                }
            }
        });

        return view;
    }

    private void toPlayStore(String url) {
        Uri uri = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW,uri);
        getContext().startActivity(i);
    }
}
