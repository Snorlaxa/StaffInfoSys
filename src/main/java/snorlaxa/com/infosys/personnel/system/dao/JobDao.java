package snorlaxa.com.infosys.personnel.system.dao;

import snorlaxa.com.infosys.personnel.system.dto.JobSelectDto;
import snorlaxa.com.infosys.personnel.system.po.JobPo;
import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/16 22:08
 */
public interface JobDao {
    List<JobPo> selectJobs(JobSelectDto jobSelectDto);
    void upsertJob(JobPo jobPo);
    void deleteJob(String id);
}
