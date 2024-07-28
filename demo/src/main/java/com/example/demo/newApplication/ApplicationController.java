//package com.example.demo.newApplication;
//
//
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "api/v1/application")
//public class ApplicationController {
//
//    private final ApplicationService applicationService;
//
//    @Autowired
//    public ApplicationController(ApplicationService applicationService) {
//        this.applicationService = applicationService;
//    }
//
//    @GetMapping
//    public List<Application> getAllApplications() {
//        return applicationService.getAllApplications();
//    }
//
//    @PostMapping
//    public void addApplication(@RequestBody Application application) {
//        applicationService.addApplication(application);
//    }
//
//    @DeleteMapping(path = "{applicationID}")
//    public void deleteApplication(@PathVariable("applicationID") Long applicationID) {
//        applicationService.deleteApplication(applicationID);
//    }
//}
package com.example.demo.newApplication;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/application")
@CrossOrigin(origins = "http://localhost:63342") // Adjust to your frontend's origin
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
