package exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Cedric on 05.07.2017.
 */
@Provider
public class PasswordIncorrectExceptionMapper implements ExceptionMapper<PasswordIncorrectException>{
    @Override
    public Response toResponse(PasswordIncorrectException e) {
        //ErrorMessage errorMsg = new ErrorMessage(ex.getMessage(), Response.Status.NOT_FOUND.getStatusCode());

        return Response.status(Response.Status.NOT_FOUND)
                //.entity(errorMsg)
                .entity(e.getMessage())
                .build();
    }
}