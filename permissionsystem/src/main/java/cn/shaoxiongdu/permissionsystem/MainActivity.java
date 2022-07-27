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
    public static final int REQUEST_CALL_CODE = 0x2;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadWebView();
        loadPosition();
    }

    private void loadPosition() {
        boolean permission = checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permission){
            getLocationManager();
            showPosition();
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_LOCATION_CODE);
            }
        }
    }

    private void showPosition() {
        TextView positionTv = findViewById(R.id.positionTv);
        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        positionTv.setText("经度:" + latitude + "\n" + "纬度: " + longitude);
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
                    getLocationManager();
                    showPosition();
                } else {
                    Toast.makeText(this, "您拒绝了授权", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CALL_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call(null);
                } else {
                    Toast.makeText(this, "您拒绝了拨号授权", Toast.LENGTH_SHORT).show();
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

    public void call(View view) {
        EditText phoneNumberEt = findViewById(R.id.phone_number_et);
        boolean checkPermission = checkPermission(Manifest.permission.CALL_PHONE);
        if (checkPermission){
            String phoneNumber = phoneNumberEt.getText().toString();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL_CODE);
            }
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