package net.iterart.seguiunne.controller;

//import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.iterart.seguiunne.model.Actividades;
import net.iterart.seguiunne.model.AdminsFacultades;
import net.iterart.seguiunne.model.DatosGralProyecto;
import net.iterart.seguiunne.model.Localidades;
import net.iterart.seguiunne.model.Proyectos;
import net.iterart.seguiunne.model.Tareas;
import net.iterart.seguiunne.model.Usuarios;
import net.iterart.seguiunne.model.service.IActividadesService;
import net.iterart.seguiunne.model.service.IAdminsFacultadesService;
import net.iterart.seguiunne.model.service.IDatosGralProyectoService;
import net.iterart.seguiunne.model.service.IFacultadesService;
import net.iterart.seguiunne.model.service.ILocalidadesService;
import net.iterart.seguiunne.model.service.IProvinciasService;
import net.iterart.seguiunne.model.service.IProyectosService;
import net.iterart.seguiunne.model.service.ITareasService;
import net.iterart.seguiunne.model.service.IUsuariosService;
import org.springframework.security.access.annotation.Secured;

@Controller
@RequestMapping("/tareas")
@SessionAttributes("tarea")
@Secured({"ROLE_USUARIO", "ROLE_UNIDAD"})
public class TareasController {

    @Autowired
    IUsuariosService usuServ;

    @Autowired
    private IProyectosService proyServ;

    @Autowired
    private ITareasService tarServ;

    @Autowired
    private IActividadesService actServ;

    @Autowired
    private ILocalidadesService locServ;

    @Autowired
    private IProvinciasService proServ;

    @Autowired
    private IDatosGralProyectoService datosService;

    @Autowired
    private IAdminsFacultadesService admServ;

    @Autowired
    private IFacultadesService facServ;

    Actividades actividad;

    private boolean reprogramado;

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("/listar/{actId}")
    public String listar(@PathVariable(value = "actId") Integer actId, Model m, RedirectAttributes flash, HttpServletRequest request) {

        List<Tareas> tareas = tarServ.fetchByActividad(actId);
        Usuarios user = new Usuarios();
        user = usuServ.findByUsuario(request.getRemoteUser());

        if (tareas.size() > 0) {
            m.addAttribute("titulo", "Tareas Programadas en la Actividad ");
            m.addAttribute("actividad", tareas.get(0).getFkIdAct());
            // m.addAttribute("user", tareas.get(0).getFkIdAct().getFkIdPro().getFkIdDat().getFkIdUsu().getUsuario());
            m.addAttribute("tareas", tareas);

            m.addAttribute("user", user);

            return "tareas/listar";
        } else {
            flash.addAttribute("msg", "Actividad sin Tareas Programadas aún...");
            return null;
        }
    }

    public String verMapa() {

        return "";
    }

    @Secured({"ROLE_USUARIO"})
    @GetMapping(value = "/cargar-localidades/{term}", produces = "application/json")
    @JsonIgnore
    public @ResponseBody
    List<Localidades> cargarLocalidades(@PathVariable String term) {
        // List<Localidades> lista = locServ.findByNombre(term);
        return locServ.findByNombre(term);
    }

