package dev.dengchao.ios;

import dev.dengchao.IOSNotification;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class IOSGroupcast extends IOSNotification {
    public IOSGroupcast(@NotNull String appkey, @NotNull String appMasterSecret) {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue(PREDEFINED_KEY_APP_KEY, appkey);
        this.setPredefinedKeyValue(PREDEFINED_KEY_TYPE, PREDEFINED_VALUE_TYPE_GROUPCAST);
    }

    public void setFilter(JSONObject filter) {
        setPredefinedKeyValue(PREDEFINED_KEY_FILTER, filter);
    }
}
