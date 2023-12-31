package qu4lizz.ip.fitness.counselingapp.dao;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DatabaseConnection {
    public static DataSource createDataSource() {
        final String url =
                "jdbc:postgresql://localhost:5432/fitness?user=postgres&password=postgres";
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(url);
        return dataSource;
    }
}
