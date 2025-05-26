package org.balinhui.Core.JSON;

import org.balinhui.Core.JSON.Wid.Choice;
import org.balinhui.Core.JSON.Wid.Usage;

/**
 * {@link  org.balinhui.Core.Call}返回的json的对象
 */
public class Response {
    private String id;
    private String object;
    private int created;
    private String model;
    private Choice[] choices;
    private Usage usage;
    private String system_fingerprint;

    public Response() {
    }

    public Response(String id, String object,
                    int created,
                    String model,
                    Choice[] choices,
                    Usage usage,
                    String system_fingerprint) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
        this.usage = usage;
        this.system_fingerprint = system_fingerprint;
    }

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public int getCreated() {
        return created;
    }

    public String getModel() {
        return model;
    }

    public Choice[] getChoices() {
        return choices;
    }

    public Usage getUsage() {
        return usage;
    }

    public String getSystem_fingerprint() {
        return system_fingerprint;
    }
}

