package com.euimyung.manual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AboutItems {

	/**
     * An array of sample (dummy) items.
     */
    public static List<AboutItem> ITEMS = new ArrayList<AboutItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, AboutItem> ITEM_MAP = new HashMap<String, AboutItem>();

    static {
        addItem(new AboutItem("01", "박사님 이력,소개"));
        addItem(new AboutItem("02", "의명학 소개 박사님 인터뷰 영상"));
        addItem(new AboutItem("03", "의명학 텍스트"));
        addItem(new AboutItem("04", "박사님 칼럼"));
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
