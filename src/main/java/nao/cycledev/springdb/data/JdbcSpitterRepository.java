package nao.cycledev.springdb.data;

import nao.cycledev.springdb.model.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class JdbcSpitterRepository implements SpitterRepository {

    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcSpitterRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void addSpitter(Spitter spitter) {

        jdbcOperations.update("insert into spitter (id, firstname, lastname, username) values (?, ?, ?, ?)",
                spitter.getId(),
                spitter.getFirstName(),
                spitter.getLastName(),
                spitter.getUserName());

    }

    public Spitter findSpitter(long id) {

        return jdbcOperations.queryForObject(
                "Select * From spitter Where id = ?",
                (rs, rowNum) -> new Spitter(
                        rs.getLong("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username")
                        ),
                id);

    }
}
