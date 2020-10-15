package org.testwork.pojo;

import java.util.Map;

public class DevicePOJO {
    private Long id;
    private String SerialNumber;
    private Long projectId;
    private boolean hasErrors;
    private Map<String, Integer> summaryInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, Integer> getSummaryInfo() {
        return summaryInfo;
    }

    public void setSummaryInfo(Map<String, Integer> summaryInfo) {
        this.summaryInfo = summaryInfo;
    }
}
