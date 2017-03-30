/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.web.app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rogerioalves21
 */
@Path("/ola")
public class OlaMundoRs {
    
    /*@GET
    @Produces("text/plain")
    public String olaMundo() {
        return "Ola Mundo Rest!";
    }*/
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Login getLogin() {
        System.out.println("Passei aqui");
        Login login = new Login();
        login.setLogin("rogerio.rodrigues");
        login.setSenha("@Loureiro3");
        return login;
    }
    
}
