package itujoker.calculator;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class History extends AppCompatActivity {
    private ListView list;
    public ArrayList<History_Item> historyArr = new ArrayList();
    private MyAdapter adapter;
    private static final int size = 20;

    JSONArray historyScreen, historyResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Themes.onActivityCreateSetTheme(this);
        setContentView(itujoker.calculator.R.layout.history_layout);

        getArray(this);

        list = (ListView) findViewById(itujoker.calculator.R.id.history_list);
        if (historyArr != null) {
            adapter = new MyAdapter(this, historyArr, getResources());
            list.setAdapter(adapter);
        }
    }

    public void getArray(Activity activity) {
        if(MainActivity.getDefaults("historyResult",this)!=null && MainActivity.getDefaults("historyScreen",this)!=null) {
            try {

                historyResult = new JSONArray(MainActivity.getDefaults("historyResult", activity));
                historyScreen = new JSONArray(MainActivity.getDefaults("historyScreen", activity));

                for (int i = 0; i < historyResult.length(); i++) {
                    History_Item sched = new History_Item();
                    sched.setChecked(false);
                    sched.setResult(historyResult.getString(i));
                    sched.setScreen(historyScreen.getString(i));
                    historyArr.add(sched);
                }

                Collections.reverse(historyArr);

                while (historyArr.size() > size) {
                    historyArr.remove(historyArr.size() - 1);
                }

            } catch (JSONException e) {
            }
        }

    }

    ////////////////
    public void checkboxTrue(int position) {
        historyArr.get(position).setChecked(true);
    }

    public void checkboxFalse(int position) {
        historyArr.get(position).setChecked(false);
    }

    ////////////////
    public void deleteHistory(View v) {
        Stack<Integer> deleted = new Stack();
        for (int i = 0; i < historyArr.size(); i++) {
            if (historyArr.get(i).isChecked())
                deleted.push(i);
        }
        while (!deleted.isEmpty()) {
            int a = deleted.pop();
            historyArr.remove(a);
        }
        historyResult = new JSONArray();
        historyScreen = new JSONArray();
        for (int i = historyArr.size() - 1; i >= 0; i--) {
            historyResult.put(historyArr.get(i).getResult());
            historyScreen.put(historyArr.get(i).getScreen());
        }
        MainActivity.setDefaults("historyResult", historyResult.toString(), this);
        MainActivity.setDefaults("historyScreen", historyScreen.toString(), this);

        adapter = new MyAdapter(this, historyArr, getResources());
        list.setAdapter(adapter);


    }

    public void returnBack(View v) {
        /*Intent i = new Intent(this, MainActivity.class);
        startActivity(i);*/
        new GoMain(History.this).execute();
    }

    private class GoMain extends AsyncTask<Void,Void,Void> {
        private Context context;

        public GoMain(Context context){
            this.context=context;
        }
        @Override
        protected void onPreExecute() {
            // write show progress Dialog code here
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // write service code here
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
    }

    ////////////
    public void listClick(int mPosition){
        History_Item temp = historyArr.get(mPosition);
        MainActivity.setDefaults("readHistoryResult",temp.getResult(),this);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
