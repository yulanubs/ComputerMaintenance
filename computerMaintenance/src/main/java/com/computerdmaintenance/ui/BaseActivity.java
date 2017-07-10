package com.computerdmaintenance.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;

import com.computerdmaintenance.ComputerMaintenanceApplication;
import com.computerdmaintenance.util.BindView;

import java.lang.reflect.Field;

/**
 * @ClassName:BaseActivity <BR>
 * @Describe：基础Activity<BR>
 * @Author: zhuxunkang
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-4-23 下午1:43:24
 */
public abstract class BaseActivity extends Activity implements OnClickListener {
    public ComputerMaintenanceApplication app;

    /**
     * @param classRoomActivity  当前类，一般为Activity或Fragment
     * @param classRoomActivity2 待绑定控件的直接或间接父控件
     */
    public static void initBindView(ClassRoomActivity classRoomActivity, ClassRoomActivity classRoomActivity2) {
        // 通过反射获取到全部属性，反射的字段可能是一个类（静态）字段或实例字段
        Field[] fields = classRoomActivity.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                // 返回BindView类型的注解内容
                BindView bindView = field.getAnnotation(BindView.class);
                if (bindView != null) {
                    int viewId = bindView.id();
                    boolean clickLis = bindView.click();
                    try {
                        field.setAccessible(true);
                        if (clickLis) {
                            classRoomActivity2.findViewById(viewId).setOnClickListener(
                                    classRoomActivity);
                        }
                        // 将currentClass的field赋值为sourceView.findViewById(viewId)
                        field.set(classRoomActivity, classRoomActivity2.findViewById(viewId));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (ComputerMaintenanceApplication) getApplication();

    }
}
