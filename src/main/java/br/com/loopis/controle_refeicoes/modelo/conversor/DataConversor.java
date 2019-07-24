package br.com.loopis.controle_refeicoes.modelo.conversor;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 12/06/2019, 11:23:59
 */
@Converter(autoApply = false)
public class DataConversor implements AttributeConverter<LocalDate,Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        if (attribute == null) {
            return null;
        }
        return Date.valueOf(attribute);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        if (dbData == null) {
            return null;
        }

        return dbData.toLocalDate();
    }

}
