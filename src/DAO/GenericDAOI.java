package DAO;

import java.util.List;

public interface GenericDAOI<T> {

        public void add(T e);
        public void delete(int id);
        public void update(T e);
        public List<T> display();
    }

