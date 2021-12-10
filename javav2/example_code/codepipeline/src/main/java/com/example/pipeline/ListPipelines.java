//snippet-sourcedescription:[ListPipelines.java demonstrates how to retrieve all pipelines.]
//snippet-keyword:[SDK for Java 2.0]
//snippet-keyword:[Code Sample]
//snippet-service:[AWS CodePipeline]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[09/29/2021]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.pipeline;

// snippet-start:[pipeline.java2.list_pipelines.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.codepipeline.CodePipelineClient;
import software.amazon.awssdk.services.codepipeline.model.CodePipelineException;
import software.amazon.awssdk.services.codepipeline.model.ListPipelinesResponse;
import software.amazon.awssdk.services.codepipeline.model.PipelineSummary;
import java.util.List;
// snippet-end:[pipeline.java2.list_pipelines.import]

/**
 * To run this Java V2 code example, ensure that you have setup your development environment, including your credentials.
 *
 * For information, see this documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class ListPipelines {

    public static void main(String[] args) {

        Region region = Region.US_EAST_1;
        CodePipelineClient pipelineClient = CodePipelineClient.builder()
                .region(region)
                .build();

        getAllPipelines(pipelineClient);
        pipelineClient.close();
    }

    // snippet-start:[pipeline.java2.list_pipelines.main]
    public static void getAllPipelines(CodePipelineClient pipelineClient) {

        try {

            ListPipelinesResponse response = pipelineClient.listPipelines();
            List<PipelineSummary> pipelines =  response.pipelines();

            for (PipelineSummary pipeline: pipelines) {
                System.out.println("The name of the pipeline is "+pipeline.name());
            }

        } catch (CodePipelineException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    // snippet-end:[pipeline.java2.list_pipelines.main]
}
