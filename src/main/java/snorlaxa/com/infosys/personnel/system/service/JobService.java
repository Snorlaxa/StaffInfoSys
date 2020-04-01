package snorlaxa.com.infosys.personnel.system.service;

import snorlaxa.com.infosys.personnel.system.dto.JobSelectDto;
import snorlaxa.com.infosys.personnel.system.po.JobPo;
import snorlaxa.com.infosys.personnel.system.view.params.JobParam;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 17:57
 */
public interface JobService {
    List<JobPo> getJobs(JobSelectDto selectParam);
    String upsertJob(JobParam jobParam);
    void delJob(String id);
}
