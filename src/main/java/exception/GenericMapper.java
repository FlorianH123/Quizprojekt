package exception;

import model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Florian on 26.06.2017.
 * GenericMapper fuer nicht behandelte Exceptions
 */

@Provider
public class GenericMapper implements ExceptionMapper<Throwable>{
    @Override
    public Response toResponse(Throwable ex) {
        ErrorMessage errorMsg = new ErrorMessage("Schwerwiegender Fehler, versuchen Sie es später wieder!", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMsg)
                .build();
    }
}
