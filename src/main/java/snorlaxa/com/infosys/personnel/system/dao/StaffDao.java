package snorlaxa.com.infosys.personnel.system.dao;

import snorlaxa.com.infosys.personnel.system.dto.StaffSelectDto;
import snorlaxa.com.infosys.personnel.system.po.StaffPo;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/13 15:53
 */

 /**
	根据id、姓名、年龄、学历、城市、工作年限、应聘岗位、招聘状态、应聘次数筛选人员
 */
public interface StaffDao {
    List<StaffPo> selectStaffs(StaffSelectDto selectDto);
    void upsertStaff(StaffPo staffPo);
    void deleteStaff(String id);
}
