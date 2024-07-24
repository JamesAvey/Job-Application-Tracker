package com.example.demo.newApplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @PostMapping
    public void addApplication(@RequestBody Application application) {
        applicationService.addApplication(application);
    }

    @DeleteMapping(path = "{applicationID}")
    public void deleteApplication(@PathVariable("applicationID") Long applicationID) {
        applicationService.deleteApplication(applicationID);
    }
}
