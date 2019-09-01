package br.com.loopis.calendario.ifpb.convert;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@FacesConverter(value="localDateConverter")
public class FacesConvertLocalDate implements Converter {

//    @Override
//    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        Locale BRAZIL = new Locale("pt", "BR");
//        return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(BRAZIL));
//    }
//
//    @Override
//    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        LocalDate dateValue = (LocalDate) value;
//
//        return dateValue.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//
//    }

    @Override
    public Object getAsObject(
            FacesContext context,
            UIComponent component,
            String value) {
        if(value.isEmpty()){
            System.out.println(value);
            return null;
        }
        return LocalDate.parse(value,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Object value) {
        LocalDate dateValue = (LocalDate) value;
        return dateValue.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));//value.toString();
    }

}