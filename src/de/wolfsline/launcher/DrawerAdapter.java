package de.wolfsline.launcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter{
	
	private Context mContext;
	private Pac[] pacsForAdapter;
	
	public DrawerAdapter(Context context, Pac pacs[]){
		this.mContext = context;
		this.pacsForAdapter = pacs;
	}

	@Override
	public int getCount() {
		return pacsForAdapter.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	static class ViewHolder{
		TextView text;
		ImageView icon;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null){
			convertView = li.inflate(R.layout.drawer_item, null);
			
			viewHolder = new ViewHolder();
			viewHolder.text = (TextView) convertView.findViewById(R.id.icon_text);
			viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon_image);
			
			convertView.setTag(viewHolder);
		}
		else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.text.setText(pacsForAdapter[position].label);
		viewHolder.icon.setImageDrawable(pacsForAdapter[position].icon);

		return convertView;
	}

}
