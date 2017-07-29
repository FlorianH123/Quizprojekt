package rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import static constants.Rest_Constants.APLLICATION_PATH;

/**
 * Created by Florian on 13.06.2017.
 * Legt fest welche Arten von Resourcen an den Server weiter gegeben werden
 * wenn keine Resourcen angebeben sind == Klasse leer -> Per default: alle
 * Path: /webapi
 */

@ApplicationPath(APLLICATION_PATH)
public class ApplicationHandler extends Application {
}
