package com.nitin.massiveTurtle.domain.virusTotal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class IpReportAttributes {
    private String network;
    private List<String> tags;
    private String country;

    @JsonProperty("last_analysis_stats")
    private LastAnalysisStats lastAnalysisStats;
}
