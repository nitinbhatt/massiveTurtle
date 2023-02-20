package com.nitin.massiveTurtle.service.virusTotal;

import com.nitin.massiveTurtle.client.virusTotal.VirusTotalClient;
import com.nitin.massiveTurtle.domain.IpAddressResponse;
import com.nitin.massiveTurtle.domain.virusTotal.*;
import com.nitin.massiveTurtle.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class VirusTotalService {

    @Autowired
    private VirusTotalClient vtClient;

    public void setVtClient(VirusTotalClient vtClient) {
        this.vtClient = vtClient;
    }

    /**
     * Build {@link IpAddressResponse} from {@link IpReportData} received from HTTP request
     * @param data
     * @return
     */
    Optional<IpAddressResponse> buildIpAddressResponse(final IpReportData data) {
        if (data == null || data.getAttributes() == null) {
            return Optional.empty();
        }

        IpReportAttributes attribute = data.getAttributes();

        return Optional.of(
                IpAddressResponse.builder()
                        .ipAddress(data.getId())
                        .country(attribute.getCountry())
                        .network(attribute.getNetwork())
                        .tags(attribute.getTags())
                        .lastAnalysisStats(attribute.getLastAnalysisStats())
                        .build());
    }

    public Optional<IpAddressResponse> getIpAddressReport(final String ipAddress) {
        Optional<IpReportResponse> responseOptional = vtClient.getIpReport(ipAddress);
        return responseOptional.isPresent() && responseOptional.get().getData() != null
                ? buildIpAddressResponse(responseOptional.get().getData())
                : Optional.empty();
    }

    public List<IpComment> getCommentsForIp(final String ipAddress) {

        Optional<IpCommentsResponse> responseOptional = vtClient.getIpComments(ipAddress);
        if (responseOptional.isPresent()) {
            IpCommentsResponse response = responseOptional.get();
            List<IpComment> comments = new LinkedList<>();
            response.getData().forEach(d -> {
                IpCommentsAttributes attr = d.getAttributes();
                if (attr != null) {
                    IpComment comment = new IpComment();
                    comment.setText(attr.getText());
                    comment.setDate(DateUtils.convertToDate(attr.getDate()*1000));
                    comments.add(comment);
                }
            });
            return comments;
        }
        else {
            return Collections.emptyList();
        }
    }

    public List<IpResolution> getResolutionsForIp(final String ipAddress) {

        Optional<IpResolutionResponse> responseOptional = vtClient.getIpResolutions(ipAddress);
        if (responseOptional.isPresent()) {
            IpResolutionResponse response = responseOptional.get();
            List<IpResolution> resolutions = new LinkedList<>();
            response.getData().forEach(d -> {
                IpResolutionAttributes attr = d.getAttributes();
                if (attr != null) {
                    IpResolution resolution = new IpResolution();
                    resolution.setHostName(attr.getHostName());
                    resolution.setDate(DateUtils.convertToDate(attr.getDate()*1000));
                    resolutions.add(resolution);
                }
            });
            return resolutions;

        }
        else {
            return Collections.emptyList();
        }
    }
}
