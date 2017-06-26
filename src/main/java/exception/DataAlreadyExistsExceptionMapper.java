package exception;

import model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Florian on 26.06.2017.
 * Mapper fuer DataAlreadyExistsException
 */

@Provider
public class DataAlreadyExistsExceptionMapper implements ExceptionMapper<DataAlreadyExistsException>{

    @Override
    public Response toResponse(DataAlreadyExistsException ex) {
        ErrorMessage errorMsg = new ErrorMessage(ex.getMessage(), 404);
        return Response.status(Response.Status.FOUND)
                .entity(errorMsg)
                .build();
    }
}
