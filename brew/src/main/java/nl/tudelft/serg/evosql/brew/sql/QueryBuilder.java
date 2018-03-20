package nl.tudelft.serg.evosql.brew.sql;

import lombok.Data;
import lombok.NonNull;
import nl.tudelft.serg.evosql.brew.data.Result;


import java.util.List;

/**
 * An abstract class for transforming data into SQL queries.
 */
@Data
public abstract class QueryBuilder {

    /**
     * The vendor options used for SQL generation.
     */
    @NonNull
    private final VendorOptions vendorOptions;

    /**
     * Builds a list of SQL queries using result data.
     *
     * @param result The result data to use for SQL generation.
     * @return A list of SQL queries.
     */
    public abstract List<String> buildQueries(Result result);

    /**
     * Builds a single aggregate SQL query using result data.
     *
     * @param result The result data to use for SQL generation.
     * @return A single SQL query.
     */
    public abstract String buildAggregateQuery(Result result);
}
