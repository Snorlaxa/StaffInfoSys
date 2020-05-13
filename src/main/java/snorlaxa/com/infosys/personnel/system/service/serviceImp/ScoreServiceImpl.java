package snorlaxa.com.infosys.personnel.system.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snorlaxa.com.infosys.personnel.system.dao.AbilityDao;
import snorlaxa.com.infosys.personnel.system.dao.ScoreDao;
import snorlaxa.com.infosys.personnel.system.dto.JobScoreDto;
import snorlaxa.com.infosys.personnel.system.dto.ScoreDto;
import snorlaxa.com.infosys.personnel.system.dto.StaffScoreDto;
import snorlaxa.com.infosys.personnel.system.service.ScoreService;
import snorlaxa.com.infosys.personnel.utils.UUIDUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 11:45
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreDao scoreDao;

    @Autowired
    AbilityDao abilityDao;

    @Override
    public List<ScoreDto> getJobScore(String jobId) {
        return scoreDao.selectJobScore(jobId);
    }

    @Override
    public List<ScoreDto> getStaffScore(String staffId) {
        return scoreDao.selectStaffScore(staffId);
    }

    @Override
    public void upsertJobScore(String jobId, String abilityId, Integer score) {
        scoreDao.upsertJobScore(jobId,abilityId,score);
    }

    @Override
    public void upsertStaffScore(String staffId, String abilityId, Integer score) {
        scoreDao.upsertStaffScore(staffId,abilityId,score);
    }

    @Override
    @Transactional
    public void batchUpsertStaffScore(String staffId, List<String> abilityIds, List<Integer> scores) {
        Iterator<String> idIterator = abilityIds.iterator();
        Iterator<Integer> scoreIterator = scores.iterator();
        List<StaffScoreDto> params = new ArrayList<>();
        while (idIterator.hasNext()&&scoreIterator.hasNext()){
            String id = idIterator.next();
            Integer score = scoreIterator.next();
            StaffScoreDto scoreDto = new StaffScoreDto();
            scoreDto.setAbilityId(id);
            scoreDto.setStaffId(staffId);
            scoreDto.setScore(score);
            params.add(scoreDto);
        }
        delStaffScore(staffId,null);
        scoreDao.batchUpsertStaffScore(params);
    }

    @Override
    @Transactional
    public void batchUpsertJobScore(String jobId, List<String> abilityIds, List<String> abilityNames, List<Integer> scores) {
        List<ScoreDto> jobScore = scoreDao.selectJobScore(jobId);
        List<ScoreDto> newScore = new ArrayList<>();
        List<JobScoreDto> jobScoreDtos = new ArrayList<>();
        Iterator<String> idIterator = abilityIds.iterator();
        Iterator<Integer> scoreIterator = scores.iterator();
        Iterator<String> nameIterator = abilityNames.iterator();
        //找出新增的技能，加入ability表
        while (idIterator.hasNext()&&scoreIterator.hasNext()&&nameIterator.hasNext()){
            String id = idIterator.next();
            Integer score = scoreIterator.next();
            String name = nameIterator.next();
            ScoreDto scoreDto = new ScoreDto();
            scoreDto.setId(id);
            scoreDto.setClassify("default");
            scoreDto.setScore(score);
            scoreDto.setName(name);
            if(!jobScore.contains(scoreDto)){
                newScore.add(scoreDto);
            }
            JobScoreDto jobScoreDto = new JobScoreDto();
            jobScoreDto.setAbilityId(id);
            jobScoreDto.setJobId(jobId);
            jobScoreDto.setScore(score);
            jobScoreDtos.add(jobScoreDto);
        }

        //批量删除已去除的技能关系
        scoreDao.deleteJobScore(jobId,null);

        //新增技能
        if(newScore.size()!=0){
            abilityDao.batchUpsertAbility(newScore);
        }

        //新增技能关系
        scoreDao.batchUpsertJobScore(jobScoreDtos);

    }

    @Override
    public void delJobScore(String jobId, String abilityId) {
        scoreDao.deleteJobScore(jobId,abilityId);
    }

    @Override
    public void delStaffScore(String staffId, String abilityId) {
        scoreDao.deleteStaffScore(staffId,abilityId);
    }
}
