package nl.tudelft.serg.evosql.experiment;

import nl.tudelft.serg.evosql.brew.db.ConnectionData;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

/**
 * Main runner class. Implementation depends on whether it will be used as a client for
 * a web server which hands out jobs or whether it should be autonomous and read a portion
 * of the queries.
 */
public class Runner {

    public static void main(String[] args) {
        int startIndex = Integer.valueOf(args[0]);
        int stepSize = Integer.valueOf(args[1]);
        BufferedReader reader_erpnext = new BufferedReader(new InputStreamReader(
                Runner.class.getResourceAsStream("sql/erpnext_queries.sql")));
        Stream<String> erpnext = reader_erpnext.lines();

        try {
            reader_erpnext.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader_espocrm = new BufferedReader(new InputStreamReader(
                Runner.class.getResourceAsStream("sql/espocrm_queries.sql")));
        Stream<String> espocrm = reader_espocrm.lines();

        try {
            reader_espocrm.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader_suitecrm = new BufferedReader(new InputStreamReader(
                Runner.class.getResourceAsStream("sql/suitecrm_queries.sql")));
        Stream<String> suitecrm = reader_suitecrm.lines();

        try {
            reader_suitecrm.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        QueryReader queryReader = new QueryReader();
        List<String> allQueries = queryReader.readQueries(erpnext, espocrm, suitecrm);

        // TODO: Make connection data for separate databases...
        for (int i = startIndex; i < allQueries.size(); i += stepSize) {
            runForQuery(
                    allQueries.get(i),
                    null,
                    null,
                    String.valueOf(i)
            );
        }

    }

    /**
     * End to end execution of experiment for a single query.
     *
     * @param query              query to execute
     * @param connectionDataProd data of db connection for production database
     * @param connectionDataTest data of db connection for test database
     */
    public static QueryExperimentResult runForQuery(String query,
                                             ConnectionData connectionDataProd,
                                             ConnectionData connectionDataTest,
                                             String queryClassName) {

        // TODO: Implement method

        return null;
    }


}
