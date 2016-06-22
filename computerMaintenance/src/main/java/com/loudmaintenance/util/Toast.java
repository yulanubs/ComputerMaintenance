/**
 * Copyright © YOLANDA. All Rights Reserved
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.loudmaintenance.util;

import com.computerdmaintenance.ComputerMaintenanceApplication;

/**
 * @ClassName: show<BR>
 * @Describe：自定义Toast<BR>
 * @Author: zhuxunkang
 * @Extends：<BR>
 * @Version:1.0
 * @date:2016-4-3 上午10:15:13
 */
public class Toast {

    public static void show(CharSequence msg) {
        android.widget.Toast.makeText(
                ComputerMaintenanceApplication.getInstance(), msg,
                android.widget.Toast.LENGTH_LONG).show();
    }

    public static void show(int stringId) {
        android.widget.Toast.makeText(
                ComputerMaintenanceApplication.getInstance(), stringId,
                android.widget.Toast.LENGTH_LONG).show();
    }

}
