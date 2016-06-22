package com.computerdmaintenance.ui.wgt.event;

import com.computerdmaintenance.ui.Action;


public class MrEventAction extends MrEvent {


    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */

    private static final long serialVersionUID = 1L;
    public Action act;

    public MrEventAction(Action a) {
        super(EVENT_ACTION);
        // TODO Auto-generated constructor stub
        this.act = a;
    }

}
