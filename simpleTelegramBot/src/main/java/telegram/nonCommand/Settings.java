package telegram.nonCommand;

public class Settings {
    private int min;


    private int max;


    private int listCount;


    private int uniqueTaskCount;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public int getUniqueTaskCount() {
        return uniqueTaskCount;
    }

    public void setUniqueTaskCount(int uniqueTaskCount) {
        this.uniqueTaskCount = uniqueTaskCount;
    }

    public Settings(int min, int max, int listCount) {
        this.min = SettingsAssistant.calculateMin(min, max);
        this.max = SettingsAssistant.calculateMax(min, max);
        this.listCount = SettingsAssistant.calculateListCount(listCount);
        this.uniqueTaskCount = SettingsAssistant.calculateUniqueTaskCount(this.min, this.max);
    }
}
