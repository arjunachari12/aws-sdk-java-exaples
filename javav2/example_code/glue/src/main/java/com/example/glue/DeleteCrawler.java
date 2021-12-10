//snippet-sourcedescription:[DeleteCrawler.java demonstrates how to delete an AWS Glue crawler.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-keyword:[AWS Glue]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[09/28/2021]
//snippet-sourceauthor:[scmacdon AWS]
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.glue;

//snippet-start:[glue.java2.delete_crawler.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.glue.GlueClient;
import software.amazon.awssdk.services.glue.model.DeleteCrawlerRequest;
import software.amazon.awssdk.services.glue.model.GlueException;
//snippet-end:[glue.java2.delete_crawler.import]

/**
 * To run this Java V2 code example, ensure that you have setup your development environment, including your credentials.
 *
 * For information, see this documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class DeleteCrawler {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    <crawlerName>\n\n" +
                "Where:\n" +
                "    crawlerName - the name of the crawler. \n" ;

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String crawlerName = args[0] ;
        Region region = Region.US_EAST_1;
        GlueClient glueClient = GlueClient.builder()
                .region(region)
                .build();

        deleteSpecificCrawler(glueClient, crawlerName);
        glueClient.close();
    }

    //snippet-start:[glue.java2.delete_crawler.main]
    public static void deleteSpecificCrawler(GlueClient glueClient, String crawlerName) {

        try {
            DeleteCrawlerRequest deleteCrawlerRequest = DeleteCrawlerRequest.builder()
                    .name(crawlerName)
                    .build();

            glueClient.deleteCrawler(deleteCrawlerRequest);
            System.out.println(crawlerName +" was deleted");

        } catch (GlueException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
    //snippet-end:[glue.java2.delete_crawler.main]
}