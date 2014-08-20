package de.wolfsline.launcher;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private DrawerAdapter drawerAdapter;
	private GridView drawerGrid;
	
	private Pac[] pacs;
	private PackageManager pm;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		drawerGrid = (GridView) findViewById(R.id.content);
		pm = getPackageManager();
		
		setPacs();
		
		RelativeLayout rLayout = (RelativeLayout) findViewById(R.id.mainLayout);
		rLayout.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()){
			@Override
			public void onSwipeTop() {
		        Log.d("TOUCH", "HOCH");
		    }
			@Override
		    public void onSwipeRight() {
				Log.d("TOUCH", "RECHTS");
		    }
			@Override
		    public void onSwipeLeft() {
				Log.d("TOUCH", "LEFT");
		    }
			@Override
		    public void onSwipeBottom() {
				Log.d("TOUCH", "RUNTER");
		    }
			@Override
		    public void onPoint() {
				Log.d("TOUCH", "TOUCH");
		    }
			@Override
			public void onDoublePoint(){
				Log.d("TOUCH", "DOUBLE");
				Toast.makeText(getApplicationContext(), "Konextmenü öffnen", Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onLongPoint(){
				Log.d("TOUCH", "LONG");
			}
			
		});
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_PACKAGE_ADDED);
		filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
		filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
		filter.addDataScheme("package");
		registerReceiver(new PacReceiver(this), filter);
	}
	
	public void setPacs(){
		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> pacsList = pm.queryIntentActivities(mainIntent, 0);
		pacs = new Pac[pacsList.size()];
		for(int i = 0 ; i < pacsList.size() ; i++){
			pacs[i] = new Pac();
			pacs[i].icon = pacsList.get(i).loadIcon(pm);
			pacs[i].name = pacsList.get(i).activityInfo.packageName;
			pacs[i].label = pacsList.get(i).loadLabel(pm).toString();
		}
		new SortApps().exchange_sort(pacs);
		
		drawerAdapter = new DrawerAdapter(this, pacs);
		drawerGrid.setAdapter(drawerAdapter);
		drawerGrid.setOnItemClickListener(new DrawerClickListener(this, pacs, pm));
		drawerGrid.setOnItemLongClickListener(new DrawerLongClickListener(this, pacs, pm));
	}
	
}
