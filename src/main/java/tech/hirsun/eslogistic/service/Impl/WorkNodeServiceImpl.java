package tech.hirsun.eslogistic.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.hirsun.eslogistic.service.WorkNodeService;
import tech.hirsun.eslogistic.service.dataBean.RegisteredWorkNodes;


@Service
public class WorkNodeServiceImpl implements WorkNodeService {

    @Autowired
    private RegisteredWorkNodes registeredWorkNodes;

}
