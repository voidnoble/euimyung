package com.euimyung.manual;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReciever extends BroadcastReceiver {

	public BootReciever() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent cIntent = new Intent(context, MainActivity.class);
		cIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//	    context.startActivity(cIntent);
	    context.startService(cIntent);
	}
}
