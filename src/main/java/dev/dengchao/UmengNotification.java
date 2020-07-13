package dev.dengchao;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashSet;

/**
 * The abstraction of umeng notification
 */
public abstract class UmengNotification {
    protected static final String PREDEFINED_KEY_APP_KEY = "appkey";
    protected static final String PREDEFINED_KEY_ALIAS = "alias";
    protected static final String PREDEFINED_KEY_ALIAS_TYPE = "alias_type";
    protected static final String PREDEFINED_KEY_FILE_ID = "file_id";
    protected static final String PREDEFINED_KEY_FILTER = "filter";
    protected static final String PREDEFINED_KEY_DEVICE_TOKENS = "device_tokens";
    protected static final String PREDEFINED_KEY_TYPE = "type";
    protected static final String PREDEFINED_VALUE_TYPE_FILECAST = "filecast";
    protected static final String PREDEFINED_VALUE_TYPE_GROUPCAST = "groupcast";
    protected static final String PREDEFINED_VALUE_TYPE_UNICAST = "unicast";
    protected static final String PREDEFINED_VALUE_TYPE_CUSTOMIZED_CAST = "customizedcast";
    protected static final String PREDEFINED_VALUE_TYPE_BROADCAST = "broadcast";
    /**
     * Keys can be set in the root level
     */
    protected static final HashSet<String> ROOT_KEYS = new HashSet<>(Arrays.asList(
            PREDEFINED_KEY_APP_KEY, "timestamp", PREDEFINED_KEY_TYPE, PREDEFINED_KEY_DEVICE_TOKENS,
            PREDEFINED_KEY_ALIAS, PREDEFINED_KEY_ALIAS_TYPE, PREDEFINED_KEY_FILE_ID, PREDEFINED_KEY_FILTER,
            "production_mode", "feedback", "description", "thirdparty_id", "mipush", "mi_activity", "channel_properties"));
    /**
     * Keys can be set in the policy level
     */
    protected static final HashSet<String> POLICY_KEYS = new HashSet<>(Arrays.asList("start_time", "expire_time", "max_send_num"));
    /**
     * This JSONObject is used for constructing the whole request string.
     */
    protected final JSONObject rootJson = new JSONObject();
    /**
     * The app master secret
     */
    protected String appMasterSecret;

    /**
     * Set predefined keys in the rootJson, for extra keys(Android) or customized keys(IOS) please
     * refer to corresponding methods in the subclass.
     */
    public abstract boolean setPredefinedKeyValue(@NotNull String key, Object value) throws Exception;

    public String getPostBody() {
        return rootJson.toString();
    }

    protected final String getAppMasterSecret() {
        return appMasterSecret;
    }

    public void setAppMasterSecret(@NotNull String secret) {
        appMasterSecret = secret;
    }

    protected void setProductionMode(Boolean prod) throws Exception {
        setPredefinedKeyValue("production_mode", prod.toString());
    }

    /**
     * 正式模式
     */
    public void setProductionMode() throws Exception {
        setProductionMode(true);
    }

    /**
     * 测试模式
     */
    public void setTestMode() throws Exception {
        setProductionMode(false);
    }

    /**
     * 发送消息描述，建议填写。
     */
    public void setDescription(@NotNull String description) throws Exception {
        setPredefinedKeyValue("description", description);
    }

    /**
     * 定时发送时间，若不填写表示立即发送。格式: "YYYY-MM-DD hh:mm:ss"。
     */
    public void setStartTime(@NotNull String startTime) throws Exception {
        setPredefinedKeyValue("start_time", startTime);
    }

    /**
     * 消息过期时间,格式: "YYYY-MM-DD hh:mm:ss"。
     */
    public void setExpireTime(@NotNull String expireTime) throws Exception {
        setPredefinedKeyValue("expire_time", expireTime);
    }

    /**
     * 发送限速，每秒发送的最大条数.
     */
    public void setMaxSendNum(Integer num) throws Exception {
        setPredefinedKeyValue("max_send_num", num);
    }

    /**
     * 厂商弹窗 activity
     */
    public void setChannelActivity(@NotNull String activity) throws Exception {
        setPredefinedKeyValue("mipush", "true");
        setPredefinedKeyValue("mi_activity", activity);
    }

    /**
     * 厂商属性配置
     */
    public void setChannelProperties(@NotNull String xiaoMiChannelId) throws Exception {
        JSONObject object = new JSONObject();
        object.put("xiaomi_channel_id", xiaoMiChannelId);
        setPredefinedKeyValue("channel_properties", object);
    }

}
