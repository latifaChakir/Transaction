package com.example.gestionTask.repository;

import com.example.gestionTask.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class AccountRepository implements AutoCloseable {

    private final EntityManagerFactory emf;

    public AccountRepository() {
        this.emf = Persistence.createEntityManagerFactory("mySystem");
    }

    public Account save(Account account) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                if (account.getId() == null) {
                    entityManager.persist(account);
                } else {
                    entityManager.merge(account);
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw new RuntimeException(e);
            }
        }
        return account;
    }

public Account update(Account account) {
        try(EntityManager entityManager=emf.createEntityManager()) {
            EntityTransaction transaction =entityManager.getTransaction();
            try {
                transaction.begin();
                Account updatedAccount= entityManager.merge(account);
                transaction.commit();
                return updatedAccount;
            }catch (Exception e){
                if(transaction.isActive()){
                    transaction.rollback();
                }
                throw new RuntimeException(e);
            }
        }
}

public void delete(int accountId){
        try(EntityManager entityManager = emf.createEntityManager()){
            EntityTransaction transaction=entityManager.getTransaction();
            try{
                transaction.begin();
                Account getAccount=entityManager.find(Account.class,accountId);
                if(getAccount!=null){
                    entityManager.remove(getAccount);
                }
                transaction.commit();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
}

public Account findById(Long accountId){
try(EntityManager entityManager=emf.createEntityManager()){
    return  entityManager.find(Account.class,accountId);
}
}

public List<Account> findAll(){
        try(EntityManager entityManager=emf.createEntityManager()){
            return entityManager.createQuery("SELECT a FROM Account a",Account.class).getResultList();
        }
}


    @Override
    public void close() throws Exception {

    }
}
