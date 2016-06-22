/*
 * Copyright © YOLANDA. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.computerdmaintenance.nohttp;

import android.content.Context;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestQueue;

/**
 * Created in Mar 6, 2016 9:12:17 PM.
 *
 * @author YOLANDA;
 */
public class CallServer {

    private static CallServer instance;

    private RequestQueue queue;

    private CallServer() {
        queue = NoHttp.newRequestQueue();
    }

    public synchronized static CallServer getInstance() {
        if (instance == null) {
            instance = new CallServer();
        }
        return instance;
    }

    /**
     * 添加一个请求到请求队列
     *
     * @param context      上下文
     * @param request      请求对象
     * @param callBack     接受回调结果
     * @param what         what，当多个请求用同一个responseListener接受结果时，用来区分请求
     * @param isShowDialog 是否显示dialog
     * @param isCanCancel  请求是否能被用户取消
     * @param isShowError  是否提示用户错误信息
     */
    public <T> void add(Context context, Request<T> request, HttpCallBack<T> callBack, int what, boolean isShowDialog, boolean isCanCancel, boolean isShowError) {
        queue.add(what, request, new ResponseListener<T>(request, context, callBack, isShowDialog, isCanCancel, isShowError));
    }

}
