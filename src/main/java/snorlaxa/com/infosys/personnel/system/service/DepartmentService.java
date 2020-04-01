package snorlaxa.com.infosys.personnel.system.service;

import snorlaxa.com.infosys.personnel.system.po.DepartmentPo;
import snorlaxa.com.infosys.personnel.system.view.params.DepartmentParam;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 17:57
 */
public interface DepartmentService {
    List<DepartmentPo> selectDepartments(String id,String name);
    String upsertDepartment(DepartmentParam departmentParam);
    void deleteDepartment(String id);
}