    @Secured({"ROLE_USUARIO"})
    @GetMapping(value = "/form/{actId}")
    public String crear(@PathVariable(value = "actId") Integer actId, Model model, RedirectAttributes flash, HttpServletRequest request) {

        // Buscar actividad de la tarea:
        actividad = actServ.findByIdInt(actId);

        // List<Localidades>localidades = locServ.findAll();
        //List<Provincias> provincias = proServ.findAll();
        //
        // for(Localidades l: localidades){
        // provincias.add(l.getFkIdPrv());
        // }
        Date fechaActual = new Date();

        Tareas tarea = new Tareas();
        tarea.setFkIdAct(actividad);
        tarea.setFecIni(fechaActual);
        tarea.setFecFin(fechaActual);
        tarea.setAtrasada(false);
        tarea.setFin(false);

        model.addAttribute("titulo", "Nueva Tarea");
        // model.addAttribute("user", tarea.getFkIdAct().getFkIdPro().getFkIdDat().getFkIdUsu().getUsuario());
        model.addAttribute("tarea", tarea);

        // Agregado por @MatiasEspindola
        Usuarios user = new Usuarios();
        user = usuServ.findByUsuario(request.getRemoteUser());

        List<DatosGralProyecto> datos = null;

        List<AdminsFacultades> admins = admServ.findAll();

        if (user.getFkIdRol().getPkIdRol() == 3) {
            datos = datosService.fetchByUser(user);
        } else if (user.getFkIdRol().getPkIdRol() == 2) {
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getFkIdUsu().getPkIdUsu().equals(user.getPkIdUsu())) {
                    datos = datosService.fetchByFacultad(facServ.findById(admins.get(i).getFkIdFac().getPkIdFac()));
                }
            }
        } else {
            datos = datosService.buscarTodo();
        }

        List<Proyectos> proyectos = proyServ.findAll();
        List<Actividades> totalActividades = actServ.findAll();
        ArrayList<Tareas> listarTA = new ArrayList<>();
        ArrayList<Tareas> listarTR = new ArrayList<>();

        //Agrega a las Notificaciones la lista de Tareas Atrasadas
        for (int i = 0; i < proyectos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (proyectos.get(i).getFkIdDat().getPkIdDat().equals(datos.get(j).getPkIdDat())) {
                    for (int k = 0; k < totalActividades.size(); k++) {
                        if (totalActividades.get(k).getFkIdPro().getPkIdPro().equals(proyectos.get(i).getPkIdPro())) {
                            listarTA.addAll(tarServ.fetchByTareaAtrasada(totalActividades.get(k).getPkIdAct()));
                            listarTR.addAll(tarServ.fetchByTareaReprogramada(totalActividades.get(k).getPkIdAct()));
                        }
                    }
                }
            }
        }

        model.addAttribute("listarTR", listarTR);
        model.addAttribute("cant_repro", listarTR.size());
        model.addAttribute("user", user);
        model.addAttribute("fecha", fechaActual.getTime());
        model.addAttribute("tareas_atras", listarTA.size());
        model.addAttribute("listarTA", listarTA);

        return "tareas/form";
    }

    @Secured({"ROLE_USUARIO"})
    @GetMapping("/edit/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model, HttpServletRequest request) {
        Tareas tarea = null;

        if (id > 0) {
            tarea = tarServ.findById(id);
        } else {
            tarea = new Tareas();
        }

        model.put("titulo", "Editar Tarea");
        model.put("tarea", tarea);
        // model.put("user", tarea.getFkIdAct().getFkIdPro().getFkIdDat().getFkIdUsu().getUsuario());

        // Agregado por @MatiasEspindola
        Usuarios user = new Usuarios();
        user = usuServ.findByUsuario(request.getRemoteUser());

        List<DatosGralProyecto> datos = null;

        List<AdminsFacultades> admins = admServ.findAll();

        if (user.getFkIdRol().getPkIdRol() == 3) {
            datos = datosService.fetchByUser(user);
        } else if (user.getFkIdRol().getPkIdRol() == 2) {
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getFkIdUsu().getPkIdUsu().equals(user.getPkIdUsu())) {
                    datos = datosService.fetchByFacultad(facServ.findById(admins.get(i).getFkIdFac().getPkIdFac()));
                }
            }
        } else {
            datos = datosService.buscarTodo();
        }

        List<Proyectos> proyectos = proyServ.findAll();
        List<Actividades> totalActividades = actServ.findAll();
        ArrayList<Tareas> listarTA = new ArrayList<>();
        ArrayList<Tareas> listarTR = new ArrayList<>();

        //Agrega a las Notificaciones la lista de Tareas Atrasadas
        for (int i = 0; i < proyectos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (proyectos.get(i).getFkIdDat().getPkIdDat().equals(datos.get(j).getPkIdDat())) {
                    for (int k = 0; k < totalActividades.size(); k++) {
                        if (totalActividades.get(k).getFkIdPro().getPkIdPro().equals(proyectos.get(i).getPkIdPro())) {
                            listarTA.addAll(tarServ.fetchByTareaAtrasada(totalActividades.get(k).getPkIdAct()));
                            listarTR.addAll(tarServ.fetchByTareaReprogramada(totalActividades.get(k).getPkIdAct()));
                        }
                    }
                }
            }
        }

        model.put("listarTR", listarTR);
        model.put("cant_repro", listarTR.size());
        model.put("user", user);

        Date fechaActual = new Date();

        model.put("fecha", fechaActual.getTime());
        model.put("tareas_atras", listarTA.size());
        model.put("listarTA", listarTA);

        return "tareas/edit";
    }

    @Secured({"ROLE_USUARIO"})
    @GetMapping("/reprogramar/{id}")
    public String reprogramar(@PathVariable(value = "id") Integer id, Map<String, Object> model, HttpServletRequest request) {
        Tareas tarea = null;

        if (id > 0) {
            tarea = tarServ.findById(id);
        } else {
            tarea = new Tareas();
        }

        model.put("titulo", "Reprogramar Tarea");
        model.put("tarea", tarea);
        // model.put("user", tarea.getFkIdAct().getFkIdPro().getFkIdDat().getFkIdUsu().getUsuario());

        // Agregado por @MatiasEspindola
        Usuarios user = new Usuarios();
        user = usuServ.findByUsuario(request.getRemoteUser());

        List<DatosGralProyecto> datos = null;

        List<AdminsFacultades> admins = admServ.findAll();

        if (user.getFkIdRol().getPkIdRol() == 3) {
            datos = datosService.fetchByUser(user);
        } else if (user.getFkIdRol().getPkIdRol() == 2) {
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getFkIdUsu().getPkIdUsu().equals(user.getPkIdUsu())) {
                    datos = datosService.fetchByFacultad(facServ.findById(admins.get(i).getFkIdFac().getPkIdFac()));
                }
            }
        } else {
            datos = datosService.buscarTodo();
        }

        List<Proyectos> proyectos = proyServ.findAll();
        List<Actividades> totalActividades = actServ.findAll();
        ArrayList<Tareas> listarTA = new ArrayList<>();
        ArrayList<Tareas> listarTR = new ArrayList<>();

        //Agrega a las Notificaciones la lista de Tareas Atrasadas
        for (int i = 0; i < proyectos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (proyectos.get(i).getFkIdDat().getPkIdDat().equals(datos.get(j).getPkIdDat())) {
                    for (int k = 0; k < totalActividades.size(); k++) {
                        if (totalActividades.get(k).getFkIdPro().getPkIdPro().equals(proyectos.get(i).getPkIdPro())) {
                            listarTA.addAll(tarServ.fetchByTareaAtrasada(totalActividades.get(k).getPkIdAct()));
                            listarTR.addAll(tarServ.fetchByTareaReprogramada(totalActividades.get(k).getPkIdAct()));
                        }
                    }
                }
            }
        }

        model.put("listarTR", listarTR);
        model.put("cant_repro", listarTR.size());
        model.put("user", user);

        Date fechaActual = new Date();

        model.put("fecha", fechaActual.getTime());
        model.put("tareas_atras", listarTA.size());
        model.put("listarTA", listarTA);

        return "tareas/reprogramar";
    }

    @Secured({"ROLE_USUARIO"})
    @RequestMapping(value = "/reprogramar", method = RequestMethod.POST)
    public String tareaProgramada(@Valid Tareas tarea,
            BindingResult r,
            Model m,
            RedirectAttributes flash,
            SessionStatus st,
            HttpServletRequest request) {

        if (r.hasErrors()) {
            m.addAttribute("error", "Corrija los errores: ");
            return "tareas/form";
        }

        Localidades loc = locServ.findLocalidadById(1);

        if (tarea.getPkIdTar() != null) {
            tarServ.updateReprogramarTarea(tarea.getFecIni(), tarea.getFecFin(), tarea.getMotivo(), tarea.getPkIdTar());
            flash.addFlashAttribute("info", "Tarea reprogramada con éxito.");
        } else {

            tarea.setFkIdAct(actividad);
            tarea.setFkIdLoc(loc);
            tarea.setHab(true);
            tarServ.save(tarea);
            st.setComplete();
            flash.addFlashAttribute("info", "Nueva tarea guardada.");
        }
        // String redirectUrl = request.getScheme() + "://localhost:8082/actividades/ver/" + tarea.getFkIdAct().getPkIdAct().toString();
        String redirectUrl = request.getScheme() + "://31.220.52.31:8082/actividades/ver/" + tarea.getFkIdAct().getPkIdAct().toString();

        return "redirect:" + redirectUrl;
    }

    @Secured({"ROLE_USUARIO"})
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Tareas tarea,
            BindingResult r,
            Model m,
            RedirectAttributes flash,
            SessionStatus st,
            HttpServletRequest request) {

        if (r.hasErrors()) {
            m.addAttribute("error", "Corrija los errores: ");
            return "tareas/form";
        }

        Localidades loc = locServ.findLocalidadById(1);

        if (tarea.getPkIdTar() != null) {
            tarServ.updateTarea(tarea.getTitu(), tarea.getLugar(), tarea.getDescri(), tarea.getPrio(), tarea.getFecIni(), tarea.getFecFin(), tarea.getPkIdTar());
            flash.addFlashAttribute("info", "Tarea modificada con éxito.");
        } else {

            tarea.setFkIdAct(actividad);
            tarea.setFkIdLoc(loc);
            tarea.setHab(true);
            tarea.setAtrasada(false);
            tarea.setFin(false);
            tarea.setRep(false);
            tarServ.save(tarea);
            st.setComplete();
            flash.addFlashAttribute("info", "Nueva tarea guardada.");
        }
        // String redirectUrl = request.getScheme() + "://localhost:8082/actividades/ver/" + tarea.getFkIdAct().getPkIdAct().toString();
        String redirectUrl = request.getScheme() + "://31.220.52.31:8082/actividades/ver/" + tarea.getFkIdAct().getPkIdAct().toString();

        return "redirect:" + redirectUrl;
    }

    @Secured({"ROLE_USUARIO"})
    @RequestMapping(value = "/borrar/{id}", method = RequestMethod.GET)
    public String borrar(
            @PathVariable(value = "id") Integer id,
            HttpServletRequest request,
            RedirectAttributes flash) {

        Tareas tarea = tarServ.findById(id);
        tarServ.deshabilitar(id);
        flash.addFlashAttribute("warning", "Tarea borrada con éxito.");
        //String redirectUrl = request.getScheme() + "://localhost:8082/actividades/ver/" + tarea.getFkIdAct().getPkIdAct().toString();
        String redirectUrl = request.getScheme() + "://31.220.52.31:8082/actividades/ver/" + tarea.getFkIdAct().getPkIdAct().toString();

        return "redirect:" + redirectUrl;

    }
}
