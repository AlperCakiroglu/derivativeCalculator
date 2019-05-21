package itujoker.calculator;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;


import org.json.JSONArray;
import org.json.JSONException;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;


public class MainActivity extends AppCompatActivity {

    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;
    private static MediaPlayer buttonVoice;
    boolean isVoice;

    private EditText screen, result;
    private TextView degText;

    public static boolean isDeg;

    private String sendedString;
    private boolean dene = false;///string donusum

    private JSONArray historyArrayScreen, historyArrayResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Themes.onActivityCreateSetTheme(this);
        setContentView(itujoker.calculator.R.layout.activity_main);

        //init_menu(MainActivity.this);
        initializeUI();
        /*takeHistory();
        readHistoryResult();*/
        new MyCustomAsyncTask(this).execute();
    }

    ////
    public class MyCustomAsyncTask extends AsyncTask<Void,Void,Void> {
        private Context context;

        public MyCustomAsyncTask(Context context){
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
            init_menu(MainActivity.this);
            initializeUI();
            takeHistory();
            readHistoryResult();
            /*Intent intent = new Intent(context, home.class);
            context.startActivity(intent);
            ((Activity)context).finish();*/
        }
    }
    public void initializeUI() {
        screen = (EditText) findViewById(itujoker.calculator.R.id.screen);
        result = (EditText) findViewById(itujoker.calculator.R.id.result);
        degText = (TextView) findViewById(itujoker.calculator.R.id.degtext);
        isDeg = true;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM, WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);


    }

    private void takeHistory() {
        if (getDefaults("historyResult", this) != null && getDefaults("historyScreen", this) != null) {
            try {
                historyArrayResult = new JSONArray(getDefaults("historyResult", this));
                historyArrayScreen = new JSONArray(getDefaults("historyScreen", this));
            } catch (JSONException e) {

            }
        } else {
            historyArrayResult = new JSONArray();
            historyArrayScreen = new JSONArray();
        }
    }

    private void readHistoryResult() {
        if (getDefaults("readHistoryResult", this) != null) {
            screen.setText(getDefaults("readHistoryResult", this));
            screen.setSelection(screen.getSelectionStart() + getDefaults("readHistoryResult", this).length());
            setDefaults("readHistoryResult", null, this);

        }
    }

    public class GoFunction extends AsyncTask<Void,Void,Void> {
        private Context context;

        public GoFunction(Context context){
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
            Intent intent = new Intent(context, Functions.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
    }
    public class GoHistory extends AsyncTask<Void,Void,Void> {
        private Context context;

        public GoHistory(Context context){
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
            Intent intent = new Intent(context, History.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
    }
    public class GoInstruction extends AsyncTask<Void,Void,Void> {
        private Context context;

        public GoInstruction(Context context){
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
            Intent intent = new Intent(context, Instructions.class);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
    }
    ////Theme/////////////////////////////////////////////////////////////////
    private void init_menu(final Activity activity) {
        mDrawerLayout = (DrawerLayout) findViewById(itujoker.calculator.R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(itujoker.calculator.R.id.navigation_view);
        checkVoice(activity, mNavigationView.getMenu());
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent i;
                switch ((menuItem.getItemId())) {

                    case itujoker.calculator.R.id.instructions:

                        /*i = new Intent(activity, Instructions.class);
                        startActivity(i);*/
                        new GoInstruction(MainActivity.this).execute();

                        mDrawerLayout.closeDrawers();
                        break;
                    case itujoker.calculator.R.id.history:

                        /*i = new Intent(activity, History.class);
                        startActivity(i);*/
                        new GoHistory(MainActivity.this).execute();
                        mDrawerLayout.closeDrawers();
                        break;
                    case itujoker.calculator.R.id.functions:

                        /*i = new Intent(activity, Functions.class);
                        startActivity(i);*/
                        new GoFunction(MainActivity.this).execute();
                        mDrawerLayout.closeDrawers();
                        break;
                    case itujoker.calculator.R.id.Paper:
                        Themes.changeToTheme(MainActivity.this, Themes.THEME_PAPER);

                        break;
                    case itujoker.calculator.R.id.Space:
                        Themes.changeToTheme(MainActivity.this, Themes.THEME_SPACE);

                        break;
                    case itujoker.calculator.R.id.Water:
                        Themes.changeToTheme(MainActivity.this, Themes.THEME_WATER);
                        break;
                    case itujoker.calculator.R.id.soundoff:
                        if (menuItem.getTitle().equals("Sound On")) {
                            menuItem.setTitle("Sound Off");
                            menuItem.setIcon(itujoker.calculator.R.drawable.ic_volume_off_black_24dp);
                            isVoice = false;
                            setDefaults("sound", "0", activity);
                        } else {
                            menuItem.setTitle("Sound On");
                            menuItem.setIcon(itujoker.calculator.R.drawable.ic_volume_up_black_24dp);
                            isVoice = true;
                            setDefaults("sound", "1", activity);
                        }
                        break;
                }
                return true;
            }
        });
    }

    public static void playbuttonVoice(Context context) {
        closeVoice();
        buttonVoice = MediaPlayer.create(context, itujoker.calculator.R.raw.buttonvoice);
        buttonVoice.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                closeVoice();
            }
        });
        buttonVoice.start();
    }

    private static void closeVoice() {
        if (buttonVoice != null) {
            buttonVoice.release();
            buttonVoice = null;
        }
    }

    public void checkVoice(Activity activity, Menu menu) {
        int voice = 0;

        try {
            voice = Integer.parseInt(getDefaults("sound", activity));
        } catch (Exception e) {
        }
        if (voice == 0) {
            isVoice = false;
            menu.findItem(itujoker.calculator.R.id.soundoff).setIcon(itujoker.calculator.R.drawable.ic_volume_off_black_24dp);
            menu.findItem(itujoker.calculator.R.id.soundoff).setTitle("Sound Off");
        } else {
            isVoice = true;
            menu.findItem(itujoker.calculator.R.id.soundoff).setIcon(itujoker.calculator.R.drawable.ic_volume_up_black_24dp);
            menu.findItem(itujoker.calculator.R.id.soundoff).setTitle("Sound On");
        }
    }

    /////Shared Preferences////////////////////////////////////////////////
    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    ////////////////Button Clicks////////////////////////////////////////////
    public void settingsButton(View view) {
        if (isVoice)
            playbuttonVoice(this);
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.closeDrawers();
        else
            mDrawerLayout.openDrawer(Gravity.LEFT);

    }

    public void inverseFlipper(View view) {
        if (isVoice)
            playbuttonVoice(this);
        ViewFlipper inverseFlipper = (ViewFlipper) findViewById(itujoker.calculator.R.id.inverseflipper);
        inverseFlipper.showNext();
    }

    public void degorrad(View view) {
        if (isVoice)
            playbuttonVoice(this);
        Button b = (Button) view;
        if (b.getText().equals("Deg")) {
            isDeg = false;
            b.setText("Rad");
            degText.setText("Rad");
        } else {
            isDeg = true;
            b.setText("Deg");
            degText.setText("Deg");
        }
    }

    public void printButton(View view) {
        if (isVoice)
            playbuttonVoice(this);
        Button b = (Button) view;
        String mscreenText = screen.getText().toString();
        String mbuttonText = b.getText().toString();
        int cursor = screen.getSelectionStart();


        if (b.getId() == itujoker.calculator.R.id.asinButton) {
            mscreenText = mscreenText.substring(0, cursor) + "asin(" + mscreenText.substring(cursor);
            cursor += 5;
        } else if (b.getId() == itujoker.calculator.R.id.acosButton) {
            mscreenText = mscreenText.substring(0, cursor) + "acos(" + mscreenText.substring(cursor);
            cursor += 5;
        } else if (b.getId() == itujoker.calculator.R.id.atanButton) {
            mscreenText = mscreenText.substring(0, cursor) + "atan(" + mscreenText.substring(cursor);
            cursor += 5;
        } else if (b.getId() == itujoker.calculator.R.id.sinButton || b.getId() == itujoker.calculator.R.id.cosButton || b.getId() == itujoker.calculator.R.id.tanButton || b.getId() == itujoker.calculator.R.id.logButton) {
            mscreenText = mscreenText.substring(0, cursor) + mbuttonText + "(" + mscreenText.substring(cursor);
            cursor += 4;
        } else if (b.getId() == itujoker.calculator.R.id.lnButton) {
            mscreenText = mscreenText.substring(0, cursor) + mbuttonText + "(" + mscreenText.substring(cursor);
            cursor += mbuttonText.length() + 1;
        } else if (b.getId() == itujoker.calculator.R.id.sqrtButton) {
            mscreenText = mscreenText.substring(0, cursor) + mbuttonText + "(" + mscreenText.substring(cursor);
            cursor += 2;
        } else if (b.getId() == itujoker.calculator.R.id.powButton) {
            mscreenText = mscreenText.substring(0, cursor) + "^(" + mscreenText.substring(cursor);
            cursor += 2;
        } else if (b.getId() == itujoker.calculator.R.id.perButton) {
            mscreenText = mscreenText.substring(0, cursor) + "P(:)" + mscreenText.substring(cursor);
            cursor += 2;
        } else if (b.getId() == itujoker.calculator.R.id.comButton) {
            mscreenText = mscreenText.substring(0, cursor) + "C(:)" + mscreenText.substring(cursor);
            cursor += 2;
        } else if (b.getId() == itujoker.calculator.R.id.inverseButton) {
            mscreenText = mscreenText.substring(0, cursor) + "1÷" + mscreenText.substring(cursor);
            cursor += 2;
        } else if (b.getId() == itujoker.calculator.R.id.nsqrtButton) {
            mscreenText = mscreenText.substring(0, cursor) + "^(1÷" + mscreenText.substring(cursor);
            cursor += 4;
        } else {
            mscreenText = mscreenText.substring(0, cursor) + mbuttonText + mscreenText.substring(cursor);
            cursor += 1;
        }

        //////////////
        if (b.getText().equals(".")) {
            dene = true;
        } else if (!(b.getText().charAt(0) <= '9' && b.getText().charAt(0) >= '0')) {
            dene = false;
        }


        if (!dene) {
            int l1 = mscreenText.length();
            mscreenText = turnNormal(mscreenText);

            mscreenText = turnBeautiful(mscreenText);
            int l3 = mscreenText.length();
            if (l1 < l3)
                cursor = cursor + (l3 - l1);
            else if (l1 > l3)
                cursor = cursor - (l1 - l3);
        }

        screen.setText(mscreenText);
        screen.setSelection(cursor);
    }

    public void calculateResult(View view) {
        dene = false;
        if (isVoice) {
            playbuttonVoice(this);

        }
        sendedString = screen.getText().toString();
        sendedString = turnNormal(sendedString);
        sendedString = addParanthesis(addMultiply(sendedString));
        if (callPerCom(sendedString)) {

            try {
                Double res = new Calculator().calculate(sendedString, isDeg);
                String s = doubletoDecimalString(res);
                s = turnNormal(s);
                s = turnBeautiful(s);
                result.setText(turnBeautiful(sendedString));
                screen.setText(s);
                screen.setSelection(s.length());

                historyArrayResult.put(s);
                historyArrayScreen.put(turnBeautiful(sendedString));
                setDefaults("historyResult", historyArrayResult.toString(), this);
                setDefaults("historyScreen", historyArrayScreen.toString(), this);

            } catch (Exception e) {
                result.setText("error");
            }
        } else {
            result.setText("error");
        }

    }

    public void clearText(View view) {
        dene = false;
        if (isVoice)
            playbuttonVoice(this);
        screen.setText(null);
        result.setText(null);
    }

    public void deleteText(View view) {
        dene = false;
        if (isVoice)
            playbuttonVoice(this);
        int cursorE = screen.getSelectionEnd();
        int cursorS = screen.getSelectionStart();
        String mscreenText = screen.getText().toString();
        if (cursorS == cursorE) {
            if (cursorS != 0) {
                mscreenText = mscreenText.substring(0, cursorS - 1) + mscreenText.substring(cursorS);
                int l1 = mscreenText.length();
                mscreenText = turnNormal(mscreenText);
                mscreenText = turnBeautiful(mscreenText);
                int l2 = mscreenText.length();
                if (l2 < l1)
                    cursorS = cursorS - (l1 - l2);
                screen.setText(mscreenText);
                if (cursorS != 0)
                    cursorS--;
                screen.setSelection(cursorS);

            }
        } else {
            mscreenText = mscreenText.substring(0, cursorS) + mscreenText.substring(cursorE);
            /*screen.setText(mscreenText);
            screen.setSelection(cursorS);*/
            int l1 = mscreenText.length();
            mscreenText = turnNormal(mscreenText);
            mscreenText = turnBeautiful(mscreenText);
            int l2 = mscreenText.length();
            if (l2 < l1)
                cursorS = cursorS - (l1 - l2);
            screen.setText(mscreenText);
            screen.setSelection(cursorS);
        }
    }

    ////////////////////String Shape/////////////////////////////////////


    private String turnBeautiful(String s) {
        int starting = 0;
        Queue<String> q = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (!((s.charAt(i) >= '0' && s.charAt(i) <= '9') /*|| s.charAt(i)==','*/ || s.charAt(i) == '.')) {
                q.add(Character.toString(s.charAt(i)));
                if (i < s.length() - 1 && ((s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') /*|| s.charAt(i+1)==','*/ || s.charAt(i + 1) == '.')) {
                    starting = i + 1;
                }
            } else {
                if (i < s.length() - 1 && !((s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') /*|| s.charAt(i+1)==',' */ || s.charAt(i + 1) == '.')) {
                    q.add(s.substring(starting, i + 1));
                } else if (i == s.length() - 1) {
                    q.add(s.substring(starting, i + 1));
                }
            }
        }
        String z = "";
        while (!q.isEmpty()) {
            String a = q.poll();
            if (isParse(a)) {
               /* if(q.isEmpty() && a.charAt(a.length()-1)=='.')
                    z=z+doubletoDecimalString(Double.parseDouble(a))+".";
                else if(Double.parseDouble(a)!= Math.floor(Double.parseDouble(a))&& a.charAt(a.length()-1)=='0')
                    z=z+doubletoDecimalString(Double.parseDouble(a))+"0";
                else*/
                z += doubletoDecimalString(Double.parseDouble(a));
            } else
                z += a;
        }

        return z;
    }

    private String turnNormal(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (i != s.length() - 1 && s.charAt(i) == ',')
                s = s.substring(0, i) + s.substring(i + 1);
            else if (i == s.length() - 1 && s.charAt(i) == ',')
                s = s.substring(0, i);
            else if (s.charAt(i) == '.') {
                if (i != 0 && !(s.charAt(i - 1) >= '0' && s.charAt(i - 1) <= '9')) {
                    s = s.substring(0, i) + "0" + s.substring(i);
                } else if (i == 0)
                    s = "0" + s;
            }
        }
        return s;
    }

    public static String doubletoDecimalString(double d) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter.applyPattern("#,###");
        formatter.setMaximumFractionDigits(340);
        return formatter.format(d);
    }

    private boolean isParse(String d) {
        try {
            Double.parseDouble(d);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //////////////////////Error Checking///////////////////////////////////

    public static String addMultiply(String function) {
        for (int i = 0; i < function.length(); i++) {
            if (function.charAt(i) == 'y' || (function.charAt(i) >= '0' && function.charAt(i) <= '9') || function.charAt(i) == 'π' || function.charAt(i) == 'e') {
                if ((i < function.length() - 4 && (function.substring(i + 1, i + 4).equals("sin") || function.substring(i + 1, i + 4).equals("cos") || function.substring(i + 1, i + 4).equals("tan") || function.substring(i + 1, i + 4).equals("log")))
                        || (i < function.length() - 1 && (function.charAt(i + 1) == '(' || function.charAt(i + 1) == '√' || function.charAt(i + 1) == 'P' || function.charAt(i + 1) == 'C'))
                        || (i < function.length() - 5 && (function.substring(i + 1, i + 5).equals("asin") || function.substring(i + 1, i + 5).equals("acos") || function.substring(i + 1, i + 5).equals("atan")))
                        || (i < function.length() - 3 && (function.substring(i + 1, i + 3).equals("ln")))
                        || (i < function.length() - 1 && (function.charAt(i) >= '0' && function.charAt(i) <= '9') && (function.charAt(i + 1) == 'y' || function.charAt(i + 1) == 'e' || function.charAt(i + 1) == 'π'))
                        || (i < function.length() - 1 && function.charAt(i) == 'y' && ((function.charAt(i + 1) >= '0' && function.charAt(i + 1) <= '9') || function.charAt(i + 1) == 'e' || function.charAt(i + 1) == 'π' || function.charAt(i + 1) == 'y'))
                        || (i < function.length() - 1 && function.charAt(i) == 'e' && ((function.charAt(i + 1) >= '0' && function.charAt(i + 1) <= '9') || function.charAt(i + 1) == 'y' || function.charAt(i + 1) == 'π' || function.charAt(i + 1) == 'e'))
                        || (i < function.length() - 1 && function.charAt(i) == 'π' && ((function.charAt(i + 1) >= '0' && function.charAt(i + 1) <= '9') || function.charAt(i + 1) == 'y' || function.charAt(i + 1) == 'e' || function.charAt(i + 1) == 'π'))
                        ) {
                    function = function.substring(0, i + 1) + "×" + function.substring(i + 1);
                    i++;
                }
            } else if (function.charAt(i) == '!' || function.charAt(i) == '%' || function.charAt(i) == ')') {
                if (i < function.length() - 1 && ((function.charAt(i + 1) >= '0' && function.charAt(i + 1) <= '9') || function.charAt(i + 1) == 'e' || function.charAt(i + 1) == 'π' || function.charAt(i + 1) == 'y')) {
                    function = function.substring(0, i + 1) + "×" + function.substring(i + 1);
                    i++;
                }
            }

        }

        return function;
    }

    public static String addParanthesis(String function) {
        int parentNumber = 0;
        for (int i = 0; i < function.length(); i++) {
            if (function.charAt(i) == '(')
                parentNumber++;
            else if (function.charAt(i) == ')')
                parentNumber--;
        }
        while (parentNumber != 0) {
            if (parentNumber > 0) {
                function += ")";
                parentNumber--;
            } else {
                function = "(" + function;
                parentNumber++;
            }
        }
        return function;
    }

    public boolean callPerCom(String s) {


        try

        {

            ///calculate per com
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 'P') {
                    int ilk, iki;
                    try {
                        int j = i + 2;
                        while (true) {
                            j++;
                            if (s.charAt(j) == ':')
                                break;
                        }
                        ilk = Integer.parseInt(s.substring(i + 2, j));
                        int k = j + 1;
                        while (true) {
                            k++;
                            if (s.charAt(k) == ')')
                                break;
                        }
                        iki = Integer.parseInt(s.substring(j + 1, k));
                        if (ilk < iki)
                            return false;
                        int res = (int) new Calculator().calculate("(" + ilk + "!)\u00F7(" + ilk + "-" + iki + ")!", isDeg);
                        sendedString = s.substring(0, i) + res + s.substring(k + 1);

                    } catch (Exception e) {
                        return false;
                    }
                } else if (s.charAt(i) == 'C') {
                    int ilk, iki;
                    try {
                        int j = i + 2;
                        while (true) {
                            j++;
                            if (s.charAt(j) == ':')
                                break;
                        }
                        ilk = Integer.parseInt(s.substring(i + 2, j));
                        int k = j + 1;
                        while (true) {
                            k++;
                            if (s.charAt(k) == ')')
                                break;
                        }
                        iki = Integer.parseInt(s.substring(j + 1, k));
                        if (ilk < iki)
                            return false;
                        int res = (int) new Calculator().calculate("(" + ilk + "!)\u00F7((" + ilk + "-" + iki + ")!\u00D7" + iki + "!)", isDeg);
                        sendedString = s.substring(0, i) + res + s.substring(k + 1);


                    } catch (Exception e) {
                        return false;
                    }
                }
            }
        } catch (
                Exception e
                )

        {
            return false;
        }


        return true;
    }

    ///////////////
}

