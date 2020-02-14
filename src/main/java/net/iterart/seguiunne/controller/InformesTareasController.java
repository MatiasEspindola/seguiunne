package net.iterart.seguiunne.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import net.iterart.seguiunne.model.Actividades;
import net.iterart.seguiunne.model.AdminsFacultades;
import net.iterart.seguiunne.model.DatosGralProyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.iterart.seguiunne.model.service.IUploadFileService;

import net.iterart.seguiunne.model.InformesTareas;
import net.iterart.seguiunne.model.Localidades;
import net.iterart.seguiunne.model.Proyectos;
import net.iterart.seguiunne.model.Tareas;
import net.iterart.seguiunne.model.Usuarios;
import net.iterart.seguiunne.model.service.IActividadesService;
import net.iterart.seguiunne.model.service.IAdminsFacultadesService;
import net.iterart.seguiunne.model.service.IDatosGralProyectoService;
import net.iterart.seguiunne.model.service.IFacultadesService;
import net.iterart.seguiunne.model.service.ILocalidadesService;
import net.iterart.seguiunne.model.service.IProyectosService;
import net.iterart.seguiunne.model.service.ITareasInformesService;
import net.iterart.seguiunne.model.service.ITareasService;
import net.iterart.seguiunne.model.service.IUsuariosService;
import org.springframework.security.access.annotation.Secured;

@Controller
@RequestMapping("/informes")
@SessionAttributes("informe")
@Secured({"ROLE_USUARIO", "ROLE_UNIDAD"})
public class InformesTareasController {

    @Autowired
    IUsuariosService usuServ;

    @Autowired
    private IProyectosService proyServ;

    @Autowired
    private ITareasInformesService infServ;

    @Autowired
    private IActividadesService actServ;

    @Autowired
    private ITareasService tarServ;

    @Autowired
    private IDatosGralProyectoService datosService;

    @Autowired
    private ILocalidadesService locServ;

    @Autowired
    private IUploadFileService uploadFileService;

    @Autowired
    private IAdminsFacultadesService admServ;

    @Autowired
    private IFacultadesService facServ;

    private Tareas tarea;
    private InformesTareas informe;

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("/listar/{idTar}")
    public String listar(@PathVariable(value = "idTar") Integer idTar, Model m) {

        List<InformesTareas> informes = infServ.findByTareas(idTar);

        m.addAttribute("titulo", "Informes de Tarea ");
        m.addAttribute("tarea", "Test");
        //m.addAttribute("user", informes);
        m.addAttribute("informes", informes);

        return "informes/listar";
    }

    @Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
    @GetMapping("/ver/{idTar}")
    public String ver(@PathVariable(value = "idTar") Integer idTar, Model m, HttpServletRequest request) {

        tarea = tarServ.findById(idTar);

        informe = new InformesTareas();

        informe = infServ.findOne(idTar);

        m.addAttribute("titulo", "Informes de Tarea ");
        m.addAttribute("titulo1", "Nombre del Proyecto");
        m.addAttribute("titulo2", "Nombre de la Actividad");
        m.addAttribute("titulo3", "Tarea Programada");
        m.addAttribute("titulo4", "Informe de la Tarea");
        m.addAttribute("tarea", tarea);
        m.addAttribute("informe", informe);

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

        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());

        Date fechaActual = new Date();

        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTA", listarTA);
        m.addAttribute("user", user);

        return "informes/ver";
    }

    @Secured({"ROLE_USUARIO"})
    @GetMapping("/form/{idTar}")
    public String crear(
            @PathVariable(value = "idTar") Integer idTar,
            Model m, HttpServletRequest request) {

        tarea = tarServ.findById(idTar);

        informe = new InformesTareas();

        //informe.setFecha(Date.valueOf(LocalDate.now()));
        m.addAttribute("titulo", "Nuevo Informe de la Tarea");
        m.addAttribute("titulo1", "Nombre del Proyecto");
        m.addAttribute("titulo2", "Nombre de la Actividad");
        m.addAttribute("titulo3", "Tarea Programada");
        m.addAttribute("titulo4", "Informe de la Tarea");
        m.addAttribute("tarea", tarea);
        m.addAttribute("informe", informe);

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

        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());

        Date fechaActual = new Date();

        m.addAttribute("fecha", fechaActual.getTime());
        m.addAttribute("tareas_atras", listarTA.size());
        m.addAttribute("listarTA", listarTA);
        m.addAttribute("user", user);

        return "informes/form";
    }

    @Secured({"ROLE_USUARIO"})
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(
            @Valid InformesTareas informe,
            @RequestParam(name = "loc_id", required = false) Integer locId,
            @RequestParam("file") MultipartFile foto,
            BindingResult r,
            Model m,
            RedirectAttributes flash,
            SessionStatus st,
            HttpServletRequest request
    ) {

        if (r.hasErrors()) {
            m.addAttribute("error", "Corrija los errores.");
            return "informes/form";
        }

        if (!foto.isEmpty()) {

            // Reemplazar foto si existe: 
//			if (informe.getPkIdInfotar() != 0L && informe.getPkIdInfotar() > 0 && informe.getFoto() != null
//					&& informe.getFoto().length() > 0) {
//
//				uploadFileService.delete(informe.getFoto());
//			}
            String uniqueFilename = null;

            try {
                uniqueFilename = uploadFileService.copy(foto);
            } catch (IOException e) {

                e.printStackTrace();
            }

            flash.addFlashAttribute("info", "Imagen " + uniqueFilename + " subida correctamente...");
            informe.setFoto(uniqueFilename);
        }

        Localidades loc = locServ.findLocalidadById(locId);

        informe.setFkIdLoc(loc);
        informe.setFkIdTar(tarea);

        //Actualizar estado de tarea a finalizado
        tarServ.updateFinalizarTarea(tarea.getPkIdTar());
        infServ.save(informe);

        st.setComplete();
        flash.addFlashAttribute("info", "Informe de Tarea guardado.");

        //return "redirect:/";
        String redirectUrl = request.getScheme() + "://31.220.52.31:8082/actividades/ver/" + tarea.getFkIdAct().getPkIdAct().toString();
        //String redirectUrl = request.getScheme() + "://localhost:8082/actividades/ver/" + tarea.getFkIdAct().getPkIdAct().toString();

        return "redirect:" + redirectUrl;
    }

    @Secured({"ROLE_USUARIO"})
    // Cargar la imagen para visualizar a travéz de un ResponseEntity:
    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

        Resource recurso = null;

        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }

}
