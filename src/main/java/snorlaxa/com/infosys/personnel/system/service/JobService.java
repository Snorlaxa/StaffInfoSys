package snorlaxa.com.infosys.personnel.system.service;

import snorlaxa.com.infosys.personnel.base.PageInfoList;
import snorlaxa.com.infosys.personnel.system.dto.JobSelectDto;
import snorlaxa.com.infosys.personnel.system.po.JobPo;
import snorlaxa.com.infosys.personnel.system.view.params.BatchJobAbilityParam;
import snorlaxa.com.infosys.personnel.system.view.params.JobParam;
import snorlaxa.com.infosys.personnel.system.view.params.PageParam;
import snorlaxa.com.infosys.personnel.system.view.vo.JobVo;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 17:57
 */
public interface JobService {
    List<JobPo> getJobs(JobSelectDto selectParam);
    JobVo getJobVoById(String id);
    JobParam getJobParamById(String id);
    String upsertJob(JobParam jobParam);
    void delJob(String id);
    PageInfoList<JobVo> getJobVos(JobSelectDto selectParam, PageParam pageParam);
    String batchUpsertJobScore(BatchJobAbilityParam batchJobAbilityParam);

}
