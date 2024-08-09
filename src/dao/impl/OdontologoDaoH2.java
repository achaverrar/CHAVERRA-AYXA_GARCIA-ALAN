package dao.impl;

import dao.IDao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:./libs";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Comenzamos a persistir un medicamento");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGO VALUES (?,?,?,?)");
            preparedStatement.setLong(1, odontologo.getId());
            preparedStatement.setString(2, odontologo.getMatricula());
            preparedStatement.setString(3, odontologo.getNombre());
            preparedStatement.setString(4, odontologo.getApellido());

            //preparedStatement.execute();

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                odontologo.setId(rs.getLong(1));

                LOGGER.info("Nombre: " +
                        odontologo.getNombre());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Odontologo> odontologos = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Long id = rs.getLong("id");
                String matricula = rs.getString("matricula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                odontologos.add(new Odontologo(id, matricula, nombre, apellido));

                LOGGER.info("Nombre: " +
                        nombre);
            }

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return odontologos;
    }
}
