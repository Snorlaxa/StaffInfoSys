package snorlaxa.com.infosys.personnel.system.service;

import snorlaxa.com.infosys.personnel.system.dao.AbilityDao;
import snorlaxa.com.infosys.personnel.system.po.AbilityPo;
import snorlaxa.com.infosys.personnel.system.view.params.AbilityParam;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/26 17:57
 */
public interface AbilityService {
    List<AbilityPo> selectAbilitys(AbilityPo abilityPo);
    String upsertAbility(AbilityParam abilityParam);
    void deleteAbility(String id,String classify);
}
