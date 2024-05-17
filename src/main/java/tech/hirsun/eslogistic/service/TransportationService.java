package tech.hirsun.eslogistic.service;

import tech.hirsun.eslogistic.pojo.bo.Transportation;

import java.util.Map;

public interface TransportationService {

    public void initService();

    public Map<Long, Transportation> getTransportationsMap();

}
