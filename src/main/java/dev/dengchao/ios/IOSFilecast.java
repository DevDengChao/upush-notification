package dev.dengchao.ios;

import dev.dengchao.IOSNotification;
import org.jetbrains.annotations.NotNull;

public class IOSFilecast extends IOSNotification {
    public IOSFilecast(@NotNull String appkey, @NotNull String appMasterSecret) {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue(PREDEFINED_KEY_APP_KEY, appkey);
        this.setPredefinedKeyValue(PREDEFINED_KEY_TYPE, PREDEFINED_VALUE_TYPE_FILECAST);
    }

    public void setFileId(@NotNull String fileId) {
        setPredefinedKeyValue(PREDEFINED_KEY_FILE_ID, fileId);
    }
}
