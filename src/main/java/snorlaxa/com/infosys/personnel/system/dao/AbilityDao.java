package snorlaxa.com.infosys.personnel.system.dao;

import org.apache.ibatis.annotations.Param;
import snorlaxa.com.infosys.personnel.system.dto.ScoreDto;
import snorlaxa.com.infosys.personnel.system.po.AbilityPo;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/25 22:56
 */
public interface AbilityDao {
    List<AbilityPo> selectAbilitys(AbilityPo abilityPo);
    void upsertAbility(AbilityPo abilityPo);
    void deleteAbility(String id,String classify);
    void batchUpsertAbility(@Param("scores") List<ScoreDto> scoreDtos);
}
