package com.computerdmaintenance.ui.wgt.event;

public class MrEventBarCode extends MrEvent {

    public static final int FROM_SEARCH = 0x1;
    public static final int FROM_ULECARD = 0x2;
    /**
     *
     */
    private static final long serialVersionUID = 2777648431533897853L;
    public int from;
    public String barCode = "";

    public MrEventBarCode(int from, String bar_code) {
        super(MrEvent.EVENT_READ_BAR_CODE);
        // TODO Auto-generated constructor stub
        this.from = from;
        barCode = bar_code;
    }

    public MrEventBarCode(int event, int from, String bar_code) {
        super(event);
        // TODO Auto-generated constructor stub
        this.from = from;
        barCode = bar_code;
    }

}
