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
        headers.put(authHeaderkey, "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJTMU1SRmtLVkh5bVFiN0dkZE40RmF0RjE5TWlmaFdoRmZDSzdOLU5qWExjIn0.eyJleHAiOjE2ODA3OTMzODAsImlhdCI6MTY4MDc5MzA4MCwianRpIjoiNzE0YWFiYzctZDM2Zi00NGQ0LThlNGYtZmNiNzZlYjQ0ZGYyIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoxODA4MC9hdXRoL3JlYWxtcy9jYW11bmRhLXBsYXRmb3JtIiwiYXVkIjpbInplZWJlIiwiemVlYmUtYXBpIiwiYWNjb3VudCJdLCJzdWIiOiI1MWNiNzA4Ni1lNDI0LTRiZWYtYjUwOS1mYzBlZGZhNmQzZGQiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ6ZWViZSIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiRGVmYXVsdCB1c2VyIHJvbGUiXX0sInJlc291cmNlX2FjY2VzcyI6eyJ6ZWViZS1hcGkiOnsicm9sZXMiOlsid3JpdGU6KiJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJjbGllbnRJZCI6InplZWJlIiwiY2xpZW50SG9zdCI6IjE3Mi4zMS4wLjEiLCJwZXJtaXNzaW9ucyI6eyJ6ZWViZS1hcGkiOlsid3JpdGU6KiJdLCJhY2NvdW50IjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX0sInByZWZlcnJlZF91c2VybmFtZSI6InNlcnZpY2UtYWNjb3VudC16ZWViZSIsImNsaWVudEFkZHJlc3MiOiIxNzIuMzEuMC4xIn0.gEJ4FNnM-olKC7Bvf4MSJrlNkcvWR62Bdz-T5VBkYUVZEs9g76Kjf3l7nLSkZeugTVJpF134k3C9rqj49zhiLnKIpqvehRkGpZgEguNV-IaAd_DKHWeN4D4-hKqcjywLG7LT8Dwg5RlUeiMQEm87h_t9W1B5IuPso04IhB1BkITnj723yPTFzYlUVTMZ0ybcm9HTxc-rctOG6pcPWD67Pc7-uySguEhxvoiCwzjunQRl-8qPYmghKoBVh5Yr9xXsdwxTioKzsR39UkCMiurh9kVCRRriVnH2OGkfXkQ3Z4KsmbOvsSSIk-9r2f0TTK1cbnpouXJFZAfqnjpKfuyo5w");
    }

    /**
     * Retries request if it failed with a timeout.
     */
    @Override
    public boolean shouldRetryRequest(final Throwable throwable) {
        return ((StatusRuntimeException) throwable).getStatus() == Status.DEADLINE_EXCEEDED;
    }
}
