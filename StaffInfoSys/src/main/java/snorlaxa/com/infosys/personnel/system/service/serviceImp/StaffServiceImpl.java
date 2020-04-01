package snorlaxa.com.infosys.personnel.system.service.serviceImp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snorlaxa.com.infosys.personnel.system.dao.StaffDao;
import snorlaxa.com.infosys.personnel.system.dto.StaffSelectDto;
import snorlaxa.com.infosys.personnel.system.po.StaffPo;
import snorlaxa.com.infosys.personnel.system.service.StaffService;
import snorlaxa.com.infosys.personnel.system.view.params.StaffParam;
import snorlaxa.com.infosys.personnel.system.view.params.StaffSelectParam;

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
        return staffDao.selectStaffs(selectDto);
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
}
