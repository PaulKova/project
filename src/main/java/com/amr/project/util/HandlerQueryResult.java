package com.amr.project.util;

import javax.persistence.*;

public class HandlerQueryResult {

//    NoResultException
//    NonUniqueResultException
//            IllegalStateException
//    QueryTimeoutException
//            TransactionRequiredException
//    PessimisticLockException
//            LockTimeoutException
//    PersistenceException
    public static <T> T wrapGetSingleResult(TypedQuery<T> q) {
        try {
            return q.getSingleResult();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}

