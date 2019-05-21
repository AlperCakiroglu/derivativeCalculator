package itujoker.calculator;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;


public class Functions extends AppCompatActivity {

    private ViewFlipper vf,vf2,vf3,function_type_vp;
    private EditText function,functionResult;
    private EditText rootguess,derivativeatthis,integralb,integrala,valuex;
    private CheckBox function_root,integral,derivative,value;
    private TextView funcDeg;
    private View focusedView;
    private Button funcdegorrad;
    private boolean isSound;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Themes.onActivityCreateSetTheme(this);
        setContentView(itujoker.calculator.R.layout.functions_layout);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM, WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        checkfunctionsound(this);
        initializefunction();

    }

    private void initializefunction(){
        vf=(ViewFlipper)findViewById(itujoker.calculator.R.id.inverseflipperfunction);
        vf2=(ViewFlipper)findViewById(itujoker.calculator.R.id.inverseflipperfunction2);
        vf3=(ViewFlipper)findViewById(itujoker.calculator.R.id.inverseflipperfunction3);
        function_type_vp=(ViewFlipper)findViewById(itujoker.calculator.R.id.function_inputs);
        function=(EditText)findViewById(itujoker.calculator.R.id.function);
        functionResult=(EditText)findViewById(itujoker.calculator.R.id.function_result);
        rootguess=(EditText)findViewById(itujoker.calculator.R.id.rootguess);
        derivativeatthis=(EditText)findViewById(itujoker.calculator.R.id.derivative_atthis);
        integralb=(EditText)findViewById(itujoker.calculator.R.id.integral_b);
        integrala=(EditText)findViewById(itujoker.calculator.R.id.integral_a);
        valuex=(EditText)findViewById(itujoker.calculator.R.id.valuex);
        function_root=(CheckBox) findViewById(itujoker.calculator.R.id.function_root);
        derivative=(CheckBox) findViewById(itujoker.calculator.R.id.Derivative);
        integral=(CheckBox) findViewById(itujoker.calculator.R.id.Integral);
        value=(CheckBox) findViewById(itujoker.calculator.R.id.Value);
        funcDeg=(TextView) findViewById(itujoker.calculator.R.id.funcdeg);
        funcdegorrad=(Button) findViewById(itujoker.calculator.R.id.funcdegorrad);

        function_root.setChecked(true);
        derivative.setChecked(false);
        integral.setChecked(false);
        value.setChecked(false);
        function_type_vp.setDisplayedChild(0);

        View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    focusedView=v;

                } else {
                    focusedView = null;

                }
            }
        };


        function.setOnFocusChangeListener(focusListener);
        rootguess.setOnFocusChangeListener(focusListener);
        derivativeatthis.setOnFocusChangeListener(focusListener);
        integralb.setOnFocusChangeListener(focusListener);
        integrala.setOnFocusChangeListener(focusListener);
        valuex.setOnFocusChangeListener(focusListener);

        if(MainActivity.isDeg){
            funcDeg.setText("Deg");
            funcdegorrad.setText("Deg");
        }
        else{
            funcDeg.setText("Rad");
            funcdegorrad.setText("Rad");}
    }
    private void checkfunctionsound(Activity activity){
        int voice = 0;

        try {
            voice = Integer.parseInt(MainActivity.getDefaults("sound", activity));
        } catch (Exception e) {
        }
        if (voice == 0) {
            isSound = false;

        } else {
            isSound = true;

        }
    }
    //////
    public void returnbackfunction(View v){
        /*Intent i = new Intent(this, MainActivity.class);
        startActivity(i);*/
        new GoMain(Functions.this).execute();
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




    public void nextflipper(View v){
        if (isSound)
            MainActivity.playbuttonVoice(this);
        vf.showNext();
        vf2.showNext();
        vf3.showNext();

    }

    public void printfunction(View view){
        try{
            if (isSound)
                MainActivity.playbuttonVoice(this);
        Button b = (Button) view;
        EditText temp=(EditText)focusedView;
            if(temp==null)
                temp=function;
        String mscreenText = temp.getText().toString();
        String mbuttonText = b.getText().toString();
        int cursor = temp.getSelectionStart();

        if(temp.getId()==function.getId()) {
            if (b.getId() == itujoker.calculator.R.id.funcasin) {
                mscreenText = mscreenText.substring(0, cursor) + "asin(" + mscreenText.substring(cursor);
                cursor += 5;
            } else if (b.getId() == itujoker.calculator.R.id.funcacos) {
                mscreenText = mscreenText.substring(0, cursor) + "acos(" + mscreenText.substring(cursor);
                cursor += 5;
            } else if (b.getId() == itujoker.calculator.R.id.funcatan) {
                mscreenText = mscreenText.substring(0, cursor) + "atan(" + mscreenText.substring(cursor);
                cursor += 5;
            } else if (b.getId() == itujoker.calculator.R.id.funcsin || b.getId() == itujoker.calculator.R.id.funccos || b.getId() == itujoker.calculator.R.id.functan || b.getId() == itujoker.calculator.R.id.funclog) {
                mscreenText = mscreenText.substring(0, cursor) + mbuttonText + "(" + mscreenText.substring(cursor);
                cursor += 4;
            } else if (b.getId() == itujoker.calculator.R.id.funcln) {
                mscreenText = mscreenText.substring(0, cursor) + mbuttonText + "(" + mscreenText.substring(cursor);
                cursor += mbuttonText.length() + 1;
            } else if (b.getId() == itujoker.calculator.R.id.funcsqrt) {
                mscreenText = mscreenText.substring(0, cursor) + mbuttonText + "(" + mscreenText.substring(cursor);
                cursor += 2;
            } else if (b.getId() == itujoker.calculator.R.id.funcpow) {
                mscreenText = mscreenText.substring(0, cursor) + "^" + mscreenText.substring(cursor);
                cursor += 1;
            } else {
                mscreenText = mscreenText.substring(0, cursor) + mbuttonText + mscreenText.substring(cursor);
                cursor += 1;
            }
            temp.setText(mscreenText);
            temp.setSelection(cursor);
        }
            else{
            if(mbuttonText.equals(".") || mbuttonText.equals("-") || (mbuttonText.charAt(0)<='9' && mbuttonText.charAt(0)>='0')){
                mscreenText = mscreenText.substring(0, cursor) + mbuttonText + mscreenText.substring(cursor);
                cursor += 1;
            }
            temp.setText(mscreenText);
            temp.setSelection(cursor);
        }


        }catch (Exception e){}

    }
    public void clfunction(View view){
        try{
            if (isSound)
                MainActivity.playbuttonVoice(this);
        EditText temp=(EditText)focusedView;
            if(temp==null)
                temp=function;
        temp.setText(null);}
        catch (Exception e){}
    }
    public void deletefunction(View view){
        try{
            if (isSound)
                MainActivity.playbuttonVoice(this);
        EditText temp=(EditText)focusedView;
            if(temp==null)
                temp=function;
        int cursorE = temp.getSelectionEnd();
        int cursorS = temp.getSelectionStart();
        String mscreenText = temp.getText().toString();
        if (cursorS == cursorE) {
            if (cursorS != 0) {
                mscreenText = mscreenText.substring(0, cursorS - 1) + mscreenText.substring(cursorS);
                cursorS--;
                temp.setText(mscreenText);
                temp.setSelection(cursorS);

            }
        } else {
            mscreenText = mscreenText.substring(0, cursorS) + mscreenText.substring(cursorE);
            function.setText(mscreenText);
            function.setSelection(cursorS);
        }}catch (Exception e){}
    }
    public void functypeclick(View view){
        if (isSound)
            MainActivity.playbuttonVoice(this);
        CheckBox b = (CheckBox) view;
        b.setChecked(true);
        rootguess.setText(null);derivativeatthis.setText(null);integralb.setText(null);integrala.setText(null);
        if(b.getId()== itujoker.calculator.R.id.function_root){
            derivative.setChecked(false);
            integral.setChecked(false);
            value.setChecked(false);
            function_type_vp.setDisplayedChild(0);
        }
        else if(b.getId() == itujoker.calculator.R.id.Derivative){

            function_root.setChecked(false);
            integral.setChecked(false);
            value.setChecked(false);
            function_type_vp.setDisplayedChild(1);
        }
        else if(b.getId() == itujoker.calculator.R.id.Integral){
            derivative.setChecked(false);
            function_root.setChecked(false);
            value.setChecked(false);
            function_type_vp.setDisplayedChild(2);
        }
        else if(b.getId() == itujoker.calculator.R.id.Value){
            derivative.setChecked(false);
            function_root.setChecked(false);
            integral.setChecked(false);
            function_type_vp.setDisplayedChild(3);
        }
    }
    public void calculatefunction(View view){
        if (isSound)
            MainActivity.playbuttonVoice(this);
        Button b = (Button) view;
        String func=function.getText().toString();
        func=correctString(func);
        func = MainActivity.addParanthesis(MainActivity.addMultiply(func));
        if(b.getText().equals("Find Root")){

            try {
                double res = new Newton_Raphson(0.01,0.01,1000,Double.parseDouble(rootguess.getText().toString()),func).findRoot();

                if (res == 61.616161)
                    functionResult.setText("Couldnt find the root");

                else {

                    functionResult.setText(MainActivity.doubletoDecimalString(res));

                }

            } catch (Exception e) {
                functionResult.setText("Error");
            }

        }
        else if(b.getText().equals("Find Derivative")){
            try {
                double res = new Numerical_Diff(func, Double.parseDouble(derivativeatthis.getText().toString()), 0.01).find_Derivative();
                functionResult.setText(MainActivity.doubletoDecimalString(res));

            } catch (Exception e) {
                functionResult.setText("Error");
            }
        }
        else if(b.getText().equals("Find Integral")){
            try {
                double res = new Numerical_Int(func, Double.parseDouble(integrala.getText().toString()),
                        Double.parseDouble(integralb.getText().toString()), 100).calculate_Int();

                functionResult.setText(MainActivity.doubletoDecimalString(res));

            } catch (Exception e) {
                functionResult.setText("Error");
            }
        }
        else if(b.getText().equals("Find Value")){
            try {
                double res = new Numerical_Diff(func, 0, 0.01).f(Double.parseDouble(valuex.getText().toString()));
                functionResult.setText(MainActivity.doubletoDecimalString(res));

            } catch (Exception e) {
                functionResult.setText("Error");
            }

        }
    }
    private String correctString(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='x' && i!=s.length()-1){
                s=s.substring(0,i)+"y"+s.substring(i+1);
            }
            else if(s.charAt(i)=='x' && i==s.length()-1){
                s=s.substring(0,i)+"y";
            }
            else if(s.charAt(i)=='*' && i!=s.length()-1){
                s=s.substring(0,i)+"×"+s.substring(i+1);
            }else if(s.charAt(i)=='*' && i==s.length()-1){
                s=s.substring(0,i)+"×";
            }
        }
        return s;
    }
    public void degorradfunction(View v){
        if(funcDeg.getText().toString().equals("Deg")){
            MainActivity.isDeg=false;
            funcDeg.setText("Rad");
            funcdegorrad.setText("Rad");

        }
        else{
            MainActivity.isDeg=true;
            funcDeg.setText("Deg");
            funcdegorrad.setText("Deg");
        }
    }
}
