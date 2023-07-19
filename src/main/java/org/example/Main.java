package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/test2";
        String username = "root";
        String password = "susanoo21";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {


            String createAutori = "CREATE TABLE autori (pk_autor INT NOT NULL, nume_autor VARCHAR(50) NOT NULL, PRIMARY KEY (pk_autor), UNIQUE (nume_autor))";
            String createCititori = "CREATE TABLE cititori (pk_cititor INT NOT NULL, nume_cititor VARCHAR(50), varsta INT(2), sex VARCHAR(1), calificativ VARCHAR(50), PRIMARY KEY (pk_cititor))";
            String createCarti = "CREATE TABLE carti ( pk_carte INT NOT NULL, domeniu VARCHAR(50), titlu_carte VARCHAR(100) NOT NULL, pk_autor1 INT, pk_autor2 INT, pk_autor3 INT, PRIMARY KEY (pk_carte), CHECK (domeniu IN ('Beletristica', 'Stiinte', 'Divertisment')), UNIQUE (titlu_carte), FOREIGN KEY (pk_autor1) REFERENCES autori(pk_autor))";
            String createImprumuturi = "CREATE TABLE imprumuturi (pk_imprumut INT NOT NULL, pk_carte INT NOT NULL, pk_cititor INT NOT NULL, data_start DATE NOT NULL, data_end DATE NOT NULL, data_return DATE, observatii VARCHAR(50), PRIMARY KEY (pk_imprumut), FOREIGN KEY (pk_carte) REFERENCES carti(pk_carte), FOREIGN KEY (pk_cititor) REFERENCES cititori(pk_cititor))";

            statement.executeUpdate(createAutori);
            statement.executeUpdate(createCititori);
            statement.executeUpdate(createCarti);
            statement.executeUpdate(createImprumuturi);

            String insertAutor1 = "INSERT INTO autori VALUES (1, 'Autor_1')";
            String insertAutor2 = "INSERT INTO autori VALUES (2, 'Autor_2')";
            String insertAutor3 = "INSERT INTO autori VALUES (3, 'Autor_3')";
            statement.executeUpdate(insertAutor1);
            statement.executeUpdate(insertAutor2);
            statement.executeUpdate(insertAutor3);

            String insertCititor1 = "INSERT INTO cititori VALUES (1, 'Cititor_1', 25, 'M', 'FB')";
            String insertCititor2 = "INSERT INTO cititori VALUES (2, 'Cititor_2', 20, 'F', 'B')";
            String insertCititor3 = "INSERT INTO cititori VALUES (3, 'Cititor_3', 21, 'M', 'S')";
            statement.executeUpdate(insertCititor1);
            statement.executeUpdate(insertCititor2);
            statement.executeUpdate(insertCititor3);

            String insertCarte1 = "INSERT INTO carti VALUES (1, 'Beletristica', 'Titlu_1', 1,NULL, NULL)";
            String insertCarte2 = "INSERT INTO carti VALUES (2, 'Beletristica', 'Titlu_2', 2, NULL, NULL)";
            String insertCarte3 = "INSERT INTO carti VALUES (3, 'Stiinte', 'Titlu_3', 1, NULL, NULL)";
            statement.executeUpdate(insertCarte1);
            statement.executeUpdate(insertCarte2);
            statement.executeUpdate(insertCarte3);

            String insertImprumut1 = "INSERT INTO imprumuturi VALUES (1, 1, 2, CURDATE() - INTERVAL 5 DAY, CURDATE() + INTERVAL 5 DAY, NULL, NULL)";
            String insertImprumut2 = "INSERT INTO imprumuturi VALUES (2, 2, 2, CURDATE() - INTERVAL 5 DAY, CURDATE() + INTERVAL 5 DAY, CURDATE() + INTERVAL 3 DAY, NULL)";
            statement.executeUpdate(insertImprumut1);
            statement.executeUpdate(insertImprumut2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}