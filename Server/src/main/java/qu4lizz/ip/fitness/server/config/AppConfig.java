package qu4lizz.ip.fitness.server.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import qu4lizz.ip.fitness.server.config.converters.ProgramImageListConverter;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);

        mapper.addConverter(new ProgramImageListConverter());

        return mapper;
    }
}

