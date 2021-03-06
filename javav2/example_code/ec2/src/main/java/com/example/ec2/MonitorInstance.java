//snippet-sourcedescription:[MonitorInstance.java demonstrates how to toggle detailed monitoring for an Amazon Elastic Compute Cloud (Amazon EC2) instance.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon EC2]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[09/28/2021]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.ec2;

// snippet-start:[ec2.java2.monitor_instance.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.MonitorInstancesRequest;
import software.amazon.awssdk.services.ec2.model.UnmonitorInstancesRequest;
// snippet-end:[ec2.java2.monitor_instance.import]

/**
 * To run this Java V2 code example, ensure that you have setup your development environment, including your credentials.
 *
 * For information, see this documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class MonitorInstance {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "   <instanceId> <monitor>\n\n" +
                "Where:\n" +
                "   instanceId - an instance id value that you can obtain from the AWS Console. \n\n" +
                "   monitor - a monitoring status (true|false)";

        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String instanceId = args[0];
        boolean monitor = Boolean.valueOf(args[1]);

        Region region = Region.US_WEST_2;
        Ec2Client ec2 = Ec2Client.builder()
                .region(region)
                .build();

        if (monitor) {
            monitorInstance(ec2, instanceId);
        } else {
            unmonitorInstance(ec2, instanceId);
        }
        ec2.close();
    }

    // snippet-start:[ec2.java2.monitor_instance.main]
    public static void monitorInstance( Ec2Client ec2, String instanceId) {

        MonitorInstancesRequest request = MonitorInstancesRequest.builder()
                .instanceIds(instanceId).build();

        ec2.monitorInstances(request);
        System.out.printf(
                "Successfully enabled monitoring for instance %s",
                instanceId);
    }
    // snippet-end:[ec2.java2.monitor_instance.main]

    // snippet-start:[ec2.java2.monitor_instance.stop]
    public static void unmonitorInstance(Ec2Client ec2, String instanceId) {
        UnmonitorInstancesRequest request = UnmonitorInstancesRequest.builder()
                .instanceIds(instanceId).build();

        ec2.unmonitorInstances(request);

        System.out.printf(
                "Successfully disabled monitoring for instance %s",
                instanceId);
    }
    // snippet-end:[ec2.java2.monitor_instance.stop]
}