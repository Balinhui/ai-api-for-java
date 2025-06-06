package org.balinhui.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.balinhui.json.widgets.Choice;
import org.balinhui.json.widgets.Usage;

/**
 * {@link  org.balinhui.core.Call}返回的json的对象
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String id;
    private String object;
    private int created;
    private String model;
    private Choice[] choices;
    private Usage usage;
    private String system_fingerprint;
    private String service_tier;

    @Override
    public String toString() {
        if (choices[0].getDelta() == null)
            return "id:" + id +
              " model:" + model +
                " message:" + choices[0].getMessage().getContent();
        else
            return "id:" + id +
                    " model:" + model +
                    " message:" + choices[0].getDelta().getContent();
    }
}

