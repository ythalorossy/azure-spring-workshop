package azure.workshop.ad;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ADController {

    @GetMapping("Admin")
    @PreAuthorize("hasAuthority('APPROLE_Admin')")
    public String Admin() {
        return "Hello Admin";
    }

}
