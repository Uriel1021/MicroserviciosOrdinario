package com.uriel.Principal.Controlador;

import com.uriel.Principal.Modelo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
public class ClienteControlador {
    private final RestTemplate restTemplate;

    public ClienteControlador(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/listar")
    public String index(Model model) {
        Cliente[] clientes = restTemplate.getForObject("http://localhost:7878/clientes/list", Cliente[].class);
        model.addAttribute("clientes", clientes);
        return "index";
    }

    @GetMapping("/buscar")
    public String buscarCliente(Model model, @RequestParam String nombre, @RequestParam String apellidoPaterno, @RequestParam String apellidoMaterno) {
        Cliente[] clientes = restTemplate.getForObject("http://localhost:7878/clientes/buscar?nombre={nombre}&apellidoPaterno={apellidoPaterno}&apellidoMaterno={apellidoMaterno}", Cliente[].class, nombre, apellidoPaterno, apellidoMaterno);
        model.addAttribute("clientes", clientes);
        return "index";
    }

    @GetMapping("/nuevo")
    public String nuevoCliente() {
        return "nuevo";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@RequestParam String nombre, @RequestParam String apellidoPaterno, @RequestParam String apellidoMaterno, @RequestParam String correoElectronico, @RequestParam String telefono, @RequestParam("datosMedicos.edad") int edad, @RequestParam("datosMedicos.peso") double peso, @RequestParam("datosMedicos.estatura") double estatura, @RequestParam("datosMedicos.padecimientoMedico") String padecimientoMedico, @RequestParam(value = "datosMedicos.consumoMedicamento", defaultValue = "false") boolean consumoMedicamento, @RequestParam("datosMedicos.presionArterial") String presionArterial, @RequestParam("ubicacion.estado") String estado, @RequestParam("ubicacion.colonia") String colonia, @RequestParam("ubicacion.codigoPostal") String codigoPostal, @RequestParam("datosContacto.telefono") String telefonoContacto, @RequestParam("datosContacto.correo") String correoContacto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellidoPaterno(apellidoPaterno);
        cliente.setApellidoMaterno(apellidoMaterno);

        DatosMedicos datosMedicos = new DatosMedicos();
        datosMedicos.setEdad(edad);
        datosMedicos.setPeso(peso);
        datosMedicos.setEstatura(estatura);
        datosMedicos.setPadecimientoMedico(padecimientoMedico);
        datosMedicos.setConsumoMedicamento(consumoMedicamento);
        datosMedicos.setPresionArterial(presionArterial);

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setEstado(estado);
        ubicacion.setColonia(colonia);
        ubicacion.setCodigoPostal(codigoPostal);

        DatosContacto datosContacto = new DatosContacto();
        datosContacto.setTelefono(telefonoContacto);
        datosContacto.setCorreo(correoContacto);

        cliente.setDatosMedicos(datosMedicos);
        cliente.setUbicacion(ubicacion);
        cliente.setDatosContacto(datosContacto);

        String respuesta = restTemplate.postForObject("http://localhost:7878/clientes/add", cliente, String.class);
        return "redirect:/listar";
    }

    @GetMapping("/editar/{clienteId}")
    public String editarCliente(Model model, @PathVariable Long clienteId) {
        Cliente cliente = restTemplate.getForObject("http://localhost:7878/clientes/obtenerclienteporid?clienteId={clienteId}", Cliente.class, clienteId);
        model.addAttribute("cliente", cliente);
        return "editar";
    }


    @GetMapping("/cliente/{clienteId}")
    public String obtenerClientePorId(Model model, @PathVariable Long clienteId) {
        Cliente cliente = restTemplate.getForObject("http://localhost:7878/clientes/obtenerclienteporid?clienteId={clienteId}", Cliente.class, clienteId);
        model.addAttribute("cliente", cliente);
        return "cliente";
    }

    @GetMapping("/eliminar/{clienteId}")
    public String eliminarCliente(@PathVariable Long clienteId) {
        restTemplate.postForObject("http://localhost:7878/clientes/eliminar?clienteId={clienteId}", null, String.class, clienteId);
        return "redirect:/listar";
    }

    @GetMapping("/pagos/{clienteId}")
    public String obtenerPagosCliente(Model model, @PathVariable Long clienteId) {
        Cliente cliente = restTemplate.getForObject("http://localhost:7878/clientes/obtenerclienteporid?clienteId={clienteId}", Cliente.class, clienteId);
        model.addAttribute("cliente", cliente);
        Pago[] pagos = restTemplate.getForObject("http://localhost:7878/clientes/listarpagos?clienteId={clienteId}", Pago[].class, clienteId);
        model.addAttribute("pagos", pagos);
        return "listar-pagos";
    }

    @GetMapping("/suscripciones/{clienteId}")
    public String obtenerSuscripcionesCliente(Model model, @PathVariable Long clienteId) {
        Suscripcion[] suscripciones = restTemplate.getForObject("http://localhost:7878/clientes/listarsuscripciones?clienteId={clienteId}", Suscripcion[].class, clienteId);
        model.addAttribute("suscripciones", suscripciones);
        return "suscripciones";
    }

    @GetMapping("/crearpago/{clienteId}")
    public String agregarPago(Model model,@PathVariable Long clienteId){
        Cliente cliente = restTemplate.getForObject("http://localhost:7878/clientes/obtenerclienteporid?clienteId={clienteId}", Cliente.class, clienteId);
        model.addAttribute("cliente", cliente);
        model.addAttribute("pago", new Pago());
        return "crear-pago";
    }

    @PostMapping("/pagos/{clienteId}")
    public String agregarPagoCliente(@PathVariable Long clienteId, Pago pago) {
        String respuesta = restTemplate.postForObject("http://localhost:7878/clientes/agregarpagos?clienteId={clienteId}", pago, String.class, clienteId);
        return "redirect:/pagos/" + clienteId;
    }

    @PostMapping("/suscripciones/{clienteId}")
    public String agregarSuscripcionCliente(@PathVariable Long clienteId, Suscripcion suscripcion) {
        String respuesta = restTemplate.postForObject("http://localhost:7878/clientes/agregarsuscripciones?clienteId={clienteId}", suscripcion, String.class, clienteId);
        return "redirect:/suscripciones/" + clienteId;
    }
}
