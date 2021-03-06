package exception;

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
        //ErrorMessage errorMsg = new ErrorMessage(ex.getMessage(), Response.Status.FOUND.getStatusCode());

        return Response.status(Response.Status.FOUND)
                //.entity(errorMsg)
                .entity(model.ExceptionToJson.exceptionMessageToJson(ex))
                .build();
    }
}
