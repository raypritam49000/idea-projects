package com.addy.rest.app.dto.fb;

public class Cursors {

    private String before;
    private String after;
    public void setBefore(String before) {
         this.before = before;
     }
     public String getBefore() {
         return before;
     }

    public void setAfter(String after) {
         this.after = after;
     }
     public String getAfter() {
         return after;
     }


    @Override
    public String toString() {
        return "Cursors{" +
                "before='" + before + '\'' +
                ", after='" + after + '\'' +
                '}';
    }
}