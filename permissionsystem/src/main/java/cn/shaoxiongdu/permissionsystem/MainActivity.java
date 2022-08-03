package cn.shaoxiongdu.permissionsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    public static final int REQUEST_LOCATION_CODE = 0x1;
    public static final int REQUEST_RECORD_AUDIO_CODE = 0x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadPosition();
        loadMicrophone();
    }

    private void loadMicrophone() {
        boolean permission = checkPermission(Manifest.permission.RECORD_AUDIO);
        if (!permission) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_CODE);
            }
        }
    }

    private void loadPosition() {
        boolean permission = checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
        if (!permission) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION_CODE);
            }
        }
    }

    /**
     * 授权回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "已成功获取到定位权限", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "您拒绝了定位授权", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_RECORD_AUDIO_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "已成功获取到麦克风权限", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "您拒绝了麦克风授权", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 检查权限
     * @param permission
     * @return
     */
    private boolean checkPermission(String permission){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return PackageManager.PERMISSION_GRANTED == checkSelfPermission(permission);
        }
        return false;
    }
}