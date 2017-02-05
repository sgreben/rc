package io.github.sgreben.rc;

import com.microsoft.z3.FuncDecl;
import io.github.sgreben.rc.types.EnumType;
import io.github.sgreben.rc.values.EnumValue;

import java.util.HashMap;
import java.util.Map;

public class TypeEnvironment {
    Map<String, EnumValue> enumValues;
    Map<String, EnumType> enumTypes;
    private Map<FuncDecl, EnumValue> enumValuesOfFuncDecls;

    public TypeEnvironment() {
        this.enumValues = new HashMap<>();
        this.enumTypes = new HashMap<>();
        this.enumValuesOfFuncDecls = new HashMap<>();
    }

    public void register(EnumType enumType) {
        enumTypes.put(enumType.getName(), enumType);
        for (String enumValueName : enumType.values()) {
            EnumValue enumValue = enumType.getValue(enumValueName);
            enumValuesOfFuncDecls.put(enumValue.getFuncDecl(), enumValue);
            enumValues.put(enumValueName, enumValue);
        }
    }

    public EnumValue enumValue(String name) {
        return enumValues.get(name);
    }

    public EnumValue enumValueOfFuncDecl(FuncDecl funcDecl) {
        return enumValuesOfFuncDecls.get(funcDecl);
    }

    public EnumType enumType(String name) {
        return enumTypes.get(name);
    }
}
