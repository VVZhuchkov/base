package com.github.vvzhuchkov.base.dao.converter;

import com.github.vvzhuchkov.base.dao.entity.RestitutionEntity;
import com.github.vvzhuchkov.base.model.Restitution;

public class RestitutionConverter {
    public static Restitution fromEntity(RestitutionEntity restitution) {
        if (restitution == null) {
            return null;
        }
        return new Restitution(
                restitution.getNumber(),
                restitution.getLogin(),
                restitution.getComment(),
                restitution.getRating(),
                restitution.getStatus());
    }

    public static RestitutionEntity toEntity(Restitution restitution) {
        if (restitution == null) {
            return null;
        }
        final RestitutionEntity restitutionEntity = new RestitutionEntity();
        restitutionEntity.setNumber(restitution.getNumber());
        restitutionEntity.setLogin(restitution.getLogin());
        restitutionEntity.setComment(restitution.getComment());
        restitutionEntity.setRating(restitution.getRating());
        restitutionEntity.setStatus(restitution.getStatus());
        return restitutionEntity;
    }
}
