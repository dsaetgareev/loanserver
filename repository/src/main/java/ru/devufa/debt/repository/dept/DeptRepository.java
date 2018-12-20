package ru.devufa.debt.repository.dept;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.devufa.debt.entity.Dept;

import java.util.UUID;

@Repository
public interface DeptRepository extends JpaRepository<Dept, UUID> {
}
