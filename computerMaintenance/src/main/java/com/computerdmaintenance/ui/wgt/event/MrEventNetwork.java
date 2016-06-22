package com.computerdmaintenance.ui.wgt.event;

public class MrEventNetwork extends MrEvent {


    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */

    private static final long serialVersionUID = 1L;

    public MrEventNetwork(boolean connected) {
        super(connected ? EVENT_NETWORK_SUCCESS : EVENT_NETWORK_ERR);
        // TODO Auto-generated constructor stub
    }

}
