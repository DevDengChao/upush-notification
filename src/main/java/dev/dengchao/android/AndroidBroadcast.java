package dev.dengchao.android;

import dev.dengchao.AndroidNotification;
import org.jetbrains.annotations.NotNull;

public class AndroidBroadcast extends AndroidNotification {

    public AndroidBroadcast(@NotNull String appkey, @NotNull String appMasterSecret) {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue(PREDEFINED_KEY_APP_KEY, appkey);
        this.setPredefinedKeyValue(PREDEFINED_KEY_TYPE, PREDEFINED_VALUE_TYPE_BROADCAST);
    }
}
