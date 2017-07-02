package rest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by Cedric on 02.07.2017.
 */
@Provider
public class Filter implements ContainerRequestFilter{
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    }
}
