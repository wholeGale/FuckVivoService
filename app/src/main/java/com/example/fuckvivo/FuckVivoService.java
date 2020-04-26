package com.example.fuckvivo;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import androidx.annotation.RequiresApi;

import java.util.List;

public class FuckVivoService extends AccessibilityService {
    private static final String TAG = "FuckVivoService";

    private final Handler myHandler = new Handler(Looper.getMainLooper());

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {


        CharSequence packageName = accessibilityEvent.getPackageName();

        Log.d(TAG, TAG + "->onAccessibilityEvent()->packageName:" + packageName.toString());
        // "com.android.packageinstaller"

        //获取到当前屏幕的根节点
        AccessibilityNodeInfo rootInActiveWindow = accessibilityEvent.getSource();
        if(rootInActiveWindow == null){
            rootInActiveWindow = getRootInActiveWindow();
        }
        if(rootInActiveWindow == null){
            return;
        }

        Log.d(TAG, TAG + "->开始查找111");
        {
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> installBtns = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.android.packageinstaller:id/continue_button");
                if(installBtns == null || installBtns.isEmpty()){
                    installBtns = rootInActiveWindow.findAccessibilityNodeInfosByText("继续安装");
                }
                if (installBtns != null && installBtns.size() > 0) {
                    Log.d(TAG, TAG + "->onAccessibilityEvent()->应用安装界面，继续按钮:" + installBtns.get(0).getText().toString());
                    if (installBtns.get(0).getText().toString().equalsIgnoreCase("继续安装")) {
                        final AccessibilityNodeInfo installAccessibilityNodeInfo = installBtns.get(0);
                        myHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                installAccessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        },1500);

                        Toast.makeText(this, "干死傻逼系统提示", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        /*
        {
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> installBtns = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.android.packageinstaller:id/ok_button");
                if(installBtns == null || installBtns.isEmpty()){
                    installBtns = rootInActiveWindow.findAccessibilityNodeInfosByText("继续安装");
                }
                if (installBtns != null && installBtns.size() > 0) {
                    Log.d(TAG, TAG + "->onAccessibilityEvent()->应用安装界面，确认按钮:" + installBtns.get(0).getText().toString());
                    if (installBtns.get(0).getText().toString().equalsIgnoreCase("安装")) {
                        installBtns.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        Toast.makeText(this, "干死傻逼系统提示", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        */


        /*
        {
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> dialog_pwd = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.bbk.account:id/dialog_pwd");
                if(dialog_pwd == null){
//                    dialog_pwd = rootInActiveWindow.findAccessibilityNodeInfosByText();
                }
                if (dialog_pwd != null && dialog_pwd.size() > 0) {
                    Log.d(TAG, TAG + "->找到输入框 - 自动填入密码");
                    Bundle arguments = new Bundle();
                    arguments.putCharSequence(
                            AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "Xzb123456.");
                    dialog_pwd.get(0).performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
                    Toast.makeText(this, "干死傻逼提示-自动输入密码", Toast.LENGTH_SHORT).show();

                    final List<AccessibilityNodeInfo> button1 = rootInActiveWindow.findAccessibilityNodeInfosByViewId("android:id/button1");

                    if (button1 != null && button1.size() > 0) {
                        myHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                button1.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        },1500);
                    }

                } else {
                    Log.d(TAG, TAG + "->没找到输入框");
                }
            }
        }*/

        /*
        {
            if (rootInActiveWindow != null) {
                List<AccessibilityNodeInfo> button2 = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.android.packageinstaller:id/launch_button");
                if (button2 != null && button2.size() > 0) {
                    button2.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
        }
        */

    }

    @Override
    public void onInterrupt() {

    }
}
