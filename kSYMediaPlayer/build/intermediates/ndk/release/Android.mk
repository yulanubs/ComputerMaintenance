LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := kSYMediaPlayer
LOCAL_SRC_FILES := \
	E:\a_code\ComputerMaintenance\kSYMediaPlayer\src\main\jni\Android.mk \
	E:\a_code\ComputerMaintenance\kSYMediaPlayer\src\main\jni\Application.mk \
	E:\a_code\ComputerMaintenance\kSYMediaPlayer\src\main\jni\ijkmedia \
	E:\a_code\ComputerMaintenance\kSYMediaPlayer\src\main\jni\ffmpeg\Android.mk \
	E:\a_code\ComputerMaintenance\kSYMediaPlayer\src\main\jni\ffmpeg\include \

LOCAL_C_INCLUDES += E:\a_code\ComputerMaintenance\kSYMediaPlayer\src\main\jni
LOCAL_C_INCLUDES += E:\a_code\ComputerMaintenance\kSYMediaPlayer\src\release\jni

include $(BUILD_SHARED_LIBRARY)
