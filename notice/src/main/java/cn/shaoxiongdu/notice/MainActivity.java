package cn.shaoxiongdu.notice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static int noticeCount = 0;

    private NotificationManager manager;
    private NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "low");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    /**
     * 发送通知
     *
     * @param notice
     */
    private void notify(Notification notice) {
        manager.notify(noticeCount++, notice);
    }

    /**
     * 关于不同渠道的通知权限 以下仅为默认情况 用户可自定义修改。
     */
    private void init() {
        manager = getSystemService(NotificationManager.class);
        // 有声音 有横幅
        manager.createNotificationChannel(new NotificationChannel("high", "high", NotificationManager.IMPORTANCE_HIGH));
        // 有声音
        manager.createNotificationChannel(new NotificationChannel("default", "default", NotificationManager.IMPORTANCE_DEFAULT));
        // 只有通知
        manager.createNotificationChannel(new NotificationChannel("low", "low", NotificationManager.IMPORTANCE_LOW));
        // 没有通知
        manager.createNotificationChannel(new NotificationChannel("min", "min", NotificationManager.IMPORTANCE_MIN));

    }

    public void highNotice(View view) {
        Notification notification = new NotificationCompat.Builder(this, "high")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentText("这是高优先级通知这是高优先级通知这是高优先级通知...")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground))
                .setContentTitle("这是高优先级通知标题").build();
        notify(notification);
    }

    public void defaultNotice(View view) {
        Notification notification = new NotificationCompat.Builder(this, "default")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentText("这是默认优先级正文这是默认优先级正文...")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground))
                .setContentTitle("这是默认优先级标题").build();
        notify(notification);
    }

    public void lowNotice(View view) {
        Notification notification = new NotificationCompat.Builder(this, "low")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentText("这是低优先级正文这是低优先级正文...")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground))
                .setContentTitle("这是低优先级标题").build();
        notify(notification);
    }

    public void minNotice(View view) {
        Notification notification = new NotificationCompat.Builder(this, "min")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentText("这是最低优先级正文这是最低优先级...")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground))
                .setContentTitle("这是最低优先级标题").build();
        notify(notification);
    }

    /**
     * 长文本可单击通知
     *
     * @param view
     */
    public void longTextAndClickable(View view) {
        // create pendingIntent
        Intent intent = new Intent(this, OtherActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this, "high")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本长文本"))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground))
                .setContentTitle("这是长文本通知的标题").build();
        notify(notification);
    }

    ;

    /**
     * 进度条通知
     *
     * @param view
     */
    public void progressBarNotice(View view) {
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setProgress(100, 0, false);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground));
        builder.setContentTitle("下载中(0%)");
        manager.notify(10, builder.build());
        new Thread(() -> {
            Random random = new Random();
            for (int i = 1; i < 100; ) {
                i += random.nextInt(10);
                if (i > 100) i = 100;
                updateProgress(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 更新进度
     *
     * @param progress
     */
    private void updateProgress(int progress) {
        if (progress == 100) {
            builder.setProgress(0, 0, false);
            builder.setContentTitle("下载完成");
        } else {
            builder.setProgress(100, progress, false);
            builder.setContentTitle("下载中(" + progress + "%)");
        }
        manager.notify(10, builder.build());
    }


}