package snorlaxa.com.infosys.personnel.system.view.params;

import lombok.Data;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/5/3 11:25
 */
@Data
public class BatchStaffAbilityParam {
    private StaffParam staffParam;
    private List<String> ids;
    private List<Integer> score;
}
