package com.example.AgencyApi.service;

import com.example.AgencyApi.model.Agency;
import com.example.AgencyApi.util.AgencyJsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgencyService {
    @Value("${agency.updates-allowed}")
    private boolean updatesAllowed;

    /**
     * Get all agencies from the JSON file.
     */
    public List<Agency> getAllAgencies() {
        return AgencyJsonUtils.readAgencies();
    }

    /**
     * Add a new agency. If an agency with the same 'name' or 'code'
     * exists, return an error message.
     */
    public String addAgency(Agency newAgency) {
        List<Agency> agencies = AgencyJsonUtils.readAgencies();
        boolean exists = agencies.stream().anyMatch(agency ->
                agency.getName().equalsIgnoreCase(newAgency.getName()) ||
                        agency.getCode().equalsIgnoreCase(newAgency.getCode())
        );
        if (exists) {
            return "Cannot add agency: An agency with the same name or code already exists.";
        }
        agencies.add(newAgency);
        AgencyJsonUtils.writeAgencies(agencies);
        return "Agency added successfully.";
    }

    /**
     * Update an existing agency. If updates are not allowed or the
     * agency is not found, return an error message.
     */
    public String updateAgency(Agency updatedAgency) {
        if (!updatesAllowed) {
            return "Update functionality is disabled.";
        }
        List<Agency> agencies = AgencyJsonUtils.readAgencies();
        Optional<Agency> existingAgency = agencies.stream()
                .filter(agency ->
                        agency.getId().equals(updatedAgency.getId()))
                .findFirst();
        if (existingAgency.isEmpty()) {
            return "No agency found with the given ID.";
        }
        agencies.remove(existingAgency.get());
        agencies.add(updatedAgency);
        AgencyJsonUtils.writeAgencies(agencies);
        return "Agency updated successfully.";
    }
}
/*
package com.example.AgencyApi.service;

import com.example.AgencyApi.model.Agency;
import com.example.AgencyApi.util.AgencyJsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static com.example.AgencyApi.util.AgencyJsonUtils.writeAgencies;

@Service
public class AgencyService {
    @Value("${agency.updates-allowed}")
    private boolean updatesAllowed;
    public List<Agency> getAllAgencies() {
        return AgencyJsonUtils.readAgencies();
    }
    public String addOrUpdateAgency(Agency newAgency) {
        List<Agency> agencies = AgencyJsonUtils.readAgencies();
        Optional<Agency> existingAgency = agencies.stream()
                .filter(agency->
                        agency.getId().equals(newAgency.getId()))
                .findFirst();
        if (existingAgency.isPresent()) {
            if (!updatesAllowed) {
                return "Update functionality is disabled.";
            }
            agencies.remove(existingAgency.get());
            agencies.add(newAgency);
            writeAgencies(agencies);
            return "Agency updated successfully.";
        } else {
            agencies.add(newAgency);

            writeAgencies(agencies);
            return "Agency added successfully.";
        }}
}
*/