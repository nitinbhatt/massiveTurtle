package com.nitin.massiveTurtle.client.virusTotal;

import com.nitin.massiveTurtle.config.VirusTotalConfig;
import com.nitin.massiveTurtle.domain.virusTotal.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Optional;

@Slf4j
@Component
public class VirusTotalClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private VirusTotalConfig config;

    private static HttpHeaders headers;

    private static final String IP_REPORT_URL = "https://www.virustotal.com/api/v3/ip_addresses/%s";
    private static final String IP_COMMENTS_URL = "https://www.virustotal.com/api/v3/ip_addresses/%s/comments";
    private static final String IP_RESOLUTIONS_URL = "https://www.virustotal.com/api/v3/ip_addresses/%s/resolutions";

    @PostConstruct
    public void init() {
        headers = new HttpHeaders();
        headers.add("x-apikey", config.getApiKey());
    }

    /**
     * Get IP Report
     * @param ipAddress
     * @return
     */
    public Optional<IpReportResponse> getIpReport(final String ipAddress) {
        URI uri = URI.create(String.format(IP_REPORT_URL, ipAddress));

        try {
            RequestEntity<Void> entity = RequestEntity.get(uri)
                    .headers(headers).build();
            ResponseEntity<IpReportResponse> response = restTemplate.exchange(entity, IpReportResponse.class);

            return  response.getStatusCode().is2xxSuccessful() && response.getBody() != null
                    ? Optional.of(response.getBody())
                    : Optional.empty();
        }
        catch (RestClientException rEx) {
            log.error("Error fetching IP Report for {} {}", ipAddress, rEx.getStackTrace());
            return Optional.empty();
        }
    }

    /**
     * Get comments for an ip address
     * @param ipAddress
     * @return
     */
    public Optional<IpCommentsResponse> getIpComments(final String ipAddress) {

        URI uri = URI.create(String.format(IP_COMMENTS_URL, ipAddress));

        RequestEntity<Void> entity =RequestEntity.get(uri)
                .headers(headers).build();
        try {
            ResponseEntity<IpCommentsResponse> response = restTemplate.exchange(entity, IpCommentsResponse.class);
            return response.getStatusCode().is2xxSuccessful() && response.getBody() != null
                    ? Optional.of(response.getBody()) : Optional.empty();
        }
        catch (RestClientException rEx) {
            log.error("Error fetching comments data for {} {}", ipAddress, rEx.getStackTrace());
            return Optional.empty();
        }


    }

    /**
     * Get historic resolutions for an IP
     * @param ipAddress
     * @return
     */
    public Optional<IpResolutionResponse> getIpResolutions(final String ipAddress) {
        URI uri = URI.create(String.format(IP_RESOLUTIONS_URL, ipAddress));

        try {
            RequestEntity<Void> entity =RequestEntity.get(uri)
                    .headers(headers).build();
            ResponseEntity<IpResolutionResponse> response = restTemplate.exchange(entity, IpResolutionResponse.class);
            return response.getStatusCode().is2xxSuccessful() && response.getBody() != null
                    ? Optional.of(response.getBody()) : Optional.empty();

        }
        catch (RestClientException rEx) {
            log.error("Error fetching resolutions data for {} {}", ipAddress, rEx.getStackTrace());
            return Optional.empty();
        }
    }
}
