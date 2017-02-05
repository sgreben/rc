package io.github.sgreben.rc.types;

import com.microsoft.z3.Sort;

public interface Type {
     Sort toZ3Sort();
}
