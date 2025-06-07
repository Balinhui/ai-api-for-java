package org.balinhui.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.balinhui.core.Client;
import org.balinhui.json.widgets.Choice;
import org.balinhui.json.widgets.STREAM_OPTIONS;
import org.balinhui.json.widgets.Usage;

import java.util.Objects;

/**
 * {@link  Client}返回的json的对象
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
    private STREAM_OPTIONS stream_options;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(id, response.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        if (choices[0].getDelta() == null)
            return choices[0].getMessage().getContent();
        else
            return choices[0].getDelta().getContent();
    }
}

