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

    final OAuthCredentialsProvider provider =
            new OAuthCredentialsProviderBuilder()
                    .clientId("zeebe")
                    .clientSecret("zecret")
                    .audience("zeebe-api")
                    .authorizationServerUrl("http://localhost:18080/auth/realms/camunda-platform/protocol/openid-connect/token")
                    .build();

    final ZeebeClient client =
            new ZeebeClientBuilderImpl()
                    .gatewayAddress("localhost:26500").usePlaintext()
                    .credentialsProvider(provider)
                    .build();

    System.out.println(client.newTopologyRequest().send().join().toString());

    //final ZeebeClientBuilder zeebeClientBuilder = ZeebeClient.newClientBuilder().credentialsProvider(new MyCredentialsProvider()).gatewayAddress(defaultAddress).usePlaintext();

    /*
    final ZeebeClientBuilder zeebeClientBuilder = ZeebeClient.newClientBuilder().gatewayAddress(defaultAddress).usePlaintext();

    final String decisionId = "Decision_1xuoniq";

    System.out.println("Evaluating decision");

    try (final ZeebeClient client = zeebeClientBuilder.build()) {


     //final DeploymentEvent deploymentEvent = client.newDeployResourceCommand().addResourceFromClasspath("decide-on-approval.dmn").send().join();

      //System.out.println("Deployment created with key: " + deploymentEvent.getKey());


      final EvaluateDecisionResponse decisionEvaluation =
              client
                      .newEvaluateDecisionCommand()
                      .decisionId(decisionId)
                      .variables("{\"invoiceValue\":300,\"department\":\"IT\"}")
                      .send()
                      .join();

      System.out.println("Decision evaluation result: " + decisionEvaluation.getDecisionOutput());

    }

     */
    }
  }

