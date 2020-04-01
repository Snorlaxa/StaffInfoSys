package snorlaxa.com.infosys.personnel.system.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snorlaxa.com.infosys.personnel.system.dao.ScoreDao;
import snorlaxa.com.infosys.personnel.system.dto.ScoreDto;
import snorlaxa.com.infosys.personnel.system.service.ScoreService;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 11:45
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreDao scoreDao;

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
    public void delJobScore(String jobId, String abilityId) {
        scoreDao.deleteJobScore(jobId,abilityId);
    }

    @Override
    public void delStaffScore(String staffId, String abilityId) {
        scoreDao.deleteStaffScore(staffId,abilityId);
    }
}
