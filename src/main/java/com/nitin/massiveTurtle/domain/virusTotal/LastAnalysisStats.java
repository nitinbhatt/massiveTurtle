package com.nitin.massiveTurtle.domain.virusTotal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LastAnalysisStats {
    private Integer harmless;
    private Integer malicious;
    private Integer suspicious;
    private Integer undetected;
    private Integer timeout;

}
