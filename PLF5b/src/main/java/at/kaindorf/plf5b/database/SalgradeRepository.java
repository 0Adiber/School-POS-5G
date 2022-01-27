package at.kaindorf.plf5b.database;

import at.kaindorf.plf5b.pojos.Salgrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalgradeRepository extends JpaRepository<Salgrade, Integer> {

    Integer getGrade(Integer empNo);

}