//snippet-sourcedescription:[DeleteSamplingRule.java demonstrates how to delete an AWS X-Ray Service rule.]
//snippet-keyword:[SDK for Java 2.0]
//snippet-keyword:[Code Sample]
//snippet-service:[AWS X-Ray Service]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[09/29/2021]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.xray;

// snippet-start:[xray.java2_delete_rule.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.xray.XRayClient;
import software.amazon.awssdk.services.xray.model.DeleteSamplingRuleRequest;
import software.amazon.awssdk.services.xray.model.XRayException;
// snippet-end:[xray.java2_delete_rule.import]

/**
 * To run this Java V2 code example, ensure that you have setup your development environment, including your credentials.
 *
 * For information, see this documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class DeleteSamplingRule {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage: " +
                "   <ruleName>\n\n" +
                "Where:\n" +
                "   ruleName - the name of the rule to delete \n\n";

         if (args.length != 1) {
             System.out.println(USAGE);
             System.exit(1);
         }

        String ruleName = args[0];
        Region region = Region.US_EAST_1;
        XRayClient xRayClient = XRayClient.builder()
                .region(region)
                .build();

        deleteRule( xRayClient, ruleName );
    }

    // snippet-start:[xray.java2_delete_rule.main]
    public static void deleteRule( XRayClient xRayClient, String ruleName ) {

        try {
            DeleteSamplingRuleRequest ruleRequest = DeleteSamplingRuleRequest.builder()
                .ruleName(ruleName)
                .build();

            xRayClient.deleteSamplingRule(ruleRequest);
        } catch (XRayException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    // snippet-end:[xray.java2_delete_rule.main]
}
