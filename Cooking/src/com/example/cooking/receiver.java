package com.example.cooking;


import android.R.menu;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;

public class receiver extends BroadcastReceiver{
	int i=0;
	String x="";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		i=intent.getIntExtra("number", 0);
		x=intent.getStringExtra("set");
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context,Movie.class),0);
		intent.putExtra("number",i);
		intent.putExtra("set", x);
		Notification notification = new Notification.Builder(context).setSmallIcon(R.drawable.ic_launcher).setTicker("New message!").setContentTitle("Attention!").setContentText("time up!").setContentIntent(pendingIntent).setNumber(1).getNotification();
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE); 
		notificationManager.notify(1,notification);
	}

}
