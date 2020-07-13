package dev.dengchao.android;

import dev.dengchao.AndroidNotification;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class AndroidGroupcast extends AndroidNotification {

    public AndroidGroupcast(@NotNull String appkey, @NotNull String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue(PREDEFINED_KEY_APP_KEY, appkey);
        this.setPredefinedKeyValue(PREDEFINED_KEY_TYPE, PREDEFINED_VALUE_TYPE_GROUPCAST);
    }

    public void setFilter(JSONObject filter) throws Exception {
        setPredefinedKeyValue(PREDEFINED_KEY_FILTER, filter);
    }
}
