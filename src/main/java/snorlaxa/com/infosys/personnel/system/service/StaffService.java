package snorlaxa.com.infosys.personnel.system.service;

import snorlaxa.com.infosys.personnel.system.po.StaffPo;
import snorlaxa.com.infosys.personnel.system.view.params.StaffParam;
import snorlaxa.com.infosys.personnel.system.view.params.StaffSelectParam;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/17 20:25
 */
public interface StaffService {
    List<StaffPo> getAllStaff(StaffSelectParam selectParam);
    String upsertStaff(StaffParam staffParam);
    void delStaff(String id);
}
