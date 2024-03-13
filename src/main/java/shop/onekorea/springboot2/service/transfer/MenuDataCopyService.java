package shop.onekorea.springboot2.service.transfer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.entity.primary.Menu1;
import shop.onekorea.springboot2.entity.secondary.Menu2;
import shop.onekorea.springboot2.repository.primary.Menu1Repository;
import shop.onekorea.springboot2.repository.secondary.Menu2Repository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuDataCopyService {

    private final Menu1Repository menu1Repository;
    private final Menu2Repository menu2Repository;

    public List<Menu2> copyService() {
        List<Menu1> menu1List = menu1Repository.findAll();
        for (Menu1 menu1 : menu1List) {
            Menu2 menu2 = new Menu2();
            menu2.setCode(menu1.getCode());
            menu2.setLanguage1(menu1.getLanguage1());
            menu2.setLanguage2(menu1.getLanguage2());
            menu2.setLanguage3(menu1.getLanguage3());
            menu2.setLanguage4(menu1.getLanguage4());
            menu2.setExplanation(menu1.getExplanation());
            menu2.setWinType(menu1.getWinType());
            menu2.setWinName(menu1.getWinName());
            menu2.setWinParam(menu1.getWinParam());
            menu2.setIsAccess(menu1.getIsAccess());
            menu2.setIsUpdated(menu1.getIsUpdated());
            menu2.setUpdatedAt(menu1.getUpdatedAt());
            menu2.setCreatedAt(menu1.getCreatedAt());
        }

        List<Menu2> menu2List = menu2Repository.findAll();

        return menu2List;
    }

}
