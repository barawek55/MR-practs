package com.example.pr4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public final class AlertDispatcher {

    private static final String CHANNEL_TAG = "daily-routine-alerts";
    private static final CharSequence CHANNEL_LABEL = "Распорядок дня";
    private static final String CHANNEL_SUMMARY = "Уведомления для экранов";

    private final Context context;
    private final NotificationManager notifier;

    public AlertDispatcher(Context context) {
        this.context = context.getApplicationContext();
        this.notifier = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        ensureChannelRegistered();
    }

    public void post(String heading, String body, int slot) {
        if (!canPostNotifications()) return;

        NotificationCompat.Builder spec = new NotificationCompat.Builder(context, CHANNEL_TAG)
                .setSmallIcon(R.drawable.ic_baseline_blender_24)
                .setContentTitle(heading)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        notifier.notify(slot, spec.build());
    }

    private boolean canPostNotifications() {
        return Build.VERSION.SDK_INT < 33
                || context.checkSelfPermission("android.permission.POST_NOTIFICATIONS")
                == PackageManager.PERMISSION_GRANTED;
    }

    private void ensureChannelRegistered() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return;

        NotificationChannel ch = new NotificationChannel(
                CHANNEL_TAG, CHANNEL_LABEL, NotificationManager.IMPORTANCE_DEFAULT);
        ch.setDescription(CHANNEL_SUMMARY);
        notifier.createNotificationChannel(ch);
    }
}