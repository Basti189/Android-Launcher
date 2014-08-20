package de.wolfsline.launcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PacReceiver extends BroadcastReceiver{

	private MainActivity mMain;
	
	public PacReceiver(MainActivity main) {
		this.mMain = main;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		mMain.setPacs();
	}

}
