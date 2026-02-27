package dataAccessLayer;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Generic DAO class providing CRUD operations using reflection.
 * @param <T> The type of entity this DAO handles
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {//r
        this.type = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Connection getConnection() throws SQLException {
        return ConnectionFactory.getConnection();
    }

    public List<T> findAll() {
        String query = "SELECT * FROM " + type.getSimpleName().toLowerCase();
        List<T> result = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                T instance = createObject(rs);
                result.add(instance);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public T findById(int id) {
        String query = "SELECT * FROM " + type.getSimpleName().toLowerCase() + " WHERE id=?";
        T result = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    result = createObject(rs);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void insert(T t) {
        Field[] fields = type.getDeclaredFields();
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(type.getSimpleName().toLowerCase()).append(" (");

        for (int i = 1; i < fields.length; i++) {
            query.append(fields[i].getName());
            if (i < fields.length - 1) query.append(", ");
        }

        query.append(") VALUES (");
        for (int i = 1; i < fields.length; i++) {
            query.append("?");
            if (i < fields.length - 1) query.append(", ");
        }
        query.append(")");

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS)) {

            for (int i = 1; i < fields.length; i++) {
                fields[i].setAccessible(true);
                statement.setObject(i, fields[i].get(t));
            }

            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    fields[0].setAccessible(true);
                    fields[0].set(t, rs.getInt(1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private T createObject(ResultSet rs) throws Exception {
        T instance = type.getDeclaredConstructor().newInstance();
        for (Field field : type.getDeclaredFields()) {
            Object value = rs.getObject(field.getName());
            field.setAccessible(true);
            field.set(instance, value);
        }
        return instance;
    }
}
