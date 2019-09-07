package br.com.loopis.controle_refeicoes.modelo.conversor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 12/06/2019, 11:23:59
 */
@Converter(autoApply = false)
public class DataTimeConversor implements AttributeConverter<LocalDateTime, Timestamp> {
     
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
        return locDateTime == null ? null : Timestamp.valueOf(locDateTime);
    }
 
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        return sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime();
    }
}
