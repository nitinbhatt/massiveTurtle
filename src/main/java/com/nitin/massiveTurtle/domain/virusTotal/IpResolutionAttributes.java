package com.nitin.massiveTurtle.domain.virusTotal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpResolutionAttributes {
    @JsonProperty("host_name")
    private String hostName;
    private Long date;
}
