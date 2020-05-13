package snorlaxa.com.infosys.personnel.system.service;

import snorlaxa.com.infosys.personnel.system.dto.ScoreDto;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 11:41
 */
public interface ScoreService {
    List<ScoreDto> getJobScore(String jobId);

    List<ScoreDto> getStaffScore(String staffId);

    void upsertJobScore(String jobId, String abilityId, Integer score);

    void upsertStaffScore(String staffId, String abilityId, Integer score);

    void batchUpsertStaffScore(String staffId, List<String> abilityIds, List<Integer> score);

    void batchUpsertJobScore(String jobId, List<String> abilityIds, List<String> abilityNames, List<Integer> score);

    void delJobScore(String jobId, String abilityId);

    void delStaffScore(String staffId, String abilityId);
}
