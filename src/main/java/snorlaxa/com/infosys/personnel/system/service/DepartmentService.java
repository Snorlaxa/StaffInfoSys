package snorlaxa.com.infosys.personnel.system.service;

import snorlaxa.com.infosys.personnel.base.PageInfoList;
import snorlaxa.com.infosys.personnel.system.po.DepartmentPo;
import snorlaxa.com.infosys.personnel.system.view.params.DepartmentParam;
import snorlaxa.com.infosys.personnel.system.view.params.PageParam;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 17:57
 */
public interface DepartmentService {
    List<DepartmentPo> selectDepartments(String id,String name);
    PageInfoList<DepartmentPo> getDepartmentPo(DepartmentParam selectParam, PageParam pageParam);
    String upsertDepartment(DepartmentParam departmentParam);
    void deleteDepartment(String id);
}
