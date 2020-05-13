package snorlaxa.com.infosys.personnel.system.view.params;

import lombok.Data;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/5/12 18:40
 */
@Data
public class BatchJobAbilityParam {
    private String id;
    private String requirement;
    private Integer number;
    private Integer salary;
    private List<String> ids;
    private List<Integer> score;
    private List<String> names;
}
