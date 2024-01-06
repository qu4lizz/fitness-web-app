package qu4lizz.ip.fitness.server.config.converters;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import qu4lizz.ip.fitness.server.models.entities.ProgramImageEntity;

import java.util.List;

public class ProgramImageListConverter implements Converter<List<ProgramImageEntity>, ProgramImageEntity> {
    @Override
    public ProgramImageEntity convert(MappingContext<List<ProgramImageEntity>, ProgramImageEntity> context) {
        List<ProgramImageEntity> programImages = context.getSource();
        if (programImages != null && !programImages.isEmpty()) {
            return programImages.get(0);
        }
        return null;
    }
}

