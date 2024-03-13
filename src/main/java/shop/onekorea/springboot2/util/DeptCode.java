package shop.onekorea.springboot2.util;

import org.springframework.stereotype.Component;

@Component
public class DeptCode {

    /**
     * 2024.02.28 Created. getDeptCode. 아래에서 공통으로 사용할 부서 코드 받기.
     */

    public static String getDeptCode(int max, int i) {
        String deptCode = "";
        if (i % max == 1) {
            deptCode = "10011001";
        } else if (i % max == 2) {
            deptCode = "10012001";
        } else if (i % max == 3) {
            deptCode = "10013001";
        } else if (i % max == 4) {
            deptCode = "10014001";
        } else if (i % max == 5) {
            deptCode = "10015001";
        } else if (i % max == 6) {
            deptCode = "10016001";
        } else if (i % max == 7) {
            deptCode = "10017001";
        } else if (i % max == 8) {
            deptCode = "10018001";
        } else if (i % max == 9) {
            deptCode = "10019001";
        } else {
            deptCode = "10010001";
        }

        return deptCode;
    }

}
