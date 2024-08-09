package dao.impl;

import dao.BD;
import dao.IDao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Comenzamos a persistir un odontólogo");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = BD.getConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS (matricula, nombre, apellido) VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.execute();

            try(ResultSet rs = preparedStatement.getGeneratedKeys()) {

                while (rs.next()){
                    odontologo.setId(rs.getLong(1));

                    LOGGER.info("Guardamos odontólogo de nombre: " + odontologo.getNombre());
                }
            }

        } catch (Exception e) {
            LOGGER.info("Error creando odontólogo: ", e);
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.info("Error cerrando conexión: ", ex);
                ex.printStackTrace();
            }
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Comenzamos a listar odontólogos");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Odontologo> odontologos = new ArrayList<>();

        try {
            connection = BD.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Long id = rs.getLong("id");
                String matricula = rs.getString("matricula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                odontologos.add(new Odontologo(id, matricula, nombre, apellido));

                LOGGER.info("Odontólogo: " + nombre);
            }

            preparedStatement.close();

        } catch (Exception e) {
            LOGGER.info("Error listando odontólogos: ", e);
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                LOGGER.info("Error cerrando conexión: ", ex);
                ex.printStackTrace();
            }
        }
        return odontologos;
    }
}
