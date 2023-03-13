package com.synesis.hrmis.repository;

import com.synesis.hrmis.domain.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionsRepository extends JpaRepository<Action,Long> {
    Action findByName(String name);
}
