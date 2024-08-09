package test;

import dao.BD;
import dao.IDao;
import dao.impl.OdontologoDaoH2;
import dao.impl.OdontologoDaoList;
import model.Odontologo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoDaoListTest {

    /* PRUEBAS CON BASE DE DATOS H2 */
    @Test
    void testCrearOdontologoH2(){
        Odontologo odontologoEsperado = new Odontologo("MO123", "Pepito", "Pérez");
        IDao<Odontologo> odontologoDao = new OdontologoDaoH2();
        Odontologo odontologoGuardado = odontologoDao.guardar(odontologoEsperado);

        assertNotNull(odontologoGuardado.getId(), "El id del odontólogo no debe ser nulo");
        assertEquals(odontologoGuardado.getNombre(), odontologoEsperado.getNombre(), "El nombre del odontólogo debe coincidir");
        assertEquals(odontologoGuardado.getApellido(), odontologoEsperado.getApellido(), "El apellido del odontólogo debe coincidir");
        assertEquals(odontologoGuardado.getMatricula(), odontologoEsperado.getMatricula(), "La matrícula del odontólogo debe coincidir");
    }

//    @BeforeEach
//    void setUpConnection() throws Exception {
//        try (Connection conn = BD.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement("TRUNCATE TABLE users RESTART IDENTITY")
//        ) {
//            pstmt.executeUpdate();
//        }
//    }

    @Test
    void testListarOdontologosH2() throws Exception {
        Connection conn = BD.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("TRUNCATE TABLE odontologos RESTART IDENTITY");
        pstmt.executeUpdate();

        IDao<Odontologo> odontologoDao = new OdontologoDaoH2();
        Odontologo odontologo1 = odontologoDao.guardar(new Odontologo("MO123", "Pepito", "Pérez"));
        Odontologo odontologo2 = odontologoDao.guardar(new Odontologo("MO124", "Fulanita", "Morales"));

        List<Odontologo> odontologos = odontologoDao.listarTodos();

        assertNotNull(odontologos, "El listado de odontólogos no debe ser nulo");
        assertEquals(2, odontologos.size(), "Debe haber al menos 2 odontólogos guardados");
        assertTrue(odontologos.contains(odontologo1), "El odontólogo debe estar guardado");
        assertTrue(odontologos.contains(odontologo2), "El odontólogo debe estar guardado");
    }

    /* PRUEBAS CON BASE DE DATOS EN MEMORIA */
    @Test
    void testCrearOdontologoList(){
        Odontologo odontologoEsperado = new Odontologo("MO123", "Pepito", "Pérez");
        IDao<Odontologo> odontologoDao = new OdontologoDaoList();
        Odontologo odontologoGuardado = odontologoDao.guardar(odontologoEsperado);

        assertNotNull(odontologoGuardado.getId(), "El id del odontólogo no debe ser nulo");
        assertEquals(odontologoGuardado.getNombre(), odontologoEsperado.getNombre(), "El nombre del odontólogo debe coincidir");
        assertEquals(odontologoGuardado.getApellido(), odontologoEsperado.getApellido(), "El apellido del odontólogo debe coincidir");
        assertEquals(odontologoGuardado.getMatricula(), odontologoEsperado.getMatricula(), "La matrícula del odontólogo debe coincidir");
    }

    @Test
    void testListarOdontologosList() {
        IDao<Odontologo> odontologoDao = new OdontologoDaoList();;
        Odontologo odontologo1 = odontologoDao.guardar(new Odontologo("MO123", "Pepito", "Pérez"));
        Odontologo odontologo2 = odontologoDao.guardar(new Odontologo("MO124", "Fulanita", "Morales"));

        List<Odontologo> odontologos = odontologoDao.listarTodos();

        assertNotNull(odontologos, "El listado de odontólogos no debe ser nulo");
        assertEquals(2, odontologos.size(), "Debe haber al menos 2 odontólogos guardados");
        assertTrue(odontologos.contains(odontologo1), "El odontólogo debe estar guardado");
        assertTrue(odontologos.contains(odontologo2), "El odontólogo debe estar guardado");
    }
}