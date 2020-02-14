package net.iterart.seguiunne.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.iterart.seguiunne.model.Actividades;
import net.iterart.seguiunne.model.AdminsFacultades;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import net.iterart.seguiunne.model.DatosGralProyecto;
import net.iterart.seguiunne.model.Proyectos;
import net.iterart.seguiunne.model.ResponsablesProyecto;
import net.iterart.seguiunne.model.Tareas;
import net.iterart.seguiunne.model.TiposCargos;
import net.iterart.seguiunne.model.Usuarios;
import net.iterart.seguiunne.model.service.IActividadesService;
import net.iterart.seguiunne.model.service.IAdminsFacultadesService;
import net.iterart.seguiunne.model.service.IDatosGralProyectoService;
import net.iterart.seguiunne.model.service.IFacultadesService;
import net.iterart.seguiunne.model.service.IProyectosService;
import net.iterart.seguiunne.model.service.IResponsablesProyectoService;
import net.iterart.seguiunne.model.service.ITareasService;
import net.iterart.seguiunne.model.service.ITiposCargosService;
import net.iterart.seguiunne.model.service.IUsuariosService;
import org.springframework.security.access.annotation.Secured;

@Secured({"ROLE_USUARIO", "ROLE_UNIDAD", "ROLE_ADMIN"})
@Controller
@SessionAttributes("fkIdPro")
public class ProyectosController {

    @Autowired
    private IDatosGralProyectoService datosService;

    @Autowired
    IUsuariosService usuServ;

    private final Logger log = LoggerFactory.getLogger(getClass());

    // Contabilizar cantidad de actividades programadas
    @Autowired
    private IActividadesService actServ;

    public static int actividades_prog;

    // Contabilizar cantidad de tareas programadas
    @Autowired
    private IProyectosService proServ;

    @Autowired
    private ITareasService tarServ;

    @Autowired
    private IAdminsFacultadesService admServ;

    @Autowired
    private IFacultadesService facServ;

    @Autowired
    private IResponsablesProyectoService resServ;

    @Autowired
    private ITiposCargosService carServ;

    public static int proyectos_financiados;

    public static int tareas_prog;

    // Contabilizar cantidad de tareas atrasadas
    public static int tareas_atras;

    @GetMapping({"/", "/proyectos/listar"})
    public String listar(Model m, HttpServletRequest request) {
        //Evitar sobrecarga
        proyectos_financiados = 0;

        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuarios user = new Usuarios();
        user = usuServ.findByUsuario(request.getRemoteUser());

        List<DatosGralProyecto> datos = null;

        List<AdminsFacultades> admins = admServ.findAll();

        if (user.getFkIdRol().getPkIdRol() == 3) {
            datos = datosService.fetchByUser(user);
        } else if (user.getFkIdRol().getPkIdRol() == 2) {
             for (int i = 0; i < admins.size(); i++) {
                if(admins.get(i).getFkIdUsu().getPkIdUsu().equals(user.getPkIdUsu())){
                    datos = datosService.fetchByFacultad(facServ.findById(admins.get(i).getFkIdFac().getPkIdFac()));
                    List<DatosGralProyecto> proyectosFinanciadosPorFacultad = datosService.buscarProyectosFinanciadosPorFacultad(facServ.findById(admins.get(i).getFkIdFac().getPkIdFac()));
                    // POR FACULTAD
                    m.addAttribute("proyectosFinanciadosPorFacultad", proyectosFinanciadosPorFacultad);
               }
            }
        } else {

            datos = datosService.buscarTodo();

        }

        // Cantidad de Proyectos Financiados
        for (int i = 0; i < datos.size(); i++) {
            if (datos.get(i).getEstado().equals("FINANCIADO")) {
                proyectos_financiados++;
            }
        }

        m.addAttribute("titulo", "Mis Proyectos de Extensión");

        // m.addAttribute("user", datos.get(0).getFkIdUsu().getUsuario());
        m.addAttribute("datos", datos);

        m.addAttribute("user", user);

        // Agregado por @MatiasEspindola
        List<Proyectos> proyectos = proServ.findAll();
        List<Actividades> actividades = actServ.findAll();
        List<Tareas> tareas = tarServ.findAll();
        ArrayList<Tareas> listarTA = new ArrayList<>();
        ArrayList<Tareas> listarTR = new ArrayList<>();

        List<Actividades> listaActividades = new ArrayList<>();
        
        ArrayList<Tareas> listar_Tareas = new ArrayList<>();

        // ADMIN GENERAL
        List<DatosGralProyecto> proyectosFinanciados = datosService.buscarProyectosFinanciados();
        m.addAttribute("proyectosFinanciados", proyectosFinanciados);

        // POR USUARIO
        List<DatosGralProyecto> proyectosFinanciadosPorUsuario = datosService.buscarProyectosFinanciadosPorUsuario(user);
        m.addAttribute("proyectosFinanciadosPorUsuario", proyectosFinanciadosPorUsuario);

        m.addAttribute("proyectos_financiados", proyectos_financiados);
        m.addAttribute("cant_proyectos", datos.size());
        m.addAttribute("actividades_prog", actividadesProgramadas(datos));
        m.addAttribute("tareas_prog", tareasProgramadas(datos, proyectos, actividades));
        m.addAttribute("tareas_atras", tareasAtrasadas(datos, proyectos, actividades));

        //Agrega a las Notificaciones la lista de Tareas Atrasadas
        for (int i = 0; i < proyectos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (proyectos.get(i).getFkIdDat().getPkIdDat().equals(datos.get(j).getPkIdDat())) {
                    for (int k = 0; k < actividades.size(); k++) {
                        if (actividades.get(k).getFkIdPro().getPkIdPro().equals(proyectos.get(i).getPkIdPro())) {

                            listaActividades.add(actividades.get(k));
                            listar_Tareas.addAll(tarServ.fetchByActividad(actividades.get(k).getPkIdAct()));
                            listarTA.addAll(tarServ.fetchByTareaAtrasada(actividades.get(k).getPkIdAct()));
                            listarTR.addAll(tarServ.fetchByTareaReprogramada(actividades.get(k).getPkIdAct()));
                            
                        }
                    }
                }
            }
        }

       

