package dev.dengchao;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public abstract class AndroidNotification extends UmengNotification {
    /**
     * Keys can be set in the payload level
     */
    private static final HashSet<String> PAYLOAD_KEYS = new HashSet<>(Collections.singletonList("display_type"));

    /**
     * Keys can be set in the body level
     */
    private static final HashSet<String> BODY_KEYS = new HashSet<>(Arrays.asList(
            "ticker", "title", "text", "builder_id", "icon", "largeIcon", "img", "play_vibrate", "play_lights", "play_sound",
            "sound", "after_open", "url", "activity", "custom"));

    /**
     * Set key/value in the rootJson, for the keys can be set please see ROOT_KEYS, PAYLOAD_KEYS,
     * BODY_KEYS and POLICY_KEYS.
     */
    @Override
    public boolean setPredefinedKeyValue(@NotNull String key, @NotNull Object value) {
        if (ROOT_KEYS.contains(key)) {
            // This key should be in the root level
            rootJson.put(key, value);
        } else if (PAYLOAD_KEYS.contains(key)) {
            // This key should be in the payload level
            JSONObject payloadJson = null;
            if (rootJson.has("payload")) {
                payloadJson = rootJson.getJSONObject("payload");
            } else {
                payloadJson = new JSONObject();
                rootJson.put("payload", payloadJson);
            }
            payloadJson.put(key, value);
        } else if (BODY_KEYS.contains(key)) {
            // This key should be in the body level
            JSONObject bodyJson;
            JSONObject payloadJson;
            // 'body' is under 'payload', so build a payload if it doesn't exist
            if (rootJson.has("payload")) {
                payloadJson = rootJson.getJSONObject("payload");
            } else {
                payloadJson = new JSONObject();
                rootJson.put("payload", payloadJson);
            }
            // Get body JSONObject, generate one if not existed
            if (payloadJson.has("body")) {
                bodyJson = payloadJson.getJSONObject("body");
            } else {
                bodyJson = new JSONObject();
                payloadJson.put("body", bodyJson);
            }
            bodyJson.put(key, value);
        } else if (POLICY_KEYS.contains(key)) {
            // This key should be in the body level
            JSONObject policyJson;
            if (rootJson.has("policy")) {
                policyJson = rootJson.getJSONObject("policy");
            } else {
                policyJson = new JSONObject();
                rootJson.put("policy", policyJson);
            }
            policyJson.put(key, value);
        } else {
            if (key.equals("payload") || key.equals("body") || key.equals("policy") || key.equals("extra")) {
                throw new RuntimeException("You don't need to set value for " + key + " , just set values for the sub keys in it.");
            } else {
                throw new RuntimeException("Unknown key: " + key);
            }
        }
        return true;
    }

    /**
     * Set extra key/value for Android notification
     */
    public boolean setExtraField(@NotNull String key, @NotNull String value) {
        JSONObject payloadJson = null;
        JSONObject extraJson = null;
        if (rootJson.has("payload")) {
            payloadJson = rootJson.getJSONObject("payload");
        } else {
            payloadJson = new JSONObject();
            rootJson.put("payload", payloadJson);
        }

        if (payloadJson.has("extra")) {
            extraJson = payloadJson.getJSONObject("extra");
        } else {
            extraJson = new JSONObject();
            payloadJson.put("extra", extraJson);
        }
        extraJson.put(key, value);
        return true;
    }

    //
    public void setDisplayType(DisplayType d) {
        setPredefinedKeyValue("display_type", d.getValue());
    }

    /**
     * 通知栏提示文字
     */
    public void setTicker(@NotNull String ticker) {
        setPredefinedKeyValue("ticker", ticker);
    }

    /**
     * 通知标题
     */
    public void setTitle(@NotNull String title) {
        setPredefinedKeyValue("title", title);
    }

    /**
     * 通知文字描述
     */
    public void setText(@NotNull String text) {
        setPredefinedKeyValue("text", text);
    }

    /**
     * 用于标识该通知采用的样式。使用该参数时, 必须在SDK里面实现自定义通知栏样式。
     */
    public void setBuilderId(Integer builder_id) {
        setPredefinedKeyValue("builder_id", builder_id);
    }

    /**
     * 状态栏图标ID, R.drawable.[smallIcon],如果没有, 默认使用应用图标。
     */
    public void setIcon(@NotNull String icon) {
        setPredefinedKeyValue("icon", icon);
    }

    /**
     * 通知栏拉开后左侧图标ID
     */
    public void setLargeIcon(@NotNull String largeIcon) {
        setPredefinedKeyValue("largeIcon", largeIcon);
    }

    /**
     * 通知栏大图标的URL链接。该字段的优先级大于largeIcon。该字段要求以http或者https开头。
     */
    public void setImg(@NotNull String img) {
        setPredefinedKeyValue("img", img);
    }

    /**
     * 收到通知是否震动,默认为"true"
     */
    public void setPlayVibrate(Boolean play_vibrate) {
        setPredefinedKeyValue("play_vibrate", play_vibrate.toString());
    }

    /**
     * 收到通知是否闪灯,默认为"true"
     */
    public void setPlayLights(Boolean play_lights) {
        setPredefinedKeyValue("play_lights", play_lights.toString());
    }

    /**
     * 收到通知是否发出声音,默认为"true"
     */
    public void setPlaySound(Boolean play_sound) {
        setPredefinedKeyValue("play_sound", play_sound.toString());
    }

    /**
     * 通知声音，R.raw.[sound]. 如果该字段为空，采用SDK默认的声音
     */
    public void setSound(@NotNull String sound) {
        setPredefinedKeyValue("sound", sound);
    }

    /**
     * 收到通知后播放指定的声音文件
     */
    public void setPlaySound(@NotNull String sound) {
        setPlaySound(true);
        setSound(sound);
    }

    /**
     * 点击"通知"的后续行为，默认为打开app。
     */
    public void goAppAfterOpen() {
        setAfterOpenAction(AfterOpenAction.go_app);
    }

    public void goUrlAfterOpen(@NotNull String url) {
        setAfterOpenAction(AfterOpenAction.go_url);
        setUrl(url);
    }

    public void goActivityAfterOpen(@NotNull String activity) {
        setAfterOpenAction(AfterOpenAction.go_activity);
        setActivity(activity);
    }

    public void goCustomAfterOpen(@NotNull String custom) {
        setAfterOpenAction(AfterOpenAction.go_custom);
        setCustomField(custom);
    }

    public void goCustomAfterOpen(JSONObject custom) {
        setAfterOpenAction(AfterOpenAction.go_custom);
        setCustomField(custom);
    }

    /**
     * 点击"通知"的后续行为，默认为打开app。原始接口
     */
    public void setAfterOpenAction(AfterOpenAction action) {
        setPredefinedKeyValue("after_open", action.toString());
    }

    public void setUrl(@NotNull String url) {
        setPredefinedKeyValue("url", url);
    }

    public void setActivity(@NotNull String activity) {
        setPredefinedKeyValue("activity", activity);
    }

    ///can be a string of json
    public void setCustomField(@NotNull String custom) {
        setPredefinedKeyValue("custom", custom);
    }

    public void setCustomField(JSONObject custom) {
        setPredefinedKeyValue("custom", custom);
    }

    public enum DisplayType {
        /**
         * 通知:消息送达到用户设备后，由友盟SDK接管处理并在通知栏上显示通知内容。
         */
        NOTIFICATION {
            public String getValue() {
                return "notification";
            }
        },
        /**
         * 消息:消息送达到用户设备后，消息内容透传给应用自身进行解析处理。
         */
        MESSAGE {
            public String getValue() {
                return "message";
            }
        };

        public abstract String getValue();
    }

    public enum AfterOpenAction {
        /**
         * 打开应用
         */
        go_app,
        /**
         * 跳转到URL
         */
        go_url,
        /**
         * 打开特定的activity
         */
        go_activity,
        /**
         * 用户自定义内容。
         */
        go_custom
    }

}
