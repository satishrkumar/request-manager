package net.pay.you.back.request.manager.job;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@EnableBatchProcessing
public class MongoDBEmailProcessor {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

 /*   @Bean
    public Job readEmployee() throws Exception {
        return jobBuilderFactory.get("readEmployee").flow(step1()).end().build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1").<Loan, Loan>chunk(10).reader(reader())
                .writer(writer()).build();
    }

    @Bean
    public MongoItemReader<Loan> reader() {
        MongoItemReader<Loan> reader = new MongoItemReader<>();
        reader.setTemplate(mongoTemplate);
        reader.setSort(new HashMap<>() {{
            put("_id", Sort.Direction.DESC);
        }});
        reader.setTargetType(Loan.class);
        reader.setQuery("{}");
        return reader;
    }*/
}
