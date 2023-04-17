/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH under
 * one or more contributor license agreements. See the NOTICE file distributed
 * with this work for additional information regarding copyright ownership.
 * Licensed under the Zeebe Community License 1.1. You may not use this file
 * except in compliance with the Zeebe Community License 1.1.
 */
package io.camunda.zeebe.example.decision;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.ZeebeClientBuilder;
import io.camunda.zeebe.client.api.response.DeploymentEvent;
import io.camunda.zeebe.client.api.response.EvaluateDecisionResponse;
import io.camunda.zeebe.client.impl.ZeebeClientBuilderImpl;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;

public final class EvaluateDecisionCreator {

  public static void main(final String[] args) {
    final String defaultAddress = "localhost:26500";
    final String decisionId = "Decision_1xuoniq";

    /*

    // Connect to local deployment. Assumes that authentication is disabled.
    final ZeebeClientBuilder zeebeClientBuilder = ZeebeClient.newClientBuilder().gatewayAddress(defaultAddress).usePlaintext();
    final ZeebeClient client = zeebeClientBuilder.build();

     */

    /*

    // Connect to local deployment with Bearer token in header. Assumes that authentication is enabled.
    final ZeebeClientBuilder zeebeClientBuilder = ZeebeClient.newClientBuilder().credentialsProvider(new MyCredentialsProvider()).gatewayAddress(defaultAddress).usePlaintext();
    final ZeebeClient client = zeebeClientBuilder.build();

     */


    // Connect to a local deployment with OAuthCredentialsProvider with Identity. Assumes authentication is enabled.
    final OAuthCredentialsProvider provider =
            new OAuthCredentialsProviderBuilder()
                    .clientId("zeebe")
                    .clientSecret("zecret")
                    .audience("zeebe-api")
                    .authorizationServerUrl("http://localhost:18080/auth/realms/camunda-platform/protocol/openid-connect/token")
                    .build();

    final ZeebeClient client =
            new ZeebeClientBuilderImpl()
                    .gatewayAddress(defaultAddress).usePlaintext()
                    .credentialsProvider(provider)
                    .build();


    System.out.println(client.newTopologyRequest().send().join().toString());

    System.out.println("Deploying decision definition");

     final DeploymentEvent deploymentEvent = client.newDeployResourceCommand().addResourceFromClasspath("decide-on-approval.dmn").send().join();

     System.out.println("Deployment created with key: " + deploymentEvent.getKey());

    System.out.println("Evaluating decision");
      final EvaluateDecisionResponse decisionEvaluation =
              client
                      .newEvaluateDecisionCommand()
                      .decisionId(decisionId)
                      .variables("{\"invoiceValue\":300,\"department\":\"IT\"}")
                      .send()
                      .join();

      System.out.println("Decision evaluation result: " + decisionEvaluation.getDecisionOutput());




    }
  }

