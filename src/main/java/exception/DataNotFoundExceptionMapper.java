package exception;

import model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Florian on 26.06.2017.
 * Mapper fuer DataNotFoundException
 */

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
    @Override
    public Response toResponse(DataNotFoundException ex) {
        ErrorMessage errorMsg = new ErrorMessage(ex.getMessage(), Response.Status.NOT_FOUND.getStatusCode());

        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMsg)
                .build();
    }
}
