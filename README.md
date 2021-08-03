# UserPricacyApiCheck
基于xposed的app合规检测模块，可通过填入包名检测多个app，可通过toast提示或者通过 logcat中 以HookModule关键字过滤知悉调用


![image](https://user-images.githubusercontent.com/8263357/127980676-793d18d2-af11-4886-bebe-628dd119fa98.png)


![image](https://user-images.githubusercontent.com/8263357/127988304-d86b4eea-5185-4a2e-977a-c3d2d60ee0b5.png)


#已经处理的检测方法如下（如有遗漏欢迎提issue补充）
##### 账户
* android.accounts.AccountManager#getAccounts 
* android.accounts.AccountManager#getAccountsByType 
##### 安装列表
* android.app.ActivityManager#getRunningAppProcesses 
* android.app.ApplicationPackageManager#getInstalledApplications 
* android.app.ApplicationPackageManager#getInstalledPackages 
* android.app.ApplicationPackageManager#getInstalledModules 
##### 蓝牙信息
* android.bluetooth.le.BluetoothLeScanner#startScan 
##### 设备序列号
* android.os.Build#getSerial 
##### 位置信息
* android.location.LocationManager#getLastKnownLocation 
* android.location.LocationManager#getLastLocation 
* android.location.LocationManager#getCurrentLocation 
* android.location.LocationManager#getCurrentLocation 
##### 系统信息（android_id）
* android.provider.Settings$Secure#getString 
* android.provider.Settings$System#getString 
* android.os.SystemProperties#get 
* android.os.SystemProperties#getInt 
* android.os.SystemProperties#getLong 
* android.os.SystemProperties#getBoolean 
##### 重力感应
* android.hardware.SensorManager#registerListener 
##### 流量卡相关id
* android.telephony.SubscriptionManager#getDefaultDataSubscriptionId 
* android.telephony.SubscriptionManager#getDefaultDataPhoneId 
* android.telephony.SubscriptionManager#getDefaultDataSubId 
##### imei，imsi,meid,sim卡信息
* android.telephony.TelephonyManager#getImei 
* android.telephony.TelephonyManager#getImei 
* android.telephony.TelephonyManager#getDeviceId 
* android.telephony.TelephonyManager#getDeviceId 
* android.telephony.TelephonyManager#getCellLocation 
* android.telephony.TelephonyManager#getAllCellInfo 
* android.telephony.TelephonyManager#getSubscriberId 
* android.telephony.TelephonyManager#getSubscriberId 
* android.telephony.TelephonyManager#getSimSerialNumber 
* android.telephony.TelephonyManager#getSimSerialNumber 
* android.telephony.TelephonyManager#getNetworkOperatorForPhone 
* android.telephony.TelephonyManager#getSimCountryIsoForPhone 
* android.telephony.TelephonyManager#getSimOperatorNameForPhone 
##### MAC相关
* java.net.NetworkInterface#getHardwareAddress 
* android.net.wifi.WifiInfo#getMacAddress 
* android.net.wifi.WifiInfo#getIpAddress 
* android.net.wifi.WifiInfo#getSSID 
* android.net.wifi.WifiInfo#getBSSID 
* android.net.wifi.WifiManager#getScanResults 
* android.net.wifi.WifiManager#getConnectionInfo
