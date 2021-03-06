
package io.github.sgreben.rc.types;

import com.microsoft.z3.Context;
import com.microsoft.z3.Sort;

public class IntType implements Type {
    private final Context context;

    public IntType(Context context) {
        this.context = context;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return context.hashCode();
    }

    public Sort toZ3Sort() {
        return context.getIntSort();
    }

    @Override
    public String toString() {
        return "IntType";
    }
}
