package dao.impl;

import dao.IDao;
import model.Odontologo;

import java.util.List;

public class OdontologoDatoHashMap implements IDao<Odontologo> {
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return null;
    }

    @Override
    public List<Odontologo> listarTodos() {
        return List.of();
    }
}
