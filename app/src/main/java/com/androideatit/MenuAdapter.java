package com.androideatit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdapter extends ArrayAdapter<String>
{
    private final int resId;
    private final Context context;
    private final String[] itemTitles;
    private final int[] itemIcons;

    private static final String LOG_TAG = MenuAdapter.class.getCanonicalName();

    public MenuAdapter(Context context,final int[] images, final String name[])
    {
        super(context, R.layout.menu_item);
        this.resId = R.layout.menu_item;
        this.context = context;
        this.itemTitles =name;
        this.itemIcons = images/*context.getResources().obtainTypedArray(R.array.contact_type_icons)*/;
    }

    @Override
    public String getItem(int position)
    {
        return itemTitles[position];
    }

    @Override
    public int getCount()
    {
        return itemTitles.length;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ContactHolder contactHolder;
        if(convertView==null)
        {
            contactHolder = new ContactHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(resId, parent, false);
            contactHolder. contactTypeIcon = (ImageView) convertView.findViewById(R.id.menu_image);
            contactHolder.contactTypeTitle = (TextView) convertView.findViewById(R.id.menu_name);
            convertView.setTag(contactHolder);
        }
        else
        {
            contactHolder =(ContactHolder) convertView.getTag();
        }
        try
        {
            contactHolder.contactTypeTitle.setText(itemTitles[position]);
            contactHolder.contactTypeIcon.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),itemIcons[position]));
        }
        catch (Throwable throwable)
        {
            Log.e(LOG_TAG,"contactHolder.contactTypeIcon.setImageBitmap failed",throwable);
        }

        return convertView;
    }
    public class ContactHolder
    {
        ImageView contactTypeIcon;
        TextView contactTypeTitle;
    }
}