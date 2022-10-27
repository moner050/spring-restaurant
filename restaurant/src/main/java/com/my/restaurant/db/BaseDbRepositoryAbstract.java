package com.my.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class BaseDbRepositoryAbstract<T extends BaseDbEntity> implements BaseDbRepository<T> {
    // 데이터를 저장할 ArrayList
    private final List<T> db = new ArrayList<>();
    private int index = 0;

    @Override
    public Optional<T> findById(int index) {
        // getIndex 의 값이 index 의 값과 동일하다고 한것중 첫번째를 찾기
        return db.stream().filter(it -> it.getIndex() == index).findFirst();
    }

    public boolean existByTitle(String title) {
         if(db.stream().anyMatch(it -> it.getTitle().equals(title))){
             return true;
         }
         return false;
    }

    @Override
    public T save(T entity) {
        Optional<T> optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();
        // db에 데이터가 없는 경우(insert)
        if(optionalEntity.isEmpty()) {
            index++;                                                // 인덱스를 증가시켜주고
            entity.setIndex(index);                                 // 인덱스를 세팅해준다
        }
        // db에 데이터가 있는 경우(update)
        else {
            Integer preIndex = optionalEntity.get().getIndex();     // 이미 있던 데이터의 인덱스를 가져와서
            entity.setIndex(preIndex);                              // 인덱스를 세팅해주고

            deleteById(preIndex);                                   // 기존에 있던 데이터를 지운다
        }
        db.add(entity);                                             // entity 를 DB 에 저장 후
        return entity;                                              // 타입을 리턴시켜준다.
    }

    @Override
    public T deleteById(int index) {
        Optional<T> optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();

        // 만약 데이터가 있는경우
        if(optionalEntity.isPresent()) {
            // 해당 object 와 동일한 해당 Entity 를 찾아서 지우고 리턴시켜준다.
            T entity = optionalEntity.get();
            db.remove(entity);
            return entity;
        } else {
            // 없으면 null 리턴
            return null;
        }
    }

    @Override
    public List<T> findAll() {
        // db 에 있는 모든 내용을 return
        return db;
    }
}
