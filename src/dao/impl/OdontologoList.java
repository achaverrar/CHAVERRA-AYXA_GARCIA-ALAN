package dao.impl;

import dao.IDao;
import model.Odontologo;

import java.util.ArrayList;
import java.util.List;

public class OdontologoList implements IDao<Odontologo> {
    private static List<Odontologo> odontologos;

    public OdontologoList() {
        odontologos = new ArrayList<>();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologos.add(odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologos;
    }
}
