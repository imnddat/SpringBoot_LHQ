package com.datnd.onetomany;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    public List<Person> findByFirstName(String firstName);
    public List<Person> findByFirstNameContaining(String keyword);
    public List<Person> findByFirstNameContainingAndLastNameContaining (String keyword1, String keyword2);

    //do là jpa nên map với entity (JPQL)
    @Query("Select p from Person p where p.firstName like ?1 or p.lastName like ?1")
    public List<Person> findByFirstNameContainingOrLastNameContaining (String keyword);
    //@Query (value = "select * from person p where p.first_name like ?1 and p.last_name like ?1", nativeQuery = true) : trường hợp câu lệnh từ sql thì thêm nativeQuery

}
