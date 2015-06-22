package com.bcit.jono.customadapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jono on 15-06-02.
 */
public class ListItem {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;


    public static List<ListItem> getSampleItems() {
        ArrayList<ListItem> items = new ArrayList<ListItem>();
        for(int i = 0; i < 25; i ++) {
            ListItem item = new ListItem();
            item.setTitle(String.format("%s %d", "Title", i));
            item.setDesc(String.format("%s %d", "Description", i));
            items.add(item);
        }

        return items;
    }
}
