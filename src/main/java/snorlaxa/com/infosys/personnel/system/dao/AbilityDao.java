package snorlaxa.com.infosys.personnel.system.dao;

import snorlaxa.com.infosys.personnel.system.po.AbilityPo;
import snorlaxa.com.infosys.personnel.system.po.JobPo;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/25 22:56
 */
public interface AbilityDao {
    List<AbilityPo> selectAbilitys(AbilityPo abilityPo);
    void upsertAbility(AbilityPo abilityPo);
    void deleteAbility(String id,String classify);
}
