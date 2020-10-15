package org.testwork.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.testwork.enums.Type;
import org.testwork.domain.Device;
import org.testwork.domain.Event;
import org.testwork.domain.Project;
import org.testwork.pojo.DevicePOJO;
import org.testwork.pojo.ProjectPOJO;
import org.testwork.repo.DeviceRepo;
import org.testwork.repo.ProjectRepo;

import java.util.*;

@RestController
public class ProjectController {

    private final ProjectRepo projectRepo;
    private final DeviceRepo deviceRepo;

    public ProjectController(ProjectRepo projectRepo, DeviceRepo deviceRepo) {
        this.projectRepo = projectRepo;
        this.deviceRepo = deviceRepo;
    }

    @GetMapping("/project/{id}")
    public List<DevicePOJO> getProject(@PathVariable("id") Long projectId){
        List<DevicePOJO> devicePOJOS = new ArrayList<>();
        List<Device> deviceList = projectRepo.getDevicesByProjectId(projectId);
        for(Device device: deviceList){
            DevicePOJO devicePOJO = new DevicePOJO();
            devicePOJO.setId(device.getId());
            devicePOJO.setSerialNumber(device.getSerialNumber());
            devicePOJO.setProjectId(projectId);
            List<Event> eventList = deviceRepo.getEventsByDeviceId(device.getId());
            boolean hasErrors = false;
            int eventCount = 0;
            int warningCount = 0;
            int errorCount = 0;
            for(Event event: eventList){
                if (event.getType()== Type.error) {
                    hasErrors = true;
                    errorCount++;
                }
                if (event.getType()== Type.event) {
                    eventCount++;
                }
                if (event.getType()== Type.warning) {
                    warningCount++;
                }
            }
            devicePOJO.setHasErrors(hasErrors);
            Map<String, Integer> summaryInfo = new HashMap<>();
            summaryInfo.put("eventCount",eventCount);
            summaryInfo.put("warningCount",warningCount);
            summaryInfo.put("errorCount",errorCount);
            devicePOJO.setSummaryInfo(summaryInfo);
            devicePOJOS.add(devicePOJO);
        }
        return devicePOJOS;
    }

    @GetMapping("/allproject/")
    public List<ProjectPOJO> getAllProject(){
        List<ProjectPOJO> projectPOJOS = new ArrayList<>();
        List<Project> projectList = projectRepo.findAll();
        for(Project project: projectList){
            ProjectPOJO projectPOJO = new ProjectPOJO();
            projectPOJO.setId(project.getId());
            projectPOJO.setName(project.getName());
            Map<String, Integer> stats = new HashMap<>();
            List<Device> deviceList = projectRepo.getDevicesByProjectId(project.getId());
            int deviceCount = deviceList.size();
            int deviceWithErrors = 0;
            int stableDevices = 0;
            for(Device device: deviceList){
                boolean stable = true;
                List<Event> eventList = deviceRepo.getEventsByDeviceId(device.getId());
                for(Event event: eventList){
                    //Проверка за текущий день
                    if((new Date().getTime() - event.getDate().getTime()) < 86400000)
                        if((event.getType() == Type.error)||(event.getType() == Type.warning)){
                            deviceWithErrors++;
                            stable=false;
                            break;
                        }
                    //Проверка на стабильность за все время
                    if((event.getType() == Type.error)||(event.getType() == Type.warning)){
                        stable=false;
                        break;
                    }
                }
                if(stable)
                    stableDevices++;
            }
            stats.put("deviceCount",deviceCount);
            stats.put("deviceWithErrors",deviceWithErrors);
            stats.put("stableDevices",stableDevices);
            projectPOJO.setStats(stats);
            List<String> deviceNameList = new ArrayList<>();
            for(Device device: deviceList){
                deviceNameList.add(device.getSerialNumber());
            }
            projectPOJO.setDevices(deviceNameList);
            projectPOJOS.add(projectPOJO);
        }
        return projectPOJOS;
    }
}
