package com.uriel.Principal.Controlador;

import com.uriel.Principal.Modelo.Servicio;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/servicios")
public class ServicioControlador {
    private final String BASE_URL = "http://localhost:7979/servicios";

    private final RestTemplate restTemplate;

    public ServicioControlador(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/listar")
    public String listarServicios(Model model) {
        ResponseEntity<Servicio[]> response = restTemplate.getForEntity(BASE_URL + "/listar", Servicio[].class);
        Servicio[] servicios = response.getBody();
        model.addAttribute("servicios", servicios);
        return "listar-servicios";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "crear-servicio";
    }

    @PostMapping("/guardar")
    public String guardarServicio(Servicio servicio) {
        HttpEntity<Servicio> requestEntity = new HttpEntity<>(servicio);
        restTemplate.postForEntity(BASE_URL + "/crear", requestEntity, String.class);
        return "redirect:/servicios/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model) {
        ResponseEntity<Servicio> response = restTemplate.getForEntity(BASE_URL + "/buscarporid?id=" + id, Servicio.class);
        Servicio servicio = response.getBody();
        model.addAttribute("servicio", servicio);
        return "editar-servicio";
    }

    @PostMapping("/actualizar")
    public String actualizarServicio(Servicio servicio) {
        HttpEntity<Servicio> requestEntity = new HttpEntity<>(servicio);
        restTemplate.postForEntity(BASE_URL + "/actualizar", requestEntity, String.class);
        return "redirect:/servicios/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarServicio(@PathVariable("id") Integer id) {
        restTemplate.postForEntity(BASE_URL + "/eliminar?id=" + id, null, String.class);
        return "redirect:/servicios/listar";
    }
}
