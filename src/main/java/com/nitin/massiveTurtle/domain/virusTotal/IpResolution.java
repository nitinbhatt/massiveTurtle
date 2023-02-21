package com.nitin.massiveTurtle.domain.virusTotal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpResolution extends IpMetaData {

    private String hostName;
}
