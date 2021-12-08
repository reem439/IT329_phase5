package forever.garden.model;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import forever.garden.R;
import forever.garden.SplashActivity;

public class Utils {


    public static void buildNotification(Context context, String tag) {

        NotificationChannel mChannel = null;
        NotificationManager mNotifManager = null;

        if (mNotifManager == null) {
            mNotifManager = (NotificationManager) context.getSystemService
                    (Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder builder;
            Intent intent = new Intent(context, SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            if (mChannel == null) {
                mChannel = new NotificationChannel
                        ("0", "Alarm", importance);
                mChannel.setDescription(tag);
                mChannel.enableVibration(true);
                mNotifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(context, "0");

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 1251, intent, PendingIntent.FLAG_ONE_SHOT);
            builder.setContentTitle(context.getResources().getString(R.string.app_name))
                    .setSmallIcon(getNotificationIcon()) // required
                    .setContentText("Don't Forget " + tag)  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setLargeIcon(BitmapFactory.decodeResource
                            (context.getResources(), R.mipmap.ic_launcher))
                    .setContentIntent(pendingIntent)
                    .setSound(RingtoneManager.getDefaultUri
                            (RingtoneManager.TYPE_NOTIFICATION));
            Notification notification = builder.build();
            mNotifManager.notify(0, notification);
        } else {

            Intent intent = new Intent(context, SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = null;

            pendingIntent = PendingIntent.getActivity(context, 1251, intent, PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                    .setContentTitle(context.getResources().getString(R.string.app_name))
                    .setContentText("Don't Forget " + tag)
                    .setAutoCancel(true)
                    .setColor(ContextCompat.getColor(context, R.color.primary))
                    .setSound(defaultSoundUri)
                    .setSmallIcon(getNotificationIcon())
                    .setContentIntent(pendingIntent)
                    .setStyle(new NotificationCompat
                            .BigTextStyle()
                            .setBigContentTitle(context.getResources().getString(R.string.app_name))
                            .bigText("Don't Forget " + tag));

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1251, notificationBuilder.build());
        }
    }

    private static int getNotificationIcon() {
        boolean useWhiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.mipmap.ic_launcher : R.mipmap.ic_launcher;
    }

    public static int getTimeInMints(String time) {

        String hourInString = time.substring(0, 2);
        String mintInString = time.substring(3, 5);

        int hour = Integer.parseInt(hourInString);
        int mints = Integer.parseInt(mintInString);

        int hourToMints = hour * 60;

        int totalMints = hourToMints + mints;

        return totalMints;
    }
}
