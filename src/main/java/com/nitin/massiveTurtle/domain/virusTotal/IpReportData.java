package com.nitin.massiveTurtle.domain.virusTotal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpReportData {
    private IpReportAttributes attributes;
    private String id;
    private String type;
}
