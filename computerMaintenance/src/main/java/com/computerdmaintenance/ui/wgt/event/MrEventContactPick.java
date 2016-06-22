package com.computerdmaintenance.ui.wgt.event;

import android.content.Intent;

public class MrEventContactPick extends MrEvent {


    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */

    private static final long serialVersionUID = 1L;
    public Intent data;

    public MrEventContactPick(Intent data) {
        super(EVENT_CONTACT_PICK);
        // TODO Auto-generated constructor stub
        this.data = data;
    }

}
