package com.nitin.massiveTurtle.service.virusTotal;


import com.nitin.massiveTurtle.client.virusTotal.VirusTotalClient;
import com.nitin.massiveTurtle.domain.IpAddressResponse;
import com.nitin.massiveTurtle.domain.virusTotal.*;
import com.nitin.massiveTurtle.utils.DateUtils;
import graphql.Assert;
import graphql.com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class VirusTotalServiceTest {

    @Mock
    private VirusTotalClient vtClient;

    private VirusTotalService virusTotalService;

    @Before
    public void setUp() throws Exception {
        virusTotalService = new VirusTotalService();
        virusTotalService.setVtClient(vtClient);
    }

    private LastAnalysisStats buildLastAnalysisStats(int a, int b, int c, int d) {
        LastAnalysisStats lastAnalysisStats = new LastAnalysisStats();
        lastAnalysisStats.setHarmless(1);
        lastAnalysisStats.setMalicious(2);
        lastAnalysisStats.setTimeout(4);
        lastAnalysisStats.setSuspicious(8);
        return lastAnalysisStats;
    }

    private IpReportAttributes buildIpReportAttributes(final String country, final String network, List<String> tags) {
        IpReportAttributes attributes = new IpReportAttributes();
        attributes.setCountry(country);
        attributes.setTags(tags);
        attributes.setNetwork(network);
        return attributes;
    }

    @Test
    public void testBuildIpAddressResponse() {

        IpReportData data = new IpReportData();
        IpReportAttributes attributes = buildIpReportAttributes("US", "_NETWORK_", ImmutableList.of("a", "b"));
        data.setAttributes(attributes);
        data.setId("1.1.1.1");
        data.setType("__TEST__");

        Optional<IpAddressResponse> responseOptional = virusTotalService.buildIpAddressResponse(data);
        Assert.assertTrue(responseOptional.isPresent());
        IpAddressResponse response = responseOptional.get();
        Assert.assertNotNull(response);

        Assert.assertTrue(response.getIpAddress().equals("1.1.1.1"));
        Assert.assertTrue(response.getNetwork().equals("_NETWORK_"));
        Assert.assertTrue(response.getTags().size() == 2);
        Assert.assertTrue(response.getCountry().equals("US"));
    }

    @Test
    public void testIpaddressReport() {
        IpReportResponse response = new IpReportResponse();
        IpReportData ipReportData = new IpReportData();
        ipReportData.setType("IP Address");
        ipReportData.setId("1.1.1.1");
        IpReportAttributes attributes = new IpReportAttributes();
        attributes.setTags(ImmutableList.of("A", "B"));
        attributes.setNetwork("_TEST_");
        ipReportData.setAttributes(attributes);
        response.setData(ipReportData);

        Mockito.doReturn(Optional.of(response)).when(vtClient).getIpReport("1.1.1.1");

        Optional<IpAddressResponse> responseOptional = virusTotalService.getIpAddressReport("1.1.1.1");
        Assert.assertTrue(responseOptional.isPresent());
        IpAddressResponse ipAddressResponse = responseOptional.get();
        Assert.assertNotNull(ipAddressResponse);
        Assert.assertTrue(ipAddressResponse.getTags().size() == 2);
        Assert.assertTrue(ipAddressResponse.getNetwork().equals("_TEST_"));
    }

    private IpComment buildIpComment(final long date, final String text) {
        IpComment comment = new IpComment();
        comment.setDate(DateUtils.convertToDate(date));
        comment.setText(text);
        return comment;
    }

    private IpCommentsData buildIpCommentsData(final long date, final String text) {
        IpCommentsData data = new IpCommentsData();
        IpCommentsAttributes attributes = new IpCommentsAttributes();
        attributes.setDate(date);
        attributes.setText(text);
        data.setAttributes(attributes);
        return data;
    }

    @Test
    public void testIpAddressComments() {

        IpCommentsResponse response = new IpCommentsResponse();
        List<IpCommentsData> ipCommentsData = ImmutableList.of(
                buildIpCommentsData(1611122234, "a"),
                buildIpCommentsData(1674194234, "b")
        );
        response.setData(ipCommentsData);

        Mockito.doReturn(Optional.of(response)).when(vtClient).getIpComments("1.1.1.1");

        List<IpComment> commentList = virusTotalService.getCommentsForIp("1.1.1.1");
        Assert.assertFalse(commentList.isEmpty());
        Assert.assertTrue(commentList.size() == 2);
        Assert.assertTrue(commentList.get(0).getText().equals("a"));
        Assert.assertTrue(commentList.get(0).getDate().equals("2021-01-19"));
        Assert.assertTrue(commentList.get(1).getText().equals("b"));
        Assert.assertTrue(commentList.get(1).getDate().equals("2023-01-19"));
    }
}