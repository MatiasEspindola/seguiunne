package net.iterart.seguiunne;

import java.util.List;
import net.iterart.seguiunne.model.DatosGralProyecto;
import net.iterart.seguiunne.model.Usuarios;
import net.iterart.seguiunne.model.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
//@ComponentScan({"net.iterart.seguiunne", "net.iterart.seguiunne.auth"}) // If our Controller class or Service class is not in the same packages we have //to add packages's name like this...directory(package) with main class
public class SeguiUnneApplication implements CommandLineRunner {

    @Autowired
    BCryptPasswordEncoder passEncoder;
    
    @Autowired
    IUsuariosService usuServ;

    @Bean
    BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(SeguiUnneApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//			ufs.deleteAll();
//			ufs.init();

        //Encript de claves:
//        List<Usuarios> datos = usuServ.findAll();
//        
//        for(Usuarios u : datos){
//            String pass = u.getClave();
//            String encPass = passEncoder.encode(pass);
//            u.setSalt(encPass);
//            usuServ.updateSalt(u.getPkIdUsu(), encPass);
//            System.out.println("Clave cambiada de: " + u.getClave() + " a " + u.getSalt());
//        }
//
//        String pass = "cgatti2017";
//
//        for (int i = 0; i < 2; i++) {
//            String encriptPass = passEncoder.encode(pass);
//            System.out.println("Password encriptada: ".concat(encriptPass));
//        }
    }
}
