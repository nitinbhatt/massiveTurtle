package com.nitin.massiveTurtle.domain.virusTotal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpReportAttributes {
    private String network;
    private List<String> tags;
    private String country;

    @JsonProperty("last_analysis_stats")
    private LastAnalysisStats lastAnalysisStats;
}
