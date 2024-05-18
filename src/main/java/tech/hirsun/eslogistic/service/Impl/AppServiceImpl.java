package tech.hirsun.eslogistic.service.Impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.hirsun.eslogistic.emulator.Master;
import tech.hirsun.eslogistic.service.AppService;
import tech.hirsun.eslogistic.service.RouterService;
import tech.hirsun.eslogistic.service.WorkNodeService;

@Slf4j
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private WorkNodeService workNodeService;

    @Autowired
    private RouterService routerService;

    @Autowired
    private Master master;

    @Override
    @PostConstruct
    public void initApp() {
        log.info("initApp");
        workNodeService.initService();
        routerService.initService();
        master.initService();
    }

    @Override
    public void resetAll() {

    }
}