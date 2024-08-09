package test;

import dao.IDao;
import dao.impl.OdontologoDaoH2;
import model.Odontologo;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.List;

class OdontologoDaoH2Test {
    private  static IDao<Odontologo> odontologoIDao = new OdontologoDaoH2();

    @BeforeClass
    public static void cargarEstudiantes() {
        odontologoIDao.guardar(new Odontologo(1L, "matricula01", "nombre01", "apellido01"));
        odontologoIDao.guardar(new Odontologo(2L, "matricula02", "nombre02", "apellido02"));
        odontologoIDao.guardar(new Odontologo(3L, "matricula03", "nombre03", "apellido03"));
    }

    @Test
    public void traerTodosLosOdontologos(){
        List<Odontologo> odontologos = odontologoIDao.listarTodos();
        Assert.assertTrue(odontologos.size() > 0);
    }
}