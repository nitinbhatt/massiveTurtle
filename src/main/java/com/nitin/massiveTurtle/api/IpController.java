package com.nitin.massiveTurtle.api;

import com.nitin.massiveTurtle.domain.IpAddressResponse;
import com.nitin.massiveTurtle.domain.virusTotal.IpComment;
import com.nitin.massiveTurtle.domain.virusTotal.IpMetaData;
import com.nitin.massiveTurtle.domain.virusTotal.IpResolution;
import com.nitin.massiveTurtle.service.virusTotal.VirusTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class IpController {
    @Autowired
    private VirusTotalService vtService;

    @SchemaMapping
    public List<IpComment> comments(IpAddressResponse ipAddress) {
        return vtService.getCommentsForIp(ipAddress.getIpAddress());
    }

    @SchemaMapping
    public List<IpResolution> resolutions(IpAddressResponse ipAddress) {
        return vtService.getResolutionsForIp(ipAddress.getIpAddress());
    }

    /**
     * Get details of an IP Address by calling IP Report endpoint
     * @param ipAddress IP Address to get the information for
     * @return Gets a complete IP Report
     */
    @QueryMapping
    public Optional<IpAddressResponse> ipAddressDetails(@Argument final String ipAddress) {
        return vtService.getIpAddressReport(ipAddress);
    }

    /**
     * Retrieve metadata information available about an IP address
     * @param ipAddress Address to get information for
     * @return Meta information (Comments and resolutions)
     */
    @QueryMapping
    public Optional<List<IpMetaData>> ipAddressInfo(@Argument final String ipAddress) {
        List<IpMetaData> ret = new LinkedList<>();
        ret.addAll(vtService.getCommentsForIp(ipAddress));
        ret.addAll(vtService.getResolutionsForIp(ipAddress));
        return Optional.of(ret);
    }
}
