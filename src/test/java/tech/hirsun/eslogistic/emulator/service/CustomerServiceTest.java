package tech.hirsun.eslogistic.emulator.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


@SpringBootTest
@Slf4j
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void batchGenPack() {
        customerService.batchGenPack();
    }

    @Test
    void randomTest() {
        SecureRandom random = null;
        int randomInt = 0;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(System.currentTimeMillis());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            log.info("random: {}", random.nextInt());
        }
    }
}