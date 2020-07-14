package dev.dengchao;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashSet;

public abstract class IOSNotification extends UmengNotification {

    /**
     * Keys can be set in the aps level
     */
    protected static final HashSet<String> APS_KEYS = new HashSet<>(Arrays.asList("alert", "badge", "sound", "content-available"));

    @Override
    public boolean setPredefinedKeyValue(@NotNull String key, Object value) {
        if (ROOT_KEYS.contains(key)) {
            // This key should be in the root level
            rootJson.put(key, value);
        } else if (APS_KEYS.contains(key)) {
            // This key should be in the aps level
            JSONObject apsJson;
            JSONObject payloadJson;
            if (rootJson.has("payload")) {
                payloadJson = rootJson.getJSONObject("payload");
            } else {
                payloadJson = new JSONObject();
                rootJson.put("payload", payloadJson);
            }
            if (payloadJson.has("aps")) {
                apsJson = payloadJson.getJSONObject("aps");
            } else {
                apsJson = new JSONObject();
                payloadJson.put("aps", apsJson);
            }
            apsJson.put(key, value);
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
            if (key.equals("payload") || key.equals("aps") || key.equals("policy")) {
                throw new RuntimeException("You don't need to set value for " + key + " , just set values for the sub keys in it.");
            } else {
                throw new RuntimeException("Unknown key: " + key);
            }
        }

        return true;
    }

    // Set customized key/value for IOS notification
    public boolean setCustomizedField(@NotNull String key, @NotNull String value) {
        JSONObject payloadJson;
        if (rootJson.has("payload")) {
            payloadJson = rootJson.getJSONObject("payload");
        } else {
            payloadJson = new JSONObject();
            rootJson.put("payload", payloadJson);
        }
        payloadJson.put(key, value);
        return true;
    }

    public void setAlert(@NotNull String token) {
        setPredefinedKeyValue("alert", token);
    }

    public void setAlert(@NotNull String title, @NotNull String subtitle, @NotNull String body) {
        JSONObject object = new JSONObject();
        object.put("title", title);
        object.put("subtitle", subtitle);
        object.put("body", body);
        setPredefinedKeyValue("alert", object);
    }

    public void setBadge(Integer badge) {
        setPredefinedKeyValue("badge", badge);
    }

    public void setSound(@NotNull String sound) {
        setPredefinedKeyValue("sound", sound);
    }

    public void setContentAvailable(Integer contentAvailable) {
        setPredefinedKeyValue("content-available", contentAvailable);
    }
}
