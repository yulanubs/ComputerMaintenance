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
import com.mr.cm.common.base.domain.UserViewModle;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.JsonObjectRequest;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.RestRequest;
import com.yolanda.nohttp.StringRequest;

/**
 * @ClassName:ObjectRequest <BR>
 * @Describe：对象请求<BR>
 * @Author: zhuxunkang
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-4-3 上午10:12:00
 */
public class ObjectRequest extends RestRequest<UserViewModle> {

    public ObjectRequest(String server, String url, RequestMethod requestMethod) {
        super(server + url, requestMethod);
    }

    public ObjectRequest(String url) {
        super(url);
    }

    @Override
    public String getAccept() {
        return JsonObjectRequest.ACCEPT;
    }

    @SuppressWarnings("unchecked")
    @Override
    public UserViewModle parseResponse(String url, Headers responseHeaders,
                                       byte[] responseBody) {
        UserViewModle info = null;
        String string = StringRequest.parseResponseString(url, responseHeaders,
                responseBody);

        try {
            info = JSON.parseObject(string, UserViewModle.class);
        } catch (Exception e) {
            info = new UserViewModle();
        }
        return info;
    }

}
