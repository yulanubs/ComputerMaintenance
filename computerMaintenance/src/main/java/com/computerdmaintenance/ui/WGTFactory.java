package com.computerdmaintenance.ui;

import android.content.Context;

import com.computerdmaintenance.ui.wgt.BaseWgt;
import com.loudmaintenance.util.MrLog;

import java.lang.ref.SoftReference;
import java.lang.reflect.Constructor;
import java.util.HashMap;

public enum WGTFactory {

    INSTANCE;

    private HashMap<Object, SoftReference<BaseWgt>> viewCache = new HashMap<Object, SoftReference<BaseWgt>>();


    private BaseWgt getFromCache(Class<?> type) {

        SoftReference<BaseWgt> cachedview = viewCache.get(type.getName());
        BaseWgt wgt = null;

        if (cachedview != null) {
            wgt = cachedview.get();
        }

        if (wgt != null) {
            return wgt;
        }
        return null;
    }

    private void cacheView(Class<?> key, BaseWgt view) {/*
        int s = view.getLifeCycle() & BaseWgt.MULTI;
		if (s == BaseWgt.MULTI)// not single
			return;

		if (viewCache.containsKey(key.getName())){
			viewCache.remove(key.getName());
		}

		viewCache.put(key.getName(), new SoftReference<BaseWgt>(view));
		
	*/
    }

    public BaseWgt createWGT(Context context, String className) {

        try {
            Class<?> c = Class.forName(className);
            return createWGT(context, c);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            MrLog.excaption(e);
        }

        return null;
    }

    public BaseWgt createWGT(Context context, Class<?> type) {

        try {

            BaseWgt wgt = getFromCache(type);

            if (wgt != null) {
                return wgt;
            }

            Constructor<?> ctor = type.getConstructor(android.content.Context.class);
            if (ctor != null) {
                wgt = (BaseWgt) ctor.newInstance(context);
                cacheView(type, wgt);
                return wgt;
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            MrLog.excaption(e);
        }
        /*catch (SecurityException e) {
            // TODO Auto-generated catch block
			UleLog.excaption(e);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			UleLog.excaption(e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			UleLog.excaption(e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			UleLog.excaption(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			UleLog.excaption(e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			UleLog.excaption(e);
		}*/

        return null;
    }

    private void sent2Back(BaseWgt temp, WGTContainer container) {
        // TODO Auto-generated method stub
        if (temp == null)
            return;
//		temp.removeContainer();
        temp.onSentToBack();
    }

    private void bring2front(BaseWgt temp, WGTContainer container) {
        // TODO Auto-generated method stub
        if (temp == null)
            return;
        temp.setContainer(container);
        temp.onBringToFront();
    }

    public void wgtRemoved(BaseWgt current, WGTContainer container) {
        sent2Back(current, container);
    }

    public void wgtAttached(BaseWgt wgt, WGTContainer container) {
        bring2front(wgt, container);
    }

    public void release() {
        viewCache.clear();
    }

}
