package exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Darth Vader on 21.07.2017.
 * Exceptionmapper fuer LevelCantBeCreatedException
 */
@Provider
 public class LevelCantBeCreatedExceptionMapper implements ExceptionMapper<LevelCantBeCreatedException> {

        @Override
        public Response toResponse(LevelCantBeCreatedException ex) {
            //ErrorMessage errorMsg = new ErrorMessage(ex.getMessage(), Response.Status.FOUND.getStatusCode());

            return Response.status(Response.Status.FOUND)
                    //.entity(errorMsg)
                    .entity(model.ExceptionToJson.exceptionMessageToJson(ex))
                    .build();
        }
    }

