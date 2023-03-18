package io.bayrktlihn.employeecrudapp.repositories;

import java.util.List;

public interface Repository<TENTITY, TID> {


    List<TENTITY> findAll();

    TENTITY findById(TID id);

    void deleteById(TID id);

    TENTITY save(TENTITY tentity);

}
