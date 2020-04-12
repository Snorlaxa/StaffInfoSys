package snorlaxa.com.infosys.personnel.system.service.serviceImp;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snorlaxa.com.infosys.personnel.base.PageInfoList;
import snorlaxa.com.infosys.personnel.system.dao.StaffDao;
import snorlaxa.com.infosys.personnel.system.dto.StaffSelectDto;
import snorlaxa.com.infosys.personnel.system.po.StaffPo;
import snorlaxa.com.infosys.personnel.system.service.StaffService;
import snorlaxa.com.infosys.personnel.system.view.params.PageParam;
import snorlaxa.com.infosys.personnel.system.view.params.StaffParam;
import snorlaxa.com.infosys.personnel.system.view.params.StaffSelectParam;
import snorlaxa.com.infosys.personnel.system.view.vo.StaffVo;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/17 20:37
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffDao staffDao;

    @Override
    public List<StaffPo> getAllStaff(StaffSelectParam selectParam) {
        StaffSelectDto selectDto = new StaffSelectDto();
        selectDto.setId(selectParam.getId());
        selectDto.setCity(selectParam.getCity());
        selectDto.setDegree(selectParam.getDegree());
        selectDto.setEmployStatus(selectParam.getEmployStatus());
        selectDto.setGender(selectParam.getGender());
        selectDto.setAge(selectParam.getAge());
        selectDto.setInterviewTimes(selectParam.getInterviewTimes());
        selectDto.setName(selectParam.getName());
        selectDto.setWorkingYears(selectParam.getWorkingYears());
        selectDto.setLimit(selectParam.getLimit());
        selectDto.setJobName(selectParam.getJobName());
        selectDto.setDepartment(selectParam.getDepartment());
        return staffDao.selectStaffs(selectDto);
    }

    @Override
    public List<StaffVo> getStaffVo(StaffSelectParam selectParam) {
        StaffSelectDto selectDto = new StaffSelectDto();
        selectDto.setId(selectParam.getId());
        selectDto.setCity(selectParam.getCity());
        selectDto.setDegree(selectParam.getDegree());
        selectDto.setEmployStatus(selectParam.getEmployStatus());
        selectDto.setGender(selectParam.getGender());
        selectDto.setAge(selectParam.getAge());
        selectDto.setInterviewTimes(selectParam.getInterviewTimes());
        selectDto.setName(selectParam.getName());
        selectDto.setWorkingYears(selectParam.getWorkingYears());
        selectDto.setLimit(selectParam.getLimit());
        selectDto.setDepartment(selectParam.getDepartment());
        selectDto.setJobName(selectParam.getJobName());
        List<StaffVo> res =staffDao.selectStaffVos(selectDto);
        return res;
    }

    @Override
    public StaffPo getStaffById(String id) {
        return staffDao.selectStaffById(id);
    }

    /**
     * 若未传入id，说明是新增，随机生成一个uuid
     * @param staffParam
     */
    @Override
    public String upsertStaff(StaffParam staffParam) {
        StaffPo staffPo = staffParam.toStaff();
        staffDao.upsertStaff(staffPo);
        return staffPo.getId();
    }

    @Override
    public void delStaff(String id) {
        staffDao.deleteStaff(id);
    }

    @Override
    public PageInfoList<StaffVo> getStaffVo(StaffSelectParam selectParam, PageParam pageParam) {
        if(selectParam==null){
            selectParam = new StaffSelectParam();
        }
        StaffSelectDto selectDto = new StaffSelectDto();
        selectDto.setId(selectParam.getId());
        selectDto.setCity(selectParam.getCity());
        selectDto.setDegree(selectParam.getDegree());
        selectDto.setEmployStatus(selectParam.getEmployStatus());
        selectDto.setGender(selectParam.getGender());
        selectDto.setAge(selectParam.getAge());
        selectDto.setInterviewTimes(selectParam.getInterviewTimes());
        selectDto.setName(selectParam.getName());
        selectDto.setWorkingYears(selectParam.getWorkingYears());
        selectDto.setLimit(selectParam.getLimit());
        selectDto.setDepartment(selectParam.getDepartment());
        selectDto.setJobName(selectParam.getJobName());
        PageInfoList<StaffVo> pageInfoList = new PageInfoList<>();
        if (pageParam.getPage()!=null && pageParam.getPageSize()!=null) {
             PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize());
        }
        List<StaffVo> res =staffDao.selectStaffVos(selectDto);
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
}
