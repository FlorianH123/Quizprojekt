package exception;

import model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by Florian on 26.06.2017.
 * Exception Klasse um alle nicht behandelten Exceptions zu fangen
 */
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{
    @Override
    public Response toResponse(Throwable ex) {
        ErrorMessage errorMsg = new ErrorMessage(ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMsg)
                .build();
    }
}
