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

import com.alibaba.fastjson.JSON;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.JsonObjectRequest;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RestRequest;
import com.yolanda.nohttp.StringRequest;

/**
 * <p>
 * 一个可以解析所有JavaBean的请求
 * </p>
 * Created in Mar 13, 2016 9:12:57 PM.
 *
 * @author YOLANDA;
 */
public class AllModuleReqeust<T> extends RestRequest<T> {

    private Class<T> clazz;

    public AllModuleReqeust(String url, RequestMethod requestMethod, Class<T> clazz) {
        super(url, requestMethod);
        this.clazz = clazz;
    }

    public AllModuleReqeust(String url, Class<T> clazz) {
        this(url, RequestMethod.POST, clazz);
    }

    @Override
    public T parseResponse(String url, Headers responseHeaders, byte[] responseBody) {
        String res = StringRequest.parseResponseString(url, responseHeaders, responseBody);
        try {
            return JSON.parseObject(res, clazz);
        } catch (Exception e) {
            Logger.e(e);
            try {
                // 所以传进来的JavaBean一定要提供默认无参构造
                return clazz.newInstance();
            } catch (Exception e1) {
            }
        }
        return null;
    }

    @Override
    public String getAccept() {
        return JsonObjectRequest.ACCEPT;
    }

}
