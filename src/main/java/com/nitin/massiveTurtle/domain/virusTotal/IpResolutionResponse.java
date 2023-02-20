package com.nitin.massiveTurtle.domain.virusTotal;

import lombok.Data;

import java.util.List;

@Data
public class IpResolutionResponse {
    private List<IpResolutionData> data;
}

