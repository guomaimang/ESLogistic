package tech.hirsun.eslogistic.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

public interface AppService {

    public void initApp();

    public void resetAll();

}
