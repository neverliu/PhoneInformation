package com.megafone.PhoneInformation.factory;

import com.megafone.PhoneInformation.manager.ThreadPoolProxy;

public class ThreadPoolFactory {
	static ThreadPoolProxy mNormalPool;
	static ThreadPoolProxy mDownLoadPool;
	public static ThreadPoolProxy getmNormalPool() {
		if(mNormalPool == null){
			synchronized (ThreadPoolFactory.class) {
				if(mNormalPool == null){
					mNormalPool = new ThreadPoolProxy(5, 5, 3000);
				}
				}
			}
		return mNormalPool;
	}
	public static ThreadPoolProxy getmDownLoadPool() {
		if(mDownLoadPool == null){
			synchronized (ThreadPoolFactory.class) {
				if(mDownLoadPool == null){
					mDownLoadPool = new ThreadPoolProxy(3, 3, 3000);
				}
				}
			}
		return mDownLoadPool;
	}
}
