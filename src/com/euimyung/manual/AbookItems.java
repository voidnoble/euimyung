package com.euimyung.manual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbookItems {

	/**
     * An array of sample (dummy) items.
     */
    public static List<AboutItem> ITEMS = new ArrayList<AboutItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, AboutItem> ITEM_MAP = new HashMap<String, AboutItem>();

    static {
        addItem(new AboutItem("1", "생명의 비밀"));
        addItem(new AboutItem("2", "본초"));
        addItem(new AboutItem("3", "밥상부터 힐링하라"));
        addItem(new AboutItem("4", "소설 도덕경1"));
        addItem(new AboutItem("5", "소설 도덕경2"));
        addItem(new AboutItem("6", "소설 도덕경3"));
    }

    private static void addItem(AboutItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class AboutItem {
        public String id;
        public String content;

        public AboutItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
