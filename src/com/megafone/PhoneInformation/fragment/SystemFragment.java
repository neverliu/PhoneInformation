package com.megafone.PhoneInformation.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import com.megafone.PhoneInformation.adapter.BaseFragment;
import com.megafone.PhoneInformation.tools.BatteryTools;
import com.megafone.PhoneInformation.tools.CPUTools;
import com.megafone.PhoneInformation.tools.SystemTools;

public class SystemFragment extends BaseFragment {
	private static final String TAG = "SystemFragment";
	List<Map<String, Object>> list;
	private SystemTools st;
	private CPUTools ct;
	private BatteryTools bt;
	public SystemFragment() {
		st = new SystemTools();
		ct = new CPUTools();
		bt = new BatteryTools();
	}
	@Override
	public List getData() {
		list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			switch (i) {
			case 0:
				map.put("title", "Model");
					map.put("info", st.getInfo(getContext(), 3));
				list.add(map);
				break;
			case 1:
				map.put("title", "Manufacturer");
				map.put("info", st.getInfo(getContext(), 4)+"("+
						st.getInfo(getContext(), 7)+")");
				list.add(map);
				break;
			case 2:
				map.put("title", "IMSI&IMEI");
				map.put("info", st.getInfo(getContext(), 2)+"&"+st.getInfo(getContext(), 1));
				list.add(map);
				break;
			case 3:
				map.put("title", "PRODUCT");
				map.put("info", st.getInfo(getContext(), 6));
				list.add(map);
				break;
			case 4:
				map.put("title", "Hardware");
				map.put("info", ct.getFieldFromCpuinfo("Hardware","/proc/cpuinfo")+"("+
						st.getInfo(getContext(), 5)+")");
				list.add(map);
				break;
			case 5:
				map.put("title", "Android Version");
				map.put("info", st.getInfo(getContext(), 8)+"("+
						st.getInfo(getContext(), 9)+")");
				list.add(map);	
				break;
			case 6:
				map.put("title", "BOARD");
				map.put("info", st.getInfo(getContext(), 10));
				list.add(map);
				break;
			case 7:
				map.put("title", "API");
				map.put("info", st.getInfo(getContext(), 11));
				list.add(map);
				break;
			case 8:
				map.put("title", "HOST");
				map.put("info", st.getInfo(getContext(), 12));
				list.add(map);
				break;
			case 9:
				map.put("title", "Kernel Version");
				map.put("info", st.getKernelVersion());
				list.add(map);
				break;
			case 11:
				DisplayMetrics dm2 = getResources().getDisplayMetrics();
				map.put("title", "Screen Resolution(Pixels)");
				map.put("info", dm2.widthPixels+" x "+dm2.heightPixels);
				list.add(map);
				break;
			case 12:
				map.put("title", "Total RAM");
				map.put("info", ct.getUnitSize(ct.getFieldFromCpuinfo("MemTotal","/proc/meminfo")
						.substring(0, ct.getFieldFromCpuinfo("MemTotal","/proc/meminfo").length()-3)));
				list.add(map);
				break;
			case 13:
			map.put("title", "Available RAM");
			map.put("info", st.getAvailMemory(context));
				list.add(map);
				break;
			case 14:
				map.put("title", "externalTotal");
				map.put("info", st.getStorage(context,1));
				list.add(map);
				break;
			case 15:
				map.put("title", "externalAvailSize");
				map.put("info", st.getStorage(context,2));
				list.add(map);
				break;
			case 16:
				map.put("title", "memoryModel");
				map.put("info", bt.getTPFreq(bt.getMemoryModel()));
				list.add(map);
				break;
			}
		}
		return list;
	}

	@Override
	public void initView(TextView title, TextView info) {
		title.setWidth(170);
	}
	@Override
	public List<String[]> initListView() {
		// TODO Auto-generated method stub
		return null;
	}

}
