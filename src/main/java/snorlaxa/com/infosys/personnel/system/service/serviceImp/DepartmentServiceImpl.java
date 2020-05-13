package snorlaxa.com.infosys.personnel.system.service.serviceImp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snorlaxa.com.infosys.personnel.base.PageInfoList;
import snorlaxa.com.infosys.personnel.system.dao.DepartmentDao;
import snorlaxa.com.infosys.personnel.system.po.DepartmentPo;
import snorlaxa.com.infosys.personnel.system.service.DepartmentService;
import snorlaxa.com.infosys.personnel.system.view.params.DepartmentParam;
import snorlaxa.com.infosys.personnel.system.view.params.PageParam;
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
    public PageInfoList<DepartmentPo> getDepartmentPo(DepartmentParam selectParam, PageParam pageParam) {
        if (selectParam == null) {
            selectParam = new DepartmentParam();
        }
        PageInfoList<DepartmentPo> pageInfoList = new PageInfoList<>();
        if (pageParam.getPage() != null && pageParam.getPageSize() != null) {
            PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize());
        }
        List<DepartmentPo> res = departmentDao.selectDepartments(selectParam.getId(),selectParam.getName());
        PageInfo pageInfo = new PageInfo(res);
        pageInfoList.setPageNum(pageInfo.getPageNum());
        pageInfoList.setPageSize(pageInfo.getPageSize());
        pageInfoList.setPages(pageInfo.getPages());
        pageInfoList.setNextPage(pageInfo.getNextPage());
        pageInfoList.setPrePage(pageInfo.getPrePage());
        pageInfoList.setDatas(res);
        pageInfoList.setNavpages(pageInfo.getNavigatepageNums());
        pageInfoList.setPages(pageInfo.getPages());
        pageInfoList.setHasNextPage(pageInfo.isHasNextPage());
        pageInfoList.setHasPreviousPage(pageInfo.isHasPreviousPage());
        return pageInfoList;
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
