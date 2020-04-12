package snorlaxa.com.infosys.personnel.system.service;

import com.github.pagehelper.PageInfo;
import snorlaxa.com.infosys.personnel.base.PageInfoList;
import snorlaxa.com.infosys.personnel.system.po.StaffPo;
import snorlaxa.com.infosys.personnel.system.view.params.PageParam;
import snorlaxa.com.infosys.personnel.system.view.params.StaffParam;
import snorlaxa.com.infosys.personnel.system.view.params.StaffSelectParam;
import snorlaxa.com.infosys.personnel.system.view.vo.StaffVo;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/17 20:25
 */
public interface StaffService {
    List<StaffPo> getAllStaff(StaffSelectParam selectParam);
    List<StaffVo> getStaffVo(StaffSelectParam selectParam);
    StaffPo getStaffById(String id);
    String upsertStaff(StaffParam staffParam);
    void delStaff(String id);
    PageInfoList<StaffVo> getStaffVo(StaffSelectParam selectParam, PageParam pageParam);
}