        m.addAttribute("listar_actividades", listaActividades);
        m.addAttribute("listar_Tareas", listar_Tareas);

        m.addAttribute("listarTR", listarTR);
        m.addAttribute("cant_repro", listarTR.size());
        m.addAttribute("listarTA", listarTA);

        Date fechaActual = new Date();

        m.addAttribute("fecha", fechaActual.getTime());

        return "proyectos/listar";
    }

    private int actividadesProgramadas(List<DatosGralProyecto> datos) {
        //Evita que se sobre carge la cantidad de actividades programadas
        actividades_prog = 0;

        for (int i = 0; i < datos.size(); i++) {
            //Realiza el conteo de actividades programadas
            actividades_prog += actServ.fetchByProyecto(datos.get(i).getPkIdDat()).size();
        }

        return actividades_prog;
    }

    private int tareasProgramadas(List<DatosGralProyecto> datos, List<Proyectos> proyectos, List<Actividades> actividades) {
        //Evita que se sobre carge la cantidad de tareas programadas
        tareas_prog = 0;

        for (int i = 0; i < proyectos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (proyectos.get(i).getFkIdDat().getPkIdDat().equals(datos.get(j).getPkIdDat())) {
                    for (int k = 0; k < actividades.size(); k++) {
                        if (actividades.get(k).getFkIdPro().getPkIdPro().equals(proyectos.get(i).getPkIdPro())) {
                            //Envía al método "calcularTareasAtrasadas" la lista de Tareas según la Actividad
                            calcularTareasAtrasadas(tarServ.fetchByActividad(actividades.get(k).getPkIdAct()));
                            //Realiza el conteo de tareas programadas
                            tareas_prog += tarServ.fetchByActividad(actividades.get(k).getPkIdAct()).size();
                        }
                    }
                }
            }
        }

        return tareas_prog;
    }

    private void calcularTareasAtrasadas(List<Tareas> tareas) {
        Tareas tareaActual;

        Date fechaSistema = new Date();
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getFin() == false) {
                //Calcula la diferencia de dias, si supera los 7 la tarea se encontrará "atrasada"
                int diferencia_dias = (int) ((fechaSistema.getTime() - tareas.get(i).getFecFin().getTime()) / 86400000);
                if (diferencia_dias > 7) {
                    tareaActual = tarServ.findById(tareas.get(i).getPkIdTar());
                    tareaActual.setAtrasada(Boolean.TRUE);
                    tarServ.save(tareaActual);
                } else {
                    tareaActual = tarServ.findById(tareas.get(i).getPkIdTar());
                    tareaActual.setAtrasada(Boolean.FALSE);
                    tarServ.save(tareaActual);
                }
            } else {
                // Si la TAREA ha finalizado, se le quita el estado de 'ATRASADO' y de 'REPROGRAMADO'
                tareaActual = tarServ.findById(tareas.get(i).getPkIdTar());
                tareaActual.setAtrasada(Boolean.FALSE);
                tareaActual.setRep(Boolean.FALSE);
                tarServ.save(tareaActual);
            }
        }
    }

    private int tareasAtrasadas(List<DatosGralProyecto> datos, List<Proyectos> proyectos, List<Actividades> actividades) {
        //Evita que se sobre carge la cantidad de tareas atrasadas
        tareas_atras = 0;

        for (int i = 0; i < proyectos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (proyectos.get(i).getFkIdDat().getPkIdDat().equals(datos.get(j).getPkIdDat())) {
                    for (int k = 0; k < actividades.size(); k++) {
                        if (actividades.get(k).getFkIdPro().getPkIdPro().equals(proyectos.get(i).getPkIdPro())) {
                            //Realiza el conteo de tareas atrasadas
                            tareas_atras += tarServ.fetchByTareaAtrasada(actividades.get(k).getPkIdAct()).size();
                        }
                    }
                }
            }
        }

        return tareas_atras;
    }

}
