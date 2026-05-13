package az.ingress.oca46.MyFirstSpringProject.util;

import java.util.Random;

public interface Helper {
    static long generateId(){
        return new Random().nextLong(9999);
    }
}
