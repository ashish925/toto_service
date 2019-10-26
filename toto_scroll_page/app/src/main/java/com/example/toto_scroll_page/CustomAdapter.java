package com.example.toto_scroll_page;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Button;
import java.util.ArrayList;
import android.os.Bundle;

import static androidx.core.content.ContextCompat.startActivity;

/**
 * Created by Parsania Hardik on 11-May-17.
 */
public class CustomAdapter extends BaseAdapter {

    private Context context;
    private Bundle bundle;
    private ArrayList<ContactModel> contactModelArrayList;

    public CustomAdapter(Context context, Bundle bundle, ArrayList<ContactModel> contactModelArrayList) {

        this.context = context;
        this.bundle = bundle;
        this.contactModelArrayList = contactModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return contactModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item, null, true);

            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvnumber = (TextView) convertView.findViewById(R.id.number);
            holder.tvbutton = (Button) convertView.findViewById(R.id.button);
            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvname.setText(contactModelArrayList.get(position).getName());
        holder.tvnumber.setText(contactModelArrayList.get(position).getNumber());
        holder.tvbutton.setText(contactModelArrayList.get(position).getNumber());
        final String phonenumber = contactModelArrayList.get(position).getNumber();

        holder.tvbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
//                    txtPhn = contactModelArrayList.get(position).getNumber()
                    callIntent.setData(Uri.parse("tel:"+phonenumber));
//                    callIntent.setData(Uri.parse("tel:9555214040"));
                    startActivity(context,callIntent, bundle);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Calling a Phone Number", "Call failed", activityException);
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvname, tvnumber;
        protected Button tvbutton;

    }
}