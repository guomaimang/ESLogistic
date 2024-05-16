package tech.hirsun.eslogistic.service.Impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.hirsun.eslogistic.service.AppService;

@Slf4j
@Service
public class AppServiceImpl implements AppService {

    @Override
    @PostConstruct
    public void initApp() {
        log.info("initApp");
    }

    @Override
    public void resetAll() {

    }
}
