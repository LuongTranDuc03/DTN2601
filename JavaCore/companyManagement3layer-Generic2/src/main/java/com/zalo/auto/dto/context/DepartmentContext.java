package com.zalo.auto.dto.context;

import com.zalo.auto.entity.Department;

import java.util.HashMap;
import java.util.Map;

public class DepartmentContext {
    private Map<String, Department> existingNamesMap = new HashMap<>();
    private Map<String, Boolean> csvNamesMapLower = new HashMap<>();

    public DepartmentContext(Map<String, Department> existingNamesMap) {
        this.existingNamesMap = existingNamesMap;
    }

    public Map<String, Department> getExistingNamesMap() {
        return existingNamesMap;
    }

    public Map<String, Boolean> getCsvNamesMapLower() {
        return csvNamesMapLower;
    }
}
