package com.nitin.massiveTurtle.domain.virusTotal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IpResolutionAttributes {
    @JsonProperty("host_name")
    private String hostName;
    private Long date;
}
