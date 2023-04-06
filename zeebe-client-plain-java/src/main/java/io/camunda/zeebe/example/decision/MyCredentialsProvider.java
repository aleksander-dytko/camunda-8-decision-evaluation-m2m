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
        headers.put(authHeaderkey, "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJTMU1SRmtLVkh5bVFiN0dkZE40RmF0RjE5TWlmaFdoRmZDSzdOLU5qWExjIn0.eyJleHAiOjE2ODA3OTQxODIsImlhdCI6MTY4MDc5Mzg4MiwianRpIjoiYjE0YTFlNjgtMjcyNS00ZTljLTk1YmEtMWIzYWE4YmFhN2I4IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoxODA4MC9hdXRoL3JlYWxtcy9jYW11bmRhLXBsYXRmb3JtIiwiYXVkIjpbInplZWJlIiwiemVlYmUtYXBpIiwiYWNjb3VudCJdLCJzdWIiOiI1MWNiNzA4Ni1lNDI0LTRiZWYtYjUwOS1mYzBlZGZhNmQzZGQiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ6ZWViZSIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiRGVmYXVsdCB1c2VyIHJvbGUiXX0sInJlc291cmNlX2FjY2VzcyI6eyJ6ZWViZS1hcGkiOnsicm9sZXMiOlsid3JpdGU6KiJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJjbGllbnRJZCI6InplZWJlIiwiY2xpZW50SG9zdCI6IjE3Mi4zMS4wLjEiLCJwZXJtaXNzaW9ucyI6eyJ6ZWViZS1hcGkiOlsid3JpdGU6KiJdLCJhY2NvdW50IjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX0sInByZWZlcnJlZF91c2VybmFtZSI6InNlcnZpY2UtYWNjb3VudC16ZWViZSIsImNsaWVudEFkZHJlc3MiOiIxNzIuMzEuMC4xIn0.xPoE7aJCKb5R3ZkQKygK-5G0umQUC-dI1nvJwIcbXQTnIiiV8bct9YTWFwn5uxbgnTDLAqDLSl82lBqXbqViqZ4uVxMXgkiqrcYOFVec0U2McOKb_vSumpIdhly02HeN9G6E2s7jsdPX942OLCbR7OVpQefkVdyhCST1GGUIK8pr6qVWT5-cBv7t1i3gNZVmmZyODB-raMa7Ygk4bb3or8vD0EfWXNm2Q0JmHYhZHgHtqdyZ8qtXLKaDfFU87OqVl_OlqpIZYBBN3DGF9Y8xtToXm875w2DgtRQExEds0U4Ca2gU2jH0_Lqt5ocynFX00Y0ndj_X5DIITKZBIkugkQ");
    }

    /**
     * Retries request if it failed with a timeout.
     */
    @Override
    public boolean shouldRetryRequest(final Throwable throwable) {
        return ((StatusRuntimeException) throwable).getStatus() == Status.DEADLINE_EXCEEDED;
    }
}
