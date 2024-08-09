package test;

import dao.IDao;
import dao.impl.OdontologoDaoList;
import model.Odontologo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoDaoListTest {

    /* PRUEBAS CON BASE DE DATOS EN MEMORIA */
    @Test
    void testCrearOdontologo(){
        Odontologo odontologoEsperado = new Odontologo("MO123", "Pepito", "Pérez");
        IDao<Odontologo> odontologoDao = new OdontologoDaoList();
        Odontologo odontologoGuardado = odontologoDao.guardar(odontologoEsperado);

        assertNotNull(odontologoGuardado.getId(), "El id del odontólogo no debe ser nulo");
        assertEquals(odontologoGuardado.getNombre(), odontologoEsperado.getNombre(), "El nombre del odontólogo debe coincidir");
        assertEquals(odontologoGuardado.getApellido(), odontologoEsperado.getApellido(), "El apellido del odontólogo debe coincidir");
        assertEquals(odontologoGuardado.getMatricula(), odontologoEsperado.getMatricula(), "La matrícula del odontólogo debe coincidir");
    }

}