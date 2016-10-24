// CalAidlInterface.aidl
package com.treasure_ct.android.xt.components.service.remoteservicedemo;

// Declare any non-default types here with import statements

interface CalAidlInterface {
    //定义AIDL中提供的接口，相当对于告诉其他应用程序，可以调用的方法
    /**
    * 对外定义一个add方法，支持两个参数，一个返回值
    */
    int add(int a,int b);
}
