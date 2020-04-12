package snorlaxa.com.infosys.personnel.system.service.serviceImp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snorlaxa.com.infosys.personnel.system.dao.JobDao;
import snorlaxa.com.infosys.personnel.system.dto.JobSelectDto;
import snorlaxa.com.infosys.personnel.system.po.JobPo;
import snorlaxa.com.infosys.personnel.system.service.JobService;
import snorlaxa.com.infosys.personnel.system.view.params.JobParam;
import snorlaxa.com.infosys.personnel.system.view.vo.JobVo;
import snorlaxa.com.infosys.personnel.utils.UUIDUtil;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 18:12
 */
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobDao jobDao;

    @Override
    public List<JobPo> getJobs(JobSelectDto selectParam) {
        return jobDao.selectJobs(selectParam);
    }

    @Override
    public JobVo getJobVoById(String id) {
        return jobDao.selectJobVoById(id);
    }

    @Override
    public String upsertJob(JobParam jobParam) {
        JobPo jobPo = new JobPo();
        if(StringUtils.isEmpty(jobParam.getId())){
            jobPo.setId(UUIDUtil.getUUID());
        }else{
            jobPo.setId(jobParam.getId());
        }
        jobPo.setName(jobParam.getName());
        jobPo.setNumber(jobParam.getNumber());
        jobPo.setDepartmentId(jobParam.getDepartmentId());
        jobPo.setSalary(jobParam.getSalary());
        jobPo.setCurrentNumber(jobParam.getCurrentNumber());
        jobPo.setRequirement(jobParam.getRequirement());
        jobDao.upsertJob(jobPo);
        return jobPo.getId();
    }

    @Override
    public void delJob(String id) {
        jobDao.deleteJob(id);
    }
}
