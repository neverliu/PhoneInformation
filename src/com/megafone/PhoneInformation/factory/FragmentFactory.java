package com.megafone.PhoneInformation.factory;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;

import com.megafone.PhoneInformation.adapter.BaseFragment;
import com.megafone.PhoneInformation.fragment.AboutFragment;
import com.megafone.PhoneInformation.fragment.BatteryFragment;
import com.megafone.PhoneInformation.fragment.DeviceFragment;
import com.megafone.PhoneInformation.fragment.SeneorsFragment;
import com.megafone.PhoneInformation.fragment.SorFragment;
import com.megafone.PhoneInformation.fragment.SystemFragment;
import com.megafone.PhoneInformation.fragment.ThermalFragment;

public class FragmentFactory {
	public static final int FRAGMENT_SOR = 0;
//	public static final int FRAGMENT_DEVICE = 1;
	public static final int FRAGMENT_SYSTEM = 1;
	public static final int FRAGMENT_BATTERY = 2;
//	public static final int FRAGMENT_SENEORS = 4;
//	public static final int FRAGMENT_THERMAL = 5;
	public static final int FRAGMENT_ABOUT = 3;
	private static final String TAG = "FragmentFactory";

	static	SparseArrayCompat<BaseFragment> cachesFragment = new SparseArrayCompat<BaseFragment>();
	public static BaseFragment getFragment(int position, Context context) {
		BaseFragment fragment = null;
		BaseFragment TmpFargment = cachesFragment.get(position);
		if(TmpFargment != null){
			fragment = TmpFargment;
			return fragment;
		}
		switch (position) {
		case FRAGMENT_SOR:
			fragment = new SorFragment();
			break;
//		case FRAGMENT_DEVICE:
//			fragment = new DeviceFragment();
//			break;
		case FRAGMENT_SYSTEM:
			fragment = new SystemFragment();
			break;
		case FRAGMENT_BATTERY:
			fragment = new BatteryFragment();
			break;
//		case FRAGMENT_THERMAL:
//			fragment = new ThermalFragment();
//			break;
//		case FRAGMENT_SENEORS:
//			fragment = new SeneorsFragment();
//			break;
		case FRAGMENT_ABOUT:
			fragment = new AboutFragment();
			break;
		default:
			break;
		}
		Log.d(TAG, "liuhao fragment:"+position);
		cachesFragment.put(position, fragment);
		return fragment;

	}
}
