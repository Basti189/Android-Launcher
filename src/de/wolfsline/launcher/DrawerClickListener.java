package de.wolfsline.launcher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class DrawerClickListener implements OnItemClickListener{

	private Context mContext;
	private Pac[] pacsForAdapter;
	private PackageManager pmForListener;
	
	public DrawerClickListener(Context context, Pac pacs[], PackageManager pm){
		this.mContext = context;
		this.pacsForAdapter = pacs;
		this.pmForListener = pm;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent launchIntent = pmForListener.getLaunchIntentForPackage(pacsForAdapter[position].name);
		mContext.startActivity(launchIntent);
	}

}
