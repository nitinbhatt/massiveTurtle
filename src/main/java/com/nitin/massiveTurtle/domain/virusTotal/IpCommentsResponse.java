package com.nitin.massiveTurtle.domain.virusTotal;

import lombok.Data;

import java.util.List;

@Data
public class IpCommentsResponse {
    private List<IpCommentsData> data;
}
