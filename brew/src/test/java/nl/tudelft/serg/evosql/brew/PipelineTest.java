package nl.tudelft.serg.evosql.brew;

import nl.tudelft.serg.evosql.brew.consumer.OutputConsumer;
import nl.tudelft.serg.evosql.brew.data.Result;
import nl.tudelft.serg.evosql.brew.db.ConnectionData;
import nl.tudelft.serg.evosql.brew.db.QueryRunner;
import nl.tudelft.serg.evosql.brew.generator.Generator;
import nl.tudelft.serg.evosql.brew.generator.Output;
import nl.tudelft.serg.evosql.brew.sql.vendor.PostgreSQLOptions;
import nl.tudelft.serg.evosql.brew.sql.vendor.VendorOptions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class PipelineTest {
    @Test
    void pipelineTest() {
        final QueryRunner queryRunner = mock(QueryRunner.class);
        final String sql = "Select * From any;";
        final ConnectionData connectionData = new ConnectionData("", "", "", "");
        final Result result = mock(Result.class);
        when(queryRunner.runQuery(sql, connectionData)).thenReturn(result);

        final Generator generator = mock(Generator.class);
        final VendorOptions vendorOptions = new PostgreSQLOptions();
        final List<Output> outputs = Arrays.asList(new Output("1", "one"), new Output("2", "two"));
        when(generator.generate(result, vendorOptions)).thenReturn(outputs);

        final OutputConsumer outputConsumer = mock(OutputConsumer.class);

        Pipeline p = Pipeline.builder()
                .queryRunner(queryRunner)
                .connectionData(connectionData)
                .sqlQuery(sql)
                .generator(generator)
                .vendorOptions(vendorOptions)
                .outputConsumer(outputConsumer)
                .build();

        p.execute();

        verify(queryRunner, times(1)).runQuery(sql, connectionData);
        verify(generator, times(1)).generate(result, vendorOptions);
        verify(outputConsumer, times(1)).consumeOutput(outputs);
    }
}
