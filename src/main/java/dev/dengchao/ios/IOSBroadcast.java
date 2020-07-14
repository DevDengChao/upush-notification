package dev.dengchao.ios;

import dev.dengchao.IOSNotification;
import org.jetbrains.annotations.NotNull;

public class IOSBroadcast extends IOSNotification {

    public IOSBroadcast(@NotNull String appkey, @NotNull String appMasterSecret) {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue(PREDEFINED_KEY_APP_KEY, appkey);
        setPredefinedKeyValue(PREDEFINED_KEY_TYPE, PREDEFINED_VALUE_TYPE_BROADCAST);
    }
}
