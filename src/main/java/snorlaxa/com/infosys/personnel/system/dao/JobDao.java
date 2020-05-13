package snorlaxa.com.infosys.personnel.system.dao;

import snorlaxa.com.infosys.personnel.system.dto.JobSelectDto;
import snorlaxa.com.infosys.personnel.system.po.JobPo;
import snorlaxa.com.infosys.personnel.system.view.params.JobParam;
import snorlaxa.com.infosys.personnel.system.view.vo.JobVo;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/16 22:08
 */
public interface JobDao {
    List<JobPo> selectJobs(JobSelectDto jobSelectDto);
    JobPo getJobPoById(String id);
    JobVo selectJobVoById(String id);
    List<JobVo> selectJobVos(JobSelectDto jobSelectDto);
    void upsertJob(JobPo jobPo);
    void deleteJob(String id);
}
