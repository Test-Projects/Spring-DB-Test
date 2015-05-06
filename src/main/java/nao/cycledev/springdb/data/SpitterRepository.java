package nao.cycledev.springdb.data;

import nao.cycledev.springdb.model.Spitter;

public interface SpitterRepository {

    void addSpitter(Spitter spitter);

    Spitter findSpitter(long id);

}
