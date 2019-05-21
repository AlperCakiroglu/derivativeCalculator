package itujoker.calculator;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter implements View.OnClickListener {
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    History_Item tempValues = null;

    public MyAdapter(Activity a, ArrayList d, Resources resLocal) {
        activity = a;
        data = d;
        res = resLocal;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    @Override
    public History_Item getItem(int position) {
        return (History_Item) data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView adapter_screen;
        public TextView adapter_result;
        public CheckBox adapter_checkbox;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;

        if (convertView == null) {
            vi = inflater.inflate(itujoker.calculator.R.layout.history_item_layout, null);
            holder = new ViewHolder();
            holder.adapter_screen = (TextView) vi.findViewById(itujoker.calculator.R.id.history_screen);
            holder.adapter_result = (TextView) vi.findViewById(itujoker.calculator.R.id.history_result);
            holder.adapter_checkbox = (CheckBox) vi.findViewById(itujoker.calculator.R.id.history_checkBox);
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();
        if (data.size() <= 0) {
            holder.adapter_result.setText("No Data");
        } else {
            tempValues = null;
            tempValues = (History_Item) data.get(position);

            holder.adapter_screen.setText(tempValues.getScreen());
            holder.adapter_result.setText(tempValues.getResult());
            if (tempValues.isChecked()) {
                holder.adapter_checkbox.setChecked(true);
            } else {
                holder.adapter_checkbox.setChecked(false);
            }
            holder.adapter_checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    History sct = (History) activity;
                    if (holder.adapter_checkbox.isChecked()) {
                        //tempValues.setChecked(true);
                        sct.checkboxTrue(position);
                    } else {
                        //tempValues.setChecked(false);
                        sct.checkboxFalse(position);
                    }

                }
            });
            vi.setOnClickListener(new OnItemClickListener(position));
        }

        return vi;
    }


    @Override
    public void onClick(View v) {

    }

    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View v) {
            History sct = (History) activity;
            sct.listClick(mPosition);
        }
    }
}













































