package shop.onekorea.springboot2.service.initdata;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.entity.primary.Menu1;
import shop.onekorea.springboot2.repository.primary.Menu1Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Menu1InitDataService {

    private final Menu1Repository menu1Repository;

    public List<Menu1> initDataAddService(int max) {
        List<Menu1> menu1List = new ArrayList<>();

        String[] language = {"시스템 관리", "인적 자원 관리", "재무적 자원 관리", "물적 자원 관리", "5 관리", "6 관리", "7 관리", "8 관리", "9 관리"};
        String[] winName = {"w_system", "w_pwms", "w_ams", "w_powerp", "w_5", "w_6", "w_7", "w_8", "w_9"};
        String menuCode;
        String iStr = "";
        for (int i = 1; i <= max; i++) {
            iStr = String.format("%04d", i);
            if (i == 0) {
                menuCode = "0001";
            } else {
                menuCode = String.valueOf(i * 1000 + 1);
            }
            Menu1 menu1 = new Menu1 (
                    (long) i,
                    menuCode + iStr,
                    menuCode + iStr,
                    language[i -1],
                    language[i -1],
                    language[i -1],
                    language[i -1],
                    language[i -1],
                    "3",
                    winName[i -1],
                    "win_param",
                    "1",
                    "l1",
                    LocalDateTime.now(),
                    LocalDateTime.now());
            menu1List.add(menu1);
        }

        menu1Repository.saveAll(menu1List);

        return menu1List;

    }

}
