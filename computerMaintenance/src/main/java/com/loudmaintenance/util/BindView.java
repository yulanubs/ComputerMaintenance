package com.loudmaintenance.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: <BR>
 * @Describe：注解<BR>
 * @Author: zhuxunkang
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-3-1 下午1:59:57
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {
    /**
     * ID
     */
    int id();

    /**
     * 点击事件
     */
    boolean click() default false;
}