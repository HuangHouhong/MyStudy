package hust.hhh.mystudy.main;

/**
 * Created by 98595 on 2019/1/22.
 */

public class Item {

    private String title;
    private Class intentClass;

    public Item() {
    }

    public Item(String title, Class intentClass) {
        this.title = title;
        this.intentClass = intentClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getIntentClass() {
        return intentClass;
    }

    public void setIntentClass(Class intentClass) {
        this.intentClass = intentClass;
    }
}
