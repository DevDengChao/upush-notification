package dev.dengchao.ios;

import dev.dengchao.IOSNotification;
import org.jetbrains.annotations.NotNull;

public class IOSUnicast extends IOSNotification {
    public IOSUnicast(@NotNull String appkey, @NotNull String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue(PREDEFINED_KEY_APP_KEY, appkey);
        this.setPredefinedKeyValue(PREDEFINED_KEY_TYPE, PREDEFINED_VALUE_TYPE_UNICAST);
    }

    public void setDeviceToken(@NotNull String token) throws Exception {
        setPredefinedKeyValue(PREDEFINED_KEY_DEVICE_TOKENS, token);
    }
}
