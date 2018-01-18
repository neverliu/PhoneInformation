package com.megafone.PhoneInformation.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

public class CPUTools {
	private static final String TAG = "CPUTools";
	java.text.DecimalFormat df2 = new java.text.DecimalFormat("#.00");
	java.text.DecimalFormat df1 = new java.text.DecimalFormat("#.0");

	public String getMaxCpuFreq() {
		String result = "";
		ProcessBuilder cmd;
		try {
			String[] args = { "/system/bin/cat",
					"/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" };
			cmd = new ProcessBuilder(args);
			Process process = cmd.start();
			InputStream in = process.getInputStream();
			byte[] re = new byte[24];
			while (in.read(re) != -1) {
				result = result + new String(re);
			}
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			result = "N/A";
		}
		return (result.trim());
	}

	public String getMinCpuFreq() {
		String result = "";
		ProcessBuilder cmd;
		try {
			String[] args = { "/system/bin/cat",
					"/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq" };
			cmd = new ProcessBuilder(args);
			Process process = cmd.start();
			InputStream in = process.getInputStream();
			byte[] re = new byte[24];
			while (in.read(re) != -1) {
				result = result + new String(re);
			}
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			result = "N/A";
		}
		return (result.trim());
	}

	public String getCurCpuFreq() {
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

	public String getCPULoad() {
		java.text.DecimalFormat df2 = new java.text.DecimalFormat("#.0");
		Double max = Double.valueOf(getMaxCpuFreq());
		Double cur = Double.valueOf(getCurCpuFreq());
		Log.d(TAG, "liuhao getCurCpuFreq:" + getCurCpuFreq()
				+ " getMaxCpuFreq:" + getMaxCpuFreq());
		return df2.format(cur / max) + " %";
	}

	public String getUnitSize(String aaa) {
		java.text.DecimalFormat df2 = new java.text.DecimalFormat("#.00");
		java.text.DecimalFormat df1 = new java.text.DecimalFormat("#.0");
		if (aaa != null) {
			Double aa = Double.valueOf(aaa);
			Double ghz = aa / (1024.0 * 1024.0);
			if (ghz > 1) {
				return df2.format(ghz) + " GHz";
			} else {
				return df1.format(aa / 1024.0) + " MHz";
			}
		}
		return null;
	}

	public String getFieldFromCpuinfo(String field, String address) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(address));
			Pattern p = Pattern.compile(field + "\\s*:\\s*(.*)");
			String line;
			while ((line = br.readLine()) != null) {
				Matcher m = p.matcher(line);
				if (m.matches()) {
					return m.group(1);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
