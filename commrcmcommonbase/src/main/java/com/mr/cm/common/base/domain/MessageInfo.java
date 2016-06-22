package com.mr.cm.common.base.domain;

public class MessageInfo extends InnovativeActivitiesInfo {
    private static final long serialVersionUID = 8909796697685695375L;
    private String chat_content;
    private String chat_time;
    private String user_name;

    public MessageInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MessageInfo(String title, String describe, String imgurl) {
        super(title, describe, imgurl);
        // TODO Auto-generated constructor stub
    }

    public MessageInfo(String title, String describe, String imgurl,
                       String chat_content, String chat_time, String user_name) {
        super(title, describe, imgurl);
        this.chat_content = chat_content;
        this.chat_time = chat_time;
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getChat_content() {
        return chat_content;
    }

    public void setChat_content(String chat_content) {
        this.chat_content = chat_content;
    }

    public String getChat_time() {
        return chat_time;
    }

    public void setChat_time(String chat_time) {
        this.chat_time = chat_time;
    }

}
