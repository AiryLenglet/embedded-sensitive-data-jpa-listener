package me.lenglet.entity;

import org.hibernate.CustomEntityDirtinessStrategy;
import org.hibernate.Session;
import org.hibernate.persister.entity.EntityPersister;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class SensitiveDataDirtinessStrategy implements CustomEntityDirtinessStrategy {
    @Override
    public boolean canDirtyCheck(Object entity, EntityPersister persister, Session session) {
        return mnpiFlagHasChanged(entity);
    }

    @Override
    public boolean isDirty(Object entity, EntityPersister persister, Session session) {
        return mnpiFlagHasChanged(entity);
    }

    @Override
    public void resetDirty(Object entity, EntityPersister persister, Session session) {

    }

    @Override
    public void findDirty(Object entity, EntityPersister persister, Session session, DirtyCheckContext dirtyCheckContext) {

    }

    private static boolean mnpiFlagHasChanged(Object entity) {
        try {
            final var properties = Introspector.getBeanInfo(entity.getClass()).getPropertyDescriptors();
            return Arrays.stream(properties)
                    .filter(propertyDescriptor -> propertyDescriptor.getPropertyType().isAssignableFrom(SensitiveData.class))
                    .map(PropertyDescriptor::getReadMethod)
                    .map(method -> invoke(method, entity))
                    .map(SensitiveData::getParent)
                    .anyMatch(SensitiveContainer::mnpiFlagHasChanged);
        } catch (IntrospectionException e) {
            throw new IllegalStateException(e);
        }
    }

    private static SensitiveData<?> invoke(Method method, Object entity) {
        try {
            return (SensitiveData<?>) method.invoke(entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }
}
