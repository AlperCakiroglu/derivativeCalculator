package itujoker.calculator;


import android.app.Activity;
import android.content.Intent;

public class Themes {

    private static int sTheme;
    public final static int THEME_PAPER = 0;
    public final static int THEME_WATER = 1;
    public final static int THEME_SPACE = 2;

    public static void changeToTheme(Activity activity, int theme) {
        //sTheme = theme;
        MainActivity.setDefaults("theme", Integer.toString(theme), activity);
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public static void onActivityCreateSetTheme(Activity activity) {
        int themetype = THEME_PAPER;
        try {
            themetype = Integer.parseInt(MainActivity.getDefaults("theme", activity));
        } catch (Exception e) {
        }

        switch (themetype) {
            default:
            case THEME_PAPER:
                activity.setTheme(itujoker.calculator.R.style.Theme2);
                break;
            case THEME_WATER:
                activity.setTheme(itujoker.calculator.R.style.AppTheme);
                break;
            case THEME_SPACE:
                activity.setTheme(itujoker.calculator.R.style.Theme1);
                break;
        }
    }
}
