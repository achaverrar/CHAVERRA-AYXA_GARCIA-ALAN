package dao.impl;

import dao.IDao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoList implements IDao<Odontologo> {
    private List<Odontologo> odontologos;
    private static Logger LOGGER = Logger.getLogger(OdontologoDaoList.class);

    public OdontologoDaoList() {
        odontologos = new ArrayList<>();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologo.setId((long) odontologos.size());
        odontologos.add(odontologo);
        LOGGER.info("Se guardó el odontólogo: " + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Retornando el listado de todos los odontólogos");
        return odontologos;
    }
}
