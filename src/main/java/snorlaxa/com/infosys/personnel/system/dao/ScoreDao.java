package snorlaxa.com.infosys.personnel.system.dao;

import org.apache.ibatis.annotations.Param;
import snorlaxa.com.infosys.personnel.system.dto.JobScoreDto;
import snorlaxa.com.infosys.personnel.system.dto.ScoreDto;
import snorlaxa.com.infosys.personnel.system.dto.StaffScoreDto;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 0:35
 */
public interface ScoreDao {
    List<ScoreDto> selectJobScore(@Param("jobId")String jobId);
    void upsertJobScore(@Param("jobId")String jobId,@Param("abilityId")String abilityId,@Param("score")Integer score);
    void deleteJobScore(@Param("jobId")String jobId,@Param("abilityId")String abilityId);

    List<ScoreDto> selectStaffScore(@Param("staffId")String staffId);
    void upsertStaffScore(@Param("staffId")String staffId,@Param("abilityId")String abilityId,@Param("score")Integer score);
    void deleteStaffScore(@Param("staffId")String staffId,@Param("abilityId")String abilityId);

    void batchUpsertStaffScore(@Param("staffScoreDtos") List<StaffScoreDto> staffScoreDtos);
    void batchUpsertJobScore(@Param("jobScoreDtos") List<JobScoreDto> jobScoreDtos);
}
