package rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Created by Florian on 13.06.2017.
 * Legt fest welche Arten von Resourcen an den Server weiter gegeben werden
 * Per defualt: alle
 */
@ApplicationPath( "webapi" )
public class ApplicationHandler extends Application {
}
