package snorlaxa.com.infosys.personnel.system.dao;

import org.apache.ibatis.annotations.Param;
import snorlaxa.com.infosys.personnel.system.po.DepartmentPo;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 0:11
 */
public interface DepartmentDao {
    List<DepartmentPo> selectDepartments(@Param("id") String id,@Param("name") String name);
    void upsertDepartment(DepartmentPo departmentPo);
    void deleteDepartment(String id);
}
