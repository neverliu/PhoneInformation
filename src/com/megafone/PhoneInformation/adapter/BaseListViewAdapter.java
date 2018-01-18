package com.megafone.PhoneInformation.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.megafone.PhoneInformation.R;
import com.megafone.PhoneInformation.listViewAnmi.ListViewLongClick;

public abstract class BaseListViewAdapter extends BaseAdapter {

	private static final String TAG = "BaseListViewAdapter";
	private List<Map<String, Object>> data;
	private LayoutInflater layoutInflater;
	private Context context;
	private View mConvertView = null;
	public Tag tag = null;
	private List<String[]> subtitleLine = null;
	private ListViewLongClick lvlk;

	public BaseListViewAdapter(Context context, List<Map<String, Object>> data,
			List<String[]> subtitleLine) {
		this.context = context;
		this.data = data;
		this.layoutInflater = LayoutInflater.from(context);
		this.subtitleLine = subtitleLine;
	}

	// insertion subtitle

	@Override
	public int getCount() {
		if (data != null) {
			Log.d(TAG, "getCount:" + data.size());
			return data.size();
		}
		return 0;

	}

	@Override
	public Object getItem(int position) {
		if (data.get(position) != null)
			return data.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			mConvertView = convertView;
			tag = new Tag();
			convertView = layoutInflater.inflate(R.layout.listview, null);
			tag.title = (TextView) convertView.findViewById(R.id.title);
			tag.info = (TextView) convertView.findViewById(R.id.info);
			convertView.setTag(tag);
		} else {
			tag = (Tag) convertView.getTag();
		}
		initView(tag.title, tag.info);
		initItemAndData(position, convertView);
		return convertView;
	}

	private void initItemAndData(int position, View convertView) {
		lvlk = new ListViewLongClick(context, position, convertView);
		if (subtitleLine != null) {
			for (int i = 0; i < subtitleLine.size(); i++) {
				if (position == Integer.parseInt(subtitleLine.get(i)[0])) {
					tag.title.setText((String) data.get(position).get(
							"titleLine"));
					LinearLayout listViewLL = (LinearLayout) convertView
							.findViewById(R.id.ListViewLL);
					tag.title.setTextSize(20);
					tag.title.setWidth(150);
					tag.title.setTextColor(Color.BLUE);
					tag.info.setText("");
					return;
				}
			}
			tag.title.setText((String) data.get(position).get("title"));
			tag.info.setText((String) data.get(position).get("info"));
			
			convertView.setOnLongClickListener(lvlk);
		} else {
			tag.title.setText((String) data.get(position).get("title"));
			tag.info.setText((String) data.get(position).get("info"));
			convertView.setOnLongClickListener(lvlk);
		}
	}

	public final class Tag {
		public TextView title;
		public TextView info;
	}

	public abstract void initView(TextView title, TextView info);

	public abstract List<String[]> initListView();
}
