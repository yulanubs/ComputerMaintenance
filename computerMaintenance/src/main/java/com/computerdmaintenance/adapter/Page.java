package com.computerdmaintenance.adapter;

import java.io.Serializable;

public class Page implements Serializable {

    private static final long serialVersionUID = 541234567603761859L;

    public int PageSize = 10;

    public int PageIndex = 1;
    public int records = 0;
    public int prenum = 2;
    public int start = 1;
    public int end = PageSize;
    public int total = 0;

    public long PageCount() {
        return (long) Math.ceil((double) total / (double) PageSize);
    }

    public void reset() {
        PageIndex = 1;
        start = 1;
        end = PageSize;
        total = 0;
    }

    public void increase() {

        PageIndex++;
        start = PageSize * (PageIndex - 1) + 1;
        end = PageSize * PageIndex;
        // Log.e("Page", "increase start: "+start);
        // Log.e("Page", "increase end: "+end);
        // Log.e("Page", "increase PageIndex: "+PageIndex);
    }

    public void decrease() {
        //			PageIndex=PageIndex>1?PageIndex--:1;
        PageIndex--;
        if (PageIndex < 1) {
            PageIndex = 1;
        }
        start = PageSize * (PageIndex - 1) + 1;
        end = PageSize * PageIndex;
        // Log.e("Page", "decrease start: "+start);
        // Log.e("Page", "decrease end: "+end);
        // Log.e("Page", "decrease PageIndex: "+PageIndex);
    }


}