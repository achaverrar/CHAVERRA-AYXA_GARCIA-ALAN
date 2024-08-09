import dao.IDao;
import dao.impl.OdontologoDaoList;
import model.Odontologo;
import servicio.OdontologoServicio;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IDao<Odontologo> odontologoDao = new OdontologoDaoList();
        OdontologoServicio odontologoServicio = new OdontologoServicio(odontologoDao);
        odontologoServicio.guardarOdontologo(new Odontologo("MO123", "Pepito", "Pérez"));
        odontologoServicio.guardarOdontologo(new Odontologo("MO124", "Fulanita", "Morales"));

        List<Odontologo> odontologos = odontologoServicio.listarOdontologos();
        for (Odontologo odontologo : odontologos) {
            System.out.println(odontologo);
        }
    }
}