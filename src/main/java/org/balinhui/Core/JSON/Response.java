package org.balinhui.Core.JSON;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.balinhui.Core.JSON.Widgets.Choice;
import org.balinhui.Core.JSON.Widgets.Usage;

/**
 * {@link  org.balinhui.Core.Call}返回的json的对象
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
}

