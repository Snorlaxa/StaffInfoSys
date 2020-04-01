package snorlaxa.com.infosys.personnel.system.service.serviceImp;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snorlaxa.com.infosys.personnel.system.dao.AbilityDao;
import snorlaxa.com.infosys.personnel.system.po.AbilityPo;
import snorlaxa.com.infosys.personnel.system.service.AbilityService;
import snorlaxa.com.infosys.personnel.system.view.params.AbilityParam;
import snorlaxa.com.infosys.personnel.utils.UUIDUtil;

import java.util.List;

/**
 * @Author: snorlaxa
 * @Date: 2020/3/29 17:28
 */
@Service
public class AbilityServiceImpl implements AbilityService {
    @Autowired
    AbilityDao abilityDao;

    @Override
    public List<AbilityPo> selectAbilitys(AbilityPo abilityPo) {
        return abilityDao.selectAbilitys(abilityPo);
    }

    @Override
    public String upsertAbility(AbilityParam abilityParam) {
        AbilityPo abilityPo = new AbilityPo();
        abilityPo.setClassify(abilityParam.getClassify());
        abilityPo.setName(abilityParam.getName());
        if(StringUtils.isEmpty(abilityParam.getId())){
            abilityPo.setId(UUIDUtil.getUUID());
        }else{
            abilityPo.setId(abilityParam.getId());
        }
        abilityDao.upsertAbility(abilityPo);
        return abilityPo.getId();
    }

    @Override
    public void deleteAbility(String id, String classify) {

        abilityDao.deleteAbility(id,classify);
    }
}
