package dev.dengchao.android;

import dev.dengchao.AndroidNotification;
import org.jetbrains.annotations.NotNull;

public class AndroidCustomizedcast extends AndroidNotification {

    public AndroidCustomizedcast(@NotNull String appkey, @NotNull String appMasterSecret) {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue(PREDEFINED_KEY_APP_KEY, appkey);
        this.setPredefinedKeyValue(PREDEFINED_KEY_TYPE, PREDEFINED_VALUE_TYPE_CUSTOMIZED_CAST);
    }

    public void setAlias(@NotNull String alias, @NotNull String aliasType) {
        setPredefinedKeyValue(PREDEFINED_KEY_ALIAS, alias);
        setPredefinedKeyValue(PREDEFINED_KEY_ALIAS_TYPE, aliasType);
    }

    public void setFileId(@NotNull String fileId, @NotNull String aliasType) {
        setPredefinedKeyValue(PREDEFINED_KEY_FILE_ID, fileId);
        setPredefinedKeyValue(PREDEFINED_KEY_ALIAS_TYPE, aliasType);
    }

}
