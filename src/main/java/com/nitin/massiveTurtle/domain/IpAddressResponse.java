package com.nitin.massiveTurtle.domain;

import com.nitin.massiveTurtle.domain.virusTotal.LastAnalysisStats;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class IpAddressResponse {
    private String ipAddress;
    private String network;
    private String country;
    private List<String> tags;

    private LastAnalysisStats lastAnalysisStats;

    private List<String> comments;
}
