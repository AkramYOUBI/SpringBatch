package com.example.bankspringbatch.demo;

import com.example.bankspringbatch.demo.domain.Entity.BankTransactionInput;
import com.example.bankspringbatch.demo.domain.Entity.BankTransactionOutput;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private ItemReader<BankTransactionInput> bankTransactionItemReader;
    @Autowired
    private ItemWriter<BankTransactionInput> bankTransactionItemWriter;
    @Autowired
    private ItemProcessor<BankTransactionInput, BankTransactionOutput> bankTransactionItemProcessor;

    public Job bankJob(){
        Step step1 = stepBuilderFactory.get("step-load-data")
                .chunk(100)
                .reader(bankTransactionItemReader)
                .writer(bankTransactionItemWriter)
                .processor(bankTransactionItemProcessor)
                .build();
        Job job = jobBuilderFactory.get("job-load-data")
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
        return job;
    }

    @Bean
    public FlatFileItemReader<BankTransactionInput> bankTransactionItemReader(@Value("${inputFile}") Resource resource){
        FlatFileItemReader<BankTransactionInput> flatFileItemReader=new FlatFileItemReader<>();
        flatFileItemReader.setName("CSV-READER");
        //Skip the first line in the csv file
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        flatFileItemReader.setResource(resource);
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<BankTransactionInput> lineMapper(){
        DefaultLineMapper<BankTransactionInput> lineMapper=new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer= new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "accountId", "transactionDate", "strTransactionDate", "transactionType","transactionAmount");
        lineMapper.setLineTokenizer(lineTokenizer);
        BeanWrapperFieldSetMapper<BankTransactionInput> fieldSetMapper= new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(BankTransactionInput.class);
        fieldSetMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }


}
