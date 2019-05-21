package itujoker.calculator;


public class History_Item {
    private String result;
    private String screen;
    private boolean isChecked;

    public String getScreen() {
        return screen;
    }

    public String getResult() {
        return result;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }
}
