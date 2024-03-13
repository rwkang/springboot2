package shop.onekorea.springboot2.config.security;

/**
 * Spring Security on Java Simplified Encryption 보안.
 * https://www.youtube.com/watch?v=3CY2pk-Ug10&list=PLeMeDIV7bypvyxWv7eIUZubmfx9W-Jjdg&index=4&t=981s
 * => MSA 패턴에서는, Config 서버를 별도로 두고, 일반 개발자는 어떤 곳에 접속을 못하게 하는 보안을 구성할 수 있다.
 *
 * 1. @Bean("jasyptStringEncryptor") // application.yml 파일에 반드시 등록해 주어야 한다.
 * 2. 암호화 키를 자동으로 생성해 주는 사이트에서, 아무래도 길게 만들어, 한 눈에 잘 안 들어오게 해 보자. google: encrypt key generator
 *    https://generate-random.org/encryption-key-generator
 * @author rwkang on 2023.10.01
 */

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
    @Bean("jasyptStringEncryptor") // application.yml 파일에 반드시 등록해 주어야 한다. [등록명과 동일하게 맞추어 준다]
    public StringEncryptor stringEncryptor() {
        /*
        암호화 키를 자동으로 생성해 주는 사이트에서, 아무래도 길게 만들어, 한 눈에 잘 안 들어오게 해 보자. google: encrypt key generator
        https://generate-random.org/encryption-key-generator
         */
        String key = "Tbd+cUcW2wYHgrInS4S7s63SQ8hvSdFIgiOTLSPAwN3HQ2l98ZnecMDZsNen++2z";
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(key); // encrypt key

        config.setAlgorithm("PBEWITHMD5ANDDES");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        return encryptor;
    }
}

