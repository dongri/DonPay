package org.dongri.donpay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import java.util.List;

public class PlayerSettingAdapter extends ArrayAdapter<PlayerListItem> {

    private int mResource;
    private List<PlayerListItem> mItems;
    private LayoutInflater mInflater;

    /**
     * コンストラクタ
     * @param context コンテキスト
     * @param resource リソースID
     * @param items リストビューの要素
     */
    public PlayerSettingAdapter(Context context, int resource, List<PlayerListItem> items) {
        super(context, resource, items);

        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        } else {
            view = mInflater.inflate(mResource, null);
        }

        final PlayerListItem item = mItems.get(position);

        ImageView thumbnail = (ImageView)view.findViewById(R.id.appicon);
        thumbnail.setImageBitmap(item.getThumbnail());

        final SharedPreferences preferenceService = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext());

        Switch showSwitch = (Switch)view.findViewById(R.id.showswitch);
        showSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferenceService.edit().putBoolean(String.valueOf(position), isChecked).commit();
            }
        });
        showSwitch.setChecked(item.getVisible());

        Switch soundSwitch = (Switch)view.findViewById(R.id.soundswitch);
        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferenceService.edit().putBoolean("sound"+String.valueOf(position), isChecked).commit();
            }
        });
        soundSwitch.setChecked(item.getSound());

        return view;
    }
}
