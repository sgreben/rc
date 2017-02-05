package io.github.sgreben.rc.builders;

import com.microsoft.z3.Context;
import io.github.sgreben.rc.TypeEnvironment;
import io.github.sgreben.rc.types.BoolType;
import io.github.sgreben.rc.types.IntType;
import io.github.sgreben.rc.types.RealType;

public class TypeBuilder {
    private final TypeEnvironment typeEnvironment;
    private final Context z3Context;

    public TypeBuilder(TypeEnvironment typeEnvironment, Context z3Context) {
        this.typeEnvironment = typeEnvironment;
        this.z3Context = z3Context;
    }

    public EnumTypeBuilder enumeration() {
        return new EnumTypeBuilder(typeEnvironment, z3Context);
    }

    public IntType integer() {
        return new IntType(z3Context);
    }

    public BoolType bool() {
        return new BoolType(z3Context);
    }

    public RealType real() {
        return new RealType(z3Context);
    }
}
