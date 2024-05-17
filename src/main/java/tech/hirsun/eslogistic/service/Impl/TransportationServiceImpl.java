package tech.hirsun.eslogistic.service.Impl;

import org.springframework.stereotype.Service;
import tech.hirsun.eslogistic.pojo.bo.Transportation;
import tech.hirsun.eslogistic.service.TransportationService;

import java.util.Map;

@Service
public class TransportationServiceImpl implements TransportationService {

    @Override
    public void initService() {

    }

    @Override
    public Map<Long, Transportation> getTransportationsMap() {
        return Map.of();
    }
}
