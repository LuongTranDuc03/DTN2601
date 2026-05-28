package com.zalo.auto.dto.context;

import com.zalo.auto.entity.Account;
import com.zalo.auto.entity.Department;
import com.zalo.auto.entity.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountContext {
    private Map<String, Account> mapAccountByEmail;
    private Map<String, Account> mapAccountByUsername;
    private List<Department> departments;
    private List<Position> positions;

    // Tracker maps to detect duplicate email/username inside the CSV file during import
    private final Map<String, Boolean> csvEmailsMapLower = new HashMap<>();
    private final Map<String, Boolean> csvUsernamesMapLower = new HashMap<>();

    public AccountContext(Map<String, Account> mapAccountByEmail, Map<String, Account> mapAccountByUsername,
                          List<Department> departments, List<Position> positions) {
        this.mapAccountByEmail = mapAccountByEmail;
        this.mapAccountByUsername = mapAccountByUsername;
        this.departments = departments;
        this.positions = positions;
    }

    public Map<String, Account> getMapAccountByEmail() {
        return mapAccountByEmail;
    }

    public Map<String, Account> getMapAccountByUsername() {
        return mapAccountByUsername;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public Map<String, Boolean> getCsvEmailsMapLower() {
        return csvEmailsMapLower;
    }

    public Map<String, Boolean> getCsvUsernamesMapLower() {
        return csvUsernamesMapLower;
    }
}
