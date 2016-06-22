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
package com.loudmaintenance.util;

/**
 * @ClassName: Constants<BR>
 * @Describe：常量类<BR>
 * @Author: zhuxunkang
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-4-3 下午2:35:59
 */

public class Constants {
    /**
     * @ClassName:Api <BR>
     * @Describe：<BR>
     * @Author: zhuxunkang
     * @Extends：<BR>
     * @Version:1.0
     * @date:2016-4-3 下午2:48:33
     */
    public class Api {
        /**
         * 1、登录接口定义
         */
        public static final String LOGIN = "/Syknet/ComputerDoctor/User/Login.do";
        /**
         * 2、首页功能分类接口定义
         */
        public static final String INDEX_FUNCATIONS = "/Syknet/ComputerDoctor/funcations/funcationslist.do";
        /**
         * 广告位接口定义
         */
        public static final String INDEX_AB = "/Syknet/ComputerDoctor/ad/adlist.do";

    }

    /**
     * @ClassName: What<BR>
     * @Describe：<BR>
     * @Author: zhuxunkang
     * @Extends：<BR>
     * @Version:1.0
     * @date:2016-4-3 下午2:48:29
     */
    public class What {
        /**
         * 登录常量
         */
        public static final int WHAT_LOGIN = 0x01;
        /**
         * 首页广告位
         */
        public static final int WHAT_INDEX_AB = 0x02;
        /**
         * 首页分类功能
         */
        public static final int WHAT_INDEX_FUNCTION = 0x03;


    }

}
