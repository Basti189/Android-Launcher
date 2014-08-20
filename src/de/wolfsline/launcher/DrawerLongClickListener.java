package de.wolfsline.launcher;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class DrawerLongClickListener implements OnItemLongClickListener{

	private Context mContext;
	private Pac[] pacsForAdapter;
	private PackageManager pmForListener;
	
	public DrawerLongClickListener(Context context, Pac pacs[], PackageManager pm){
		this.mContext = context;
		this.pacsForAdapter = pacs;
		this.pmForListener = pm;
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(mContext, "Rechtsklick -> " + pacsForAdapter[position].label, Toast.LENGTH_SHORT).show();
		return true;
	}

}
