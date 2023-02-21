package com.nitin.massiveTurtle.service.virusTotal;


import com.nitin.massiveTurtle.client.virusTotal.VirusTotalClient;
import com.nitin.massiveTurtle.domain.IpAddressResponse;
import com.nitin.massiveTurtle.domain.virusTotal.*;
import com.nitin.massiveTurtle.utils.DateUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.collect.ImmutableList;

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
        return LastAnalysisStats.builder()
                .harmless(a)
                .malicious(b)
                .timeout(c)
                .suspicious(d)
                .build();
    }

    private IpReportAttributes buildIpReportAttributes(final String country, final String network, List<String> tags) {
        return IpReportAttributes.builder()
                .country(country)
                .tags(tags)
                .network(network)
                .build();
    }

    @Test
    public void testBuildIpAddressResponse() {

        IpReportAttributes attributes = buildIpReportAttributes("US", "_NETWORK_", ImmutableList.of("a", "b"));
        IpReportData data = IpReportData.builder().attributes(attributes).id("1.1.1.1").type("__TEST__").build();

        Optional<IpAddressResponse> responseOptional = virusTotalService.buildIpAddressResponse(data);
        Assert.assertTrue(responseOptional.isPresent());
        IpAddressResponse response = responseOptional.get();
        Assert.assertNotNull(response);

        Assert.assertEquals(response.getIpAddress(), "1.1.1.1");
        Assert.assertEquals(response.getNetwork(), ("_NETWORK_"));
        Assert.assertEquals(response.getTags().size() , 2);
        Assert.assertEquals(response.getCountry(), "US");
    }

    @Test
    public void testIpaddressReport() {

        IpReportAttributes attributes = IpReportAttributes.builder().tags(ImmutableList.of("A", "B")).network("_TEST_").build();
        IpReportData ipReportData = IpReportData.builder().type("IP Address").id("1.1.1.1").attributes(attributes). build();

        IpReportResponse response = IpReportResponse.builder().data(ipReportData).build();

        Mockito.doReturn(Optional.of(response)).when(vtClient).getIpReport("1.1.1.1");

        Optional<IpAddressResponse> responseOptional = virusTotalService.getIpAddressReport("1.1.1.1");
        Assert.assertTrue(responseOptional.isPresent());
        IpAddressResponse ipAddressResponse = responseOptional.get();
        Assert.assertNotNull(ipAddressResponse);
        Assert.assertEquals(ipAddressResponse.getTags().size(), 2);
        Assert.assertEquals(ipAddressResponse.getNetwork(), "_TEST_");
    }

    private IpComment buildIpComment(final long date, final String text) {
        IpComment comment = IpComment.builder().text(text).build();
        comment.setDate(DateUtils.convertToDate(date));
        return comment;
    }

    private IpCommentsData buildIpCommentsData(final long date, final String text) {

        IpCommentsAttributes attributes = IpCommentsAttributes.builder()
                .date(date).text(text).build();

        return IpCommentsData.builder().attributes(attributes).build();
    }

    @Test
    public void testIpAddressComments() {
        List<IpCommentsData> ipCommentsData = ImmutableList.of(
                buildIpCommentsData(1611122234, "a"),
                buildIpCommentsData(1674194234, "b")
        );
        IpCommentsResponse response = IpCommentsResponse.builder().data(ipCommentsData).build();

        Mockito.doReturn(Optional.of(response)).when(vtClient).getIpComments("1.1.1.1");

        List<IpComment> commentList = virusTotalService.getCommentsForIp("1.1.1.1");
        Assert.assertFalse(commentList.isEmpty());
        Assert.assertEquals(commentList.size(), 2);
        Assert.assertEquals(commentList.get(0).getText(), "a");
        Assert.assertEquals(commentList.get(0).getDate(), "2021-01-19");
        Assert.assertEquals(commentList.get(1).getText(), "b");
        Assert.assertEquals(commentList.get(1).getDate(), "2023-01-19");
    }
}