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

import java.util.ArrayList;


public class Instructions extends AppCompatActivity {
    private ListView list;
    private Instruction_Adapter adapter;
    private ArrayList<Instruction_Item> instructionArr = new ArrayList();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Themes.onActivityCreateSetTheme(this);
        setContentView(itujoker.calculator.R.layout.instructions_layout);

        list=(ListView) findViewById(itujoker.calculator.R.id.instruction_list);
        printInstructions();
        adapter=new Instruction_Adapter(this,instructionArr,getResources());
        list.setAdapter(adapter);
    }

    public void goback(View v){
        /*Intent i = new Intent(this, MainActivity.class);
        startActivity(i);*/
        new GoMain(Instructions.this).execute();
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


    public void printInstructions(){
        Instruction_Item temp=new Instruction_Item();
        temp.setHeader("Rounding ,Auto Parenthesis Completion and Auto Multiplication Completion");
        temp.setInfo("-The numbers are rounded automatically after 15 digits(both in decimal and fractional part)\n" +
                "-There is auto parenthesis completion(For example you can write sin(30\n" +
                "-There is auto multiplication completion(For example 2sin(30 will be taken as 2*sin(30)");
        instructionArr.add(temp);
        temp=new Instruction_Item();
        temp.setHeader("P and C");
        temp.setInfo("P is permutation and C is Combination.For example P(5:1) is 5");
        instructionArr.add(temp);
        temp=new Instruction_Item();
        temp.setHeader("Inv Button");
        temp.setInfo("New buttons can be seen with this button");
        instructionArr.add(temp);
        temp=new Instruction_Item();
        temp.setHeader("Menu");
        temp.setInfo("Menu can be opened with settings button or scrolling on the left screen");
        instructionArr.add(temp);
        temp=new Instruction_Item();
        temp.setHeader("Functions");
        temp.setInfo("- Functions have four sections which are root finding,derivative value finding ,integral calculation and function value calculation\n" +
                "- '*' is used for multiplication and 'x' is for function variable in fuctions part\n" +
                "-There is auto multiplication completion.For example you can write 2x instead of 2*x in function part \n" +
                "In root finding,if the root is very close to 0(For example 0.00001) or the root is 0,the algorithm may not find the root");
        instructionArr.add(temp);
        temp=new Instruction_Item();
        temp.setHeader("History");
        temp.setInfo("- History shows the last results. You can remove them with checking checkboxes and pressing delete button\n" +
                "-When clicking a history object,the result will be taken to the calculator");
        instructionArr.add(temp);
    }
}
