package snorlaxa.com.infosys.personnel.system.service.serviceImp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snorlaxa.com.infosys.personnel.base.PageInfoList;
import snorlaxa.com.infosys.personnel.system.dao.JobDao;
import snorlaxa.com.infosys.personnel.system.dto.JobSelectDto;
import snorlaxa.com.infosys.personnel.system.po.JobPo;
import snorlaxa.com.infosys.personnel.system.service.JobService;
import snorlaxa.com.infosys.personnel.system.service.ScoreService;
import snorlaxa.com.infosys.personnel.system.view.params.BatchJobAbilityParam;
import snorlaxa.com.infosys.personnel.system.view.params.JobParam;
import snorlaxa.com.infosys.personnel.system.view.params.PageParam;
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
    @Autowired
    ScoreService scoreService;

    @Override
    public List<JobPo> getJobs(JobSelectDto selectParam) {
        return jobDao.selectJobs(selectParam);
    }

    @Override
    public JobVo getJobVoById(String id) {
        return jobDao.selectJobVoById(id);
    }

    @Override
    public JobParam getJobParamById(String id) {
        JobPo jobPo = jobDao.getJobPoById(id);
        JobParam jobParam = new JobParam();
        jobParam.setId(jobPo.getId());
        jobParam.setCurrentNumber(jobPo.getCurrentNumber());
        jobParam.setDepartmentId(jobPo.getDepartmentId());
        jobParam.setRequirement(jobPo.getRequirement());
        jobParam.setNumber(jobPo.getNumber());
        jobParam.setSalary(jobPo.getSalary());
        jobParam.setName(jobPo.getName());
        return jobParam;
    }

    @Override
    public String upsertJob(JobParam jobParam) {
        JobPo jobPo = new JobPo();
        if (StringUtils.isEmpty(jobParam.getId())) {
            jobPo.setId(UUIDUtil.getUUID());
        } else {
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

    @Override
    public PageInfoList<JobVo> getJobVos(JobSelectDto selectParam, PageParam pageParam) {
        if (selectParam == null) {
            selectParam = new JobSelectDto();
        }
        PageInfoList<JobVo> pageInfoList = new PageInfoList<>();
        if (pageParam.getPage() != null && pageParam.getPageSize() != null) {
            PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize());
        }
        List<JobVo> res = jobDao.selectJobVos(selectParam);
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
    public String batchUpsertJobScore(BatchJobAbilityParam batchJobAbilityParam) {
        JobPo jobPo = jobDao.getJobPoById(batchJobAbilityParam.getId());
        jobPo.setRequirement(batchJobAbilityParam.getRequirement());
        jobPo.setSalary(batchJobAbilityParam.getSalary());
        jobPo.setNumber(batchJobAbilityParam.getNumber());
        jobDao.upsertJob(jobPo);
        scoreService.batchUpsertJobScore(batchJobAbilityParam.getId(),
                batchJobAbilityParam.getIds(),
                batchJobAbilityParam.getNames(),
                batchJobAbilityParam.getScore());
        return jobPo.getId();
    }


}
