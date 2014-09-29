package com.euimyung.manual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryItems {

	/**
     * An array of sample (dummy) items.
     */
    public static List<AboutItem> ITEMS = new ArrayList<AboutItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, AboutItem> ITEM_MAP = new HashMap<String, AboutItem>();

    static {
        addItem(new AboutItem("1", "제1권 의학1"));
        addItem(new AboutItem("2", "제2권 의학2"));
        addItem(new AboutItem("3", "제3권 의학3"));
        addItem(new AboutItem("4", "제5권 명리5"));
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
