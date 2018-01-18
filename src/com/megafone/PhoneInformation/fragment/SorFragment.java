package com.megafone.PhoneInformation.fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import android.os.Build;
import android.widget.TextView;

import com.megafone.PhoneInformation.adapter.BaseFragment;
import com.megafone.PhoneInformation.tools.CPUTools;

public class SorFragment extends BaseFragment {
	public SorFragment() {
		ct = new CPUTools();
	}

	private static final String TAG = "SorFragment";
	List<Map<String, Object>> list;
	private CPUTools ct;


	@Override
	public List getData() {
		list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			switch (i) {
			case 0:
				map.put("title", "cpu");
				try{
					map.put("info", ct.getFieldFromCpuinfo("Hardware","/proc/cpuinfo"));
				}catch (Exception e) {
					e.printStackTrace();
				}
				list.add(map);
				break;
			case 1:
				map.put("title", "Cores");
				map.put("info", ""+getNumCores());
				list.add(map);
				break;
			case 2:
				map.put("title", "Architecture");
				map.put("info", ""+ct.getFieldFromCpuinfo("model name","/proc/cpuinfo"));
				list.add(map);
				break;
			case 3:
				map.put("title", "MaxCpuFreq");
				map.put("info", ""+ct.getUnitSize(ct.getMaxCpuFreq()));
				list.add(map);
				break;
			case 4:
				map.put("title", "MinCpuFreq");
				map.put("info", ""+ct.getUnitSize(ct.getMinCpuFreq()));
				list.add(map);
				break;
			case 5:
				map.put("title", "CPU revision");
				map.put("info", ct.getFieldFromCpuinfo("CPU revision","/proc/cpuinfo"));
				list.add(map);
				break;
			}
			
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	private int getNumCores() {
		// Private Class to display only CPU devices in the directory listing
		class CpuFilter implements FileFilter {
			@Override
			public boolean accept(File pathname) {
				// Check if filename is "cpu", followed by a single digit number
				if (Pattern.matches("cpu[0-9]", pathname.getName())) {
					return true;
				}
				return false;
			}
		}
		try {
			File dir = new File("/sys/devices/system/cpu/");
			File[] files = dir.listFiles(new CpuFilter());
			return files.length;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	public String[] getVersion() {
		String[] version = { "null", "null", "null", "null" };
		String str1 = "/proc/version";
		String str2;
		String[] arrayOfString;
		try {
			FileReader localFileReader = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(
					localFileReader, 8192);
			str2 = localBufferedReader.readLine();
			arrayOfString = str2.split("\\s+");
			version[0] = arrayOfString[2];// KernelVersion
			localBufferedReader.close();
		} catch (IOException e) {
		}
		version[1] = Build.VERSION.RELEASE;// firmware version
		version[2] = Build.MODEL;// model
		version[3] = Build.DISPLAY;// system version
		return version;
	}




	public  String getCurCpuFreq() {
		String result = "N/A";
		try {
			FileReader fr = new FileReader(
					"/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
			BufferedReader br = new BufferedReader(fr);
			String text = br.readLine();
			result = text.trim();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void initView(TextView title, TextView info) {
		title.setWidth(135);
	}

	@Override
	public List<String[]> initListView() {
		// TODO Auto-generated method stub
		return null;
	}

}
