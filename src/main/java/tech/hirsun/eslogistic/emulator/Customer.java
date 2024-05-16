package tech.hirsun.eslogistic.emulator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.hirsun.eslogistic.emulator.service.CustomerService;

@Component
@EnableScheduling
@Slf4j
public class Customer {

    @Autowired
    private CustomerService customerService;

    @Scheduled(cron ="*/10 * * * * ?")
    public void placeOrder() {
        customerService.batchGenPack();
    }


}
