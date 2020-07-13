package dev.dengchao.android;

import dev.dengchao.AndroidNotification;
import org.jetbrains.annotations.NotNull;

public class AndroidFilecast extends AndroidNotification {

    public AndroidFilecast(@NotNull String appkey, @NotNull String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue(PREDEFINED_KEY_APP_KEY, appkey);
        this.setPredefinedKeyValue(PREDEFINED_KEY_TYPE, PREDEFINED_VALUE_TYPE_FILECAST);
    }

    public void setFileId(@NotNull String fileId) throws Exception {
        setPredefinedKeyValue(PREDEFINED_KEY_FILE_ID, fileId);
    }
}
