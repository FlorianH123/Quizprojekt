package exception;

import model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Cedric on 05.07.2017.
 * Mapper fuer EmailNotFoundException
 */

@Provider
public class EmailNotFoundExceptionMapper implements ExceptionMapper<EmailNotFoundException>{
    @Override
    public Response toResponse(EmailNotFoundException e) {
        //ErrorMessage errorMsg = new ErrorMessage(e.getMessage(), Response.Status.NOT_FOUND.getStatusCode());

        return Response.status(Response.Status.NOT_FOUND)
                //.entity(errorMsg)
                .entity(e.getMessage())
                .build();
    }
}
