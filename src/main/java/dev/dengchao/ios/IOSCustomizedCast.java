package dev.dengchao.ios;

import dev.dengchao.IOSNotification;
import org.jetbrains.annotations.NotNull;

public class IOSCustomizedCast extends IOSNotification {
    public IOSCustomizedCast(@NotNull String appkey, @NotNull String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue(PREDEFINED_KEY_APP_KEY, appkey);
        this.setPredefinedKeyValue(PREDEFINED_KEY_TYPE, PREDEFINED_VALUE_TYPE_CUSTOMIZED_CAST);
    }

    public void setAlias(@NotNull String alias, @NotNull String aliasType) throws Exception {
        setPredefinedKeyValue(PREDEFINED_KEY_ALIAS, alias);
        setPredefinedKeyValue(PREDEFINED_KEY_ALIAS_TYPE, aliasType);
    }

    public void setFileId(@NotNull String fileId, @NotNull String aliasType) throws Exception {
        setPredefinedKeyValue(PREDEFINED_KEY_FILE_ID, fileId);
        setPredefinedKeyValue(PREDEFINED_KEY_ALIAS_TYPE, aliasType);
    }

}
