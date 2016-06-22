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
import com.alibaba.fastjson.JSONObject;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.JsonObjectRequest;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RestRequest;
import com.yolanda.nohttp.StringRequest;

/**
 * @ClassName:FastJsonRequest <BR>
 * @Describe：自定义FastJsonRequest对象，所有的自定义对象都要继承{@link RestReqeust}<BR>
 * @Author: zhuxunkang
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-4-3 上午10:16:30
 */
public class FastJsonRequest extends RestRequest<JSONObject> {

    public FastJsonRequest(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
    }

    public FastJsonRequest(String url) {
        super(url);
    }

    /**
     * 解析服务端数据成{@link JsonObject}
     *
     * @param url
     * @param responseHeaders
     * @param responseBody
     * @return
     */
    public static JSONObject parse(String url, Headers responseHeaders, byte[] responseBody) {
        String string = StringRequest.parseResponseString(url, responseHeaders, responseBody);
        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(string);
        } catch (Exception e) {// 可能返回的数据不是json，或者其他异常
            string = "{}";
            jsonObject = JSON.parseObject(string);
        }
        return jsonObject;
    }

    /**
     * 高速服务端你能接受的数据类型是什么
     */
    @Override
    public String getAccept() {
        return JsonObjectRequest.ACCEPT;
    }

    /**
     * @param url             请求的url
     * @param responseHeaders 服务端的响应头
     * @param 服务端的响应数据
     * @return 你解析后的对象
     */
    @Override
    public JSONObject parseResponse(String url, Headers responseHeaders, byte[] responseBody) {
        return parse(url, responseHeaders, responseBody);
    }

}
