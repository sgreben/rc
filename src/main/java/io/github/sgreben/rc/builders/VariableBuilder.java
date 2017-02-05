package io.github.sgreben.rc.builders;

import com.microsoft.z3.Context;
import io.github.sgreben.rc.Variable;
import io.github.sgreben.rc.types.Type;

public class VariableBuilder {
    private final Context z3Context;
    private final String name;

    public VariableBuilder(Context z3Context, String name) {
        this.z3Context = z3Context;
        this.name = name;
    }

    public Variable ofType(Type type) {
        return new Variable(z3Context, name, type);
    }
}
