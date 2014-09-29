package com.euimyung.manual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnItems {

	/**
     * An array of sample (dummy) items.
     */
    public static List<ColumnItem> ITEMS = new ArrayList<ColumnItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, ColumnItem> ITEM_MAP = new HashMap<String, ColumnItem>();

    static {
    	for (int i = 1; i <= 100; i++) {
    		addItem(new ColumnItem(Integer.toString(i), "칼럼 "+ Integer.toString(i)));
    	}
    }

    private static void addItem(ColumnItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class ColumnItem {
        public String id;
        public String content;

        public ColumnItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
