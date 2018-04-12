package es.enxenio.GGAL1701.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Redirección de /admin a la página de login.
 * Created by crodriguez on 12/05/2016.
 */
@Controller
@RequestMapping("/admin")
public class PrincipalAdminController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getLoginPage() {
        return "redirect:/#/login";
    }

}
