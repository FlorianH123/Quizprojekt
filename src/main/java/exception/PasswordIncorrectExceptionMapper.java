package exception;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Cedric on 05.07.2017.
 * Mapper Klasse fuer PasswordIncorrectException
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class PasswordIncorrectExceptionMapper implements ExceptionMapper<PasswordIncorrectException>{
    @Override
    public Response toResponse(PasswordIncorrectException ex) {
        //ErrorMessage errorMsg = new ErrorMessage(e.getMessage(), Response.Status.NOT_FOUND.getStatusCode());

        return Response.status(Response.Status.NOT_FOUND)
                //.entity(errorMsg)
                .entity(model.ExceptionToJson.exceptionMessageToJson(ex))
                .build();
    }
}
