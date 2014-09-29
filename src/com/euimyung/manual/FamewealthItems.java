package com.euimyung.manual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FamewealthItems {

	/**
     * An array of sample (dummy) items.
     */
    public static List<AboutItem> ITEMS = new ArrayList<AboutItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, AboutItem> ITEM_MAP = new HashMap<String, AboutItem>();

    static {
        addItem(new AboutItem("1", "제1강 우주의 변화"));
        addItem(new AboutItem("2", "제2강 삼라만상"));
        addItem(new AboutItem("3", "제40강 명리 시험문제"));
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
