package com.nitin.massiveTurtle.domain.virusTotal;

import lombok.Data;

@Data
public class LastAnalysisStats {
    private Integer harmless;
    private Integer malicious;
    private Integer suspicious;
    private Integer undetected;
    private Integer timeout;

}
