package shop.onekorea.springboot2.config;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

import static shop.onekorea.springboot2.Springboot2Application.getInfo;

@Slf4j
public class JasyptConfigTest {

    @Test
    public void jasyptConfigTest() {

        /*
        암호화 하고자 하는 application-prod에 있는 특정 값을 넣어 주고 실행하여,
         출력되는 값을 복사하여, application-prod.yml 파일에 넣어 준다.
         */
//        String value = "*963210z"; // Database Password. 데이터베이스 암호를 "암호화" 한다.
//        String value = "sa"; // Database username. 데이터베이스 사용자 이름을 "암호화" 한다.
//        String value = "com.mysql.cj.jdbc.Driver"; // driver-class-name를 "암호화" 한다.

//        String value = "tiger"; // // Database Password. 데이터베이스 암호를 "암호화" 한다.
//        String value = "scott"; // Database username. 데이터베이스 사용자 이름을 "암호화" 한다.
        String value = "oracle.jdbc.OracleDriver"; // driver-class-name를 "암호화" 한다.

        /**
         * String value = "jdbc:mysql://localhost:3306/powerapp?serverTimezone=UTC&characterEncoding=UTF-8"; // url도 암호화 할 수 있다.
         * => FDsnvImt/YaPpCisNLzh8Fx8c6sXl6a0AIp9CLTlIWzj+kh2MUHDYeTELnJyLHg8ObMtGLW7p4zfLnG3Z/Qkm1sKTPkILJPRMIcQzE7Q14qffw6oFFuVsw==
         * String value = "sa"; // username도 암호화 할 수 있다.
         *          * => FDsnvImt/YaPpCisNLzh8Fx8c6sXl6a0AIp9CLTlIWzj+kh2MUHDYeTELnJyLHg8ObMtGLW7p4zfLnG3Z/Qkm1sKTPkILJPRMIcQzE7Q14qffw6oFFuVsw==
         */
        String result = jasyptEncoding(value);
//        log.debug(getInfo(), "---{}---", result);
        log.debug("---{}---", result);
        System.err.println(getInfo() + ", ===> " + result);

    }

    public String jasyptEncoding(String value) {
        // JasyptConfig.java 파일의 jasyptEncoding().key 값과 반드시 동일하게 세팅해 주어야 한다.
        String key = "Tbd+cUcW2wYHgrInS4S7s63SQ8hvSdFIgiOTLSPAwN3HQ2l98ZnecMDZsNen++2z";
        StandardPBEStringEncryptor pbeStringEncryptor = new StandardPBEStringEncryptor();
        pbeStringEncryptor.setAlgorithm("PBEWITHMD5ANDDES");
        pbeStringEncryptor.setPassword(key);
//        System.err.println(getInfo() + ", password: " + pbeEnc.toString());
        return pbeStringEncryptor.encrypt(value);
    }


}
