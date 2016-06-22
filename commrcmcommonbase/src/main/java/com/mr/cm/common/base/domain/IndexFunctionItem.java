package com.mr.cm.common.base.domain;

import java.io.Serializable;

public class IndexFunctionItem implements Serializable {


    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */

    private static final long serialVersionUID = 1L;
    public IndexFunctionItem item;
    public String default_function;
    public String device_type;
    public String link;
    public String log_title;
    public String is_new;
    public String imgUrl;
    public String id;
    public String title;
    public String sectionId;
    public String need_login;
    public String min_version;
    public String priority;
    public String max_version;
    public String android_action;
    public String key;
    public String delete_flag = "0";

    public String areaName;
    public String provinceId;
    public String cityId;
    public String cityName;
    public String layer;
    public String provinceName;
    public String fullName;
    public String firstName;
    public String action;
    public String attribute1;
    public String attribute2;
    public String attribute3;

    public IndexFunctionItem() {
    }

    public IndexFunctionItem(String default_function, String device_type,
                             String link, String log_title, String is_new, String imgUrl,
                             String id, String title, String sectionId, String need_login,
                             String min_version, String priority, String max_version,
                             String android_action, String key, String delete_flag) {
        super();
        this.default_function = default_function;
        this.device_type = device_type;
        this.link = link;
        this.log_title = log_title;
        this.is_new = is_new;
        this.imgUrl = imgUrl;
        this.id = id;
        this.title = title;
        this.sectionId = sectionId;
        this.need_login = need_login;
        this.min_version = min_version;
        this.priority = priority;
        this.max_version = max_version;
        this.android_action = android_action;
        this.key = key;
        this.delete_flag = delete_flag;
    }


    /**
     * @return the default_function
     */
    public String getDefault_function() {
        return default_function;
    }

    /**
     * @param default_function the default_function to set
     */
    public void setDefault_function(String default_function) {
        this.default_function = default_function;
    }

    /**
     * @return the device_type
     */
    public String getDevice_type() {
        return device_type;
    }

    /**
     * @param device_type the device_type to set
     */
    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the log_title
     */
    public String getLog_title() {
        return log_title;
    }

    /**
     * @param log_title the log_title to set
     */
    public void setLog_title(String log_title) {
        this.log_title = log_title;
    }

    /**
     * @return the is_new
     */
    public String getIs_new() {
        return is_new;
    }

    /**
     * @param is_new the is_new to set
     */
    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    /**
     * @return the imgUrl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl the imgUrl to set
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the sectionId
     */
    public String getSectionId() {
        return sectionId;
    }

    /**
     * @param sectionId the sectionId to set
     */
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * @return the need_login
     */
    public String getNeed_login() {
        return need_login;
    }

    /**
     * @param need_login the need_login to set
     */
    public void setNeed_login(String need_login) {
        this.need_login = need_login;
    }

    /**
     * @return the min_version
     */
    public String getMin_version() {
        return min_version;
    }

    /**
     * @param min_version the min_version to set
     */
    public void setMin_version(String min_version) {
        this.min_version = min_version;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the max_version
     */
    public String getMax_version() {
        return max_version;
    }

    /**
     * @param max_version the max_version to set
     */
    public void setMax_version(String max_version) {
        this.max_version = max_version;
    }

    /**
     * @return the android_action
     */
    public String getAndroid_action() {
        return android_action;
    }

    /**
     * @param android_action the android_action to set
     */
    public void setAndroid_action(String android_action) {
        this.android_action = android_action;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }


    /**
     * @return the deleteFlag
     */
    public String getDeleteFlag() {
        return delete_flag;
    }

    /**
     * @param deleteFlag the deleteFlag to set
     */
    public void setDeleteFlag(String delete_flag) {
        this.delete_flag = delete_flag;
    }

    /**
     * @return the item
     */
    public IndexFunctionItem getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(IndexFunctionItem item) {
        this.item = item;
    }

}
