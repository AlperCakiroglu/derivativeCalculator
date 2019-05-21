package itujoker.calculator;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Instruction_Adapter extends BaseAdapter {
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    Instruction_Item tempValues = null;

    public Instruction_Adapter(Activity a, ArrayList d, Resources resLocal) {
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
    public Instruction_Item getItem(int position) {
        return (Instruction_Item) data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public static class ViewHolderins {
        public TextView instruction_head;
        public TextView instruction_text;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolderins holder;

        if (convertView == null) {
            vi = inflater.inflate(itujoker.calculator.R.layout.instruction_item_layout, null);
            holder = new ViewHolderins();
            holder.instruction_head = (TextView) vi.findViewById(itujoker.calculator.R.id.header);
            holder.instruction_text = (TextView) vi.findViewById(itujoker.calculator.R.id.info);

            vi.setTag(holder);
        } else
            holder = (ViewHolderins) vi.getTag();
        if (data.size() <= 0) {
            holder.instruction_head.setText("No Data");
        } else {
            tempValues = null;
            tempValues = (Instruction_Item) data.get(position);

            holder.instruction_head.setText(tempValues.getHeader());
            holder.instruction_text.setText(tempValues.getInfo());

        }

        return vi;
    }
}
