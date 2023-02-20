package com.nitin.massiveTurtle.domain.virusTotal;

import lombok.Data;

@Data
public class IpReportData {
    private IpReportAttributes attributes;
    private String id;
    private String type;
}
