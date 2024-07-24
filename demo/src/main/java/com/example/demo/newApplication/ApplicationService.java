package com.example.demo.newApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public void addApplication(Application application) {
        applicationRepository.save(application);
    }

    public void deleteApplication(Long applicationID) {
        applicationRepository.deleteById(applicationID);
    }
}
