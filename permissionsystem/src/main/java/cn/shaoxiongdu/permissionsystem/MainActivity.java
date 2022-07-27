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
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    public static final int REQUEST_LOCATION_CODE = 0x1;
    private LocationManager locationManager;
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadWebView();
        loadPosition();
    }

    private void loadPosition() {
        checkPermission();
        getLocationManager();
        showPosition();
    }

    private void showPosition() {
        TextView positionTv = findViewById(R.id.positionTv);
        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        positionTv.setText("经度:" + latitude + "\n" + "纬度: " + longitude);
    }

    /**
     *  检查权限
     */
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int res = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
            if (PackageManager.PERMISSION_GRANTED != res) {
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
                    Toast.makeText(this, "您拒绝了授权", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void loadWebView() {
        WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://baidu.com");
    }

    public void getLocationManager() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            locationManager = getSystemService(LocationManager.class);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                //跳转到手机打开GPS页面
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                //设置完成后返回原来的界面
                startActivity(intent);
            }
        }
    }
}