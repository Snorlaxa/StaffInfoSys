package snorlaxa.com.infosys.personnel.system.service.serviceImp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snorlaxa.com.infosys.personnel.system.dao.DepartmentDao;
import snorlaxa.com.infosys.personnel.system.po.DepartmentPo;
import snorlaxa.com.infosys.personnel.system.service.DepartmentService;
import snorlaxa.com.infosys.personnel.system.view.params.DepartmentParam;
import snorlaxa.com.infosys.personnel.utils.UUIDUtil;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/29 18:43
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentDao departmentDao;
    @Override
    public List<DepartmentPo> selectDepartments(String id,String name) {
        return departmentDao.selectDepartments(id, name);
    }

    @Override
    public String upsertDepartment(DepartmentParam departmentParam) {
        DepartmentPo departmentPo =new DepartmentPo();
        if(StringUtils.isEmpty(departmentParam.getId())){
            departmentPo.setId(UUIDUtil.getUUID());
        }else{
            departmentPo.setId(departmentParam.getId());
        }
        departmentPo.setName(departmentParam.getName());
        departmentPo.setStaffNum(departmentParam.getStaffNum());
        departmentDao.upsertDepartment(departmentPo);
        return departmentPo.getId();
    }

    @Override
    public void deleteDepartment(String id) {
        departmentDao.deleteDepartment(id);
    }
}
