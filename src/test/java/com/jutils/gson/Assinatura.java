package com.jutils.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mi on 18-1-11.
 */
public class Assinatura extends DiffTypeBase {

    @SerializedName("line_items")
    private LineItems lineItems;

    public LineItems getLineItems() {
        return lineItems;
    }

    public void setLineItems(LineItems lineItems) {
        this.lineItems = lineItems;
    }

    public class LineItems {
        @SerializedName("meta_data")
        private AssiData metaData;

        public AssiData getMetaData() {
            return metaData;
        }

        public void setMetaData(AssiData metaData) {
            this.metaData = metaData;
        }
    }



    public class AssiData {
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
