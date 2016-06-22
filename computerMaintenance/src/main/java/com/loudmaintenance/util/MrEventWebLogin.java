package com.loudmaintenance.util;

import com.computerdmaintenance.ui.wgt.event.MrEvent;

public class MrEventWebLogin extends MrEvent {


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public String resultType = "";

    public MrEventWebLogin(String resultType) {
        super(EVENT_WEB_LOGIN_RESULT_TYPE);
        // TODO Auto-generated constructor stub
        this.resultType = resultType;
    }
}