package net.notfab.lindsey.shared.converters;

import org.json.JSONArray;

import javax.persistence.AttributeConverter;
import java.util.HashSet;
import java.util.Set;

public class SetStringConverter implements AttributeConverter<Set<Long>, String> {

    @Override
    public String convertToDatabaseColumn(Set<Long> attribute) {
        if (attribute == null) {
            return null;
        }
        JSONArray array = new JSONArray();
        attribute.forEach(array::put);
        return array.toString();
    }

    @Override
    public Set<Long> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        JSONArray array = new JSONArray(dbData);
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < array.length(); i++) {
            set.add(array.getLong(i));
        }
        return set;
    }

}
