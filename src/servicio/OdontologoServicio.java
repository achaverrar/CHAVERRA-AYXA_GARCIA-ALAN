package servicio;

import dao.IDao;
import model.Odontologo;

import java.util.List;

public class OdontologoServicio {
    private final IDao<Odontologo> odontologoDao;

    public OdontologoServicio(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoDao.guardar(odontologo);
    }

    public List<Odontologo> listarOdontologos() {
        return odontologoDao.listarTodos();
    }
}