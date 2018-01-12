package com.jutils.gson.entity;

import com.google.gson.annotations.SerializedName;
import com.jutils.gson.entity.DiffTypeBase;

import java.util.List;

/**
 * Created by mi on 18-1-11.
 */
public class Unico extends DiffTypeBase {
    @SerializedName("line_items")
    private LineItem lineItem;

    public LineItem getLineItem() {
        return lineItem;
    }

    public void setLineItem(LineItem lineItem) {
        this.lineItem = lineItem;
    }

    public class LineItem {
        @SerializedName("meta_data")
        private List<UnicoData> metaData;

        public List<UnicoData> getMetaData() {
            return metaData;
        }

        public void setMetaData(List<UnicoData> metaData) {
            this.metaData = metaData;
        }
    }

    public class UnicoData {
        private String id;
        private String key;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
