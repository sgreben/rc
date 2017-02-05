package io.github.sgreben.rc.declarations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.github.sgreben.rc.Context;
import io.github.sgreben.rc.builders.EnumTypeBuilder;
import io.github.sgreben.rc.types.EnumType;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TypeDeclaration {
    private List<String> values;

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public EnumType compile(Context context, String name) {
        EnumTypeBuilder builder = context.buildType().enumeration().withName(name);
        for(String value: values) {
            builder.withValue(value);
        }
        return builder.build();
    }

    public static TypeDeclaration load(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(file, TypeDeclaration.class);
    }

    @Override
    public String toString() {
        return "TypeDeclaration{" +
                "values=" + values +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeDeclaration that = (TypeDeclaration) o;

        return values != null ? values.equals(that.values) : that.values == null;
    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }
}
