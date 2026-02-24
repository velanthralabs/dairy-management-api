package com.velanthralabs.dairy.config;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Service
public class AuditService {

    @PersistenceContext
    private EntityManager entityManager;
    public List<?> getEntityHistory(Class<?> entityClass, Long id) {
        AuditReader reader = AuditReaderFactory.get(entityManager);

        return reader.createQuery()
                .forRevisionsOfEntity(entityClass, false, true)
                .add(AuditEntity.id().eq(id))
                .getResultList();
    }
}