package org.balinhui.json.widgets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CONTENT_FILTER_RESULTS {
    //这四个类型是一样的，但由于不知道叫什么，就都用Hate了
    private Hate hate;
    private Hate self_harm;
    private Hate sexual;
    private Hate violence;
}
