package com.addy.rest.app.dto.fb;

import java.util.List;

public class FacebookPageData {

    private List<FacebookData> data;
    private Paging paging;


    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public Paging getPaging() {
        return paging;
    }

    public List<FacebookData> getData() {
        return data;
    }

    public void setData(List<FacebookData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FacebookPageData{" +
                "data=" + data +
                ", paging=" + paging +
                '}';
    }
}