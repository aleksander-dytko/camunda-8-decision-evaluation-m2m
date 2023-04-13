package io.camunda.zeebe.example.decision;

import io.camunda.zeebe.client.CredentialsProvider;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;

public class MyCredentialsProvider implements CredentialsProvider {

    /**
     * Adds a token to the Authorization header of a gRPC call.
     */
    @Override
    public void applyCredentials(final Metadata headers) {
        final Metadata.Key<String> authHeaderkey = Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);
        headers.put(authHeaderkey, "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJVczhhb2d3UFhRQURBYXdGdDMyYkdFQ0dRV196dUZoUlpiRHF5dV9zTnFZIn0.eyJleHAiOjE2ODE0MDYwNjEsImlhdCI6MTY4MTQwNTc2MSwianRpIjoiNGMwZDYxMmYtNDRlOC00ZmI2LWIyMWYtODdlNGVmNmI1ZjMzIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoxODA4MC9hdXRoL3JlYWxtcy9jYW11bmRhLXBsYXRmb3JtIiwiYXVkIjpbInplZWJlIiwiemVlYmUtYXBpIiwiYWNjb3VudCJdLCJzdWIiOiI5OWUyYzg4NC0xMmNjLTQyMWEtOWNhNi04MTg3ZGNiNTdmYzEiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ6ZWViZSIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiRGVmYXVsdCB1c2VyIHJvbGUiXX0sInJlc291cmNlX2FjY2VzcyI6eyJ6ZWViZS1hcGkiOnsicm9sZXMiOlsid3JpdGU6KiJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwiY2xpZW50SG9zdCI6IjE3Mi4yMC4wLjEiLCJjbGllbnRJZCI6InplZWJlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwZXJtaXNzaW9ucyI6eyJ6ZWViZS1hcGkiOlsid3JpdGU6KiJdLCJhY2NvdW50IjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX0sInByZWZlcnJlZF91c2VybmFtZSI6InNlcnZpY2UtYWNjb3VudC16ZWViZSIsImNsaWVudEFkZHJlc3MiOiIxNzIuMjAuMC4xIn0.moRH8X47IRp0wphPEgrjBB5XSKX3phL36AgKfK5ThwbNyj9FNdJDv_QOYd20wKl_xhSSI4wHKPcMN17BDA-wDDu-MvTtrCnMEoIpdMTNL0NU1Nbs8gy20OBKBbBcCHW__GuMxxTCYszOYwbkFGz1DW5CUG7goycbiO0NjiZqCWF2wAi2Jzgu-xXv1L56bB5eueA2qOuls1rlQtG5tSul-w5ILvifuLkwrB-sR0W_vd-P90pvBY2ZlRLpZNwwn9j7_r26Dws6GE9pCVayU0bM8hazg7Sb-aQcZVchrUACrxNXTizdlS6EpCBS6K6V-0v1Yih3bbmygTzMC3us1ZseHA");
    }

    /**
     * Retries request if it failed with a timeout.
     */
    @Override
    public boolean shouldRetryRequest(final Throwable throwable) {
        return ((StatusRuntimeException) throwable).getStatus() == Status.DEADLINE_EXCEEDED;
    }
}
