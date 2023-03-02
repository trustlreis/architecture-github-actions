package com.paywithmyback.labs.githubactions.repos;

import com.paywithmyback.labs.githubactions.data.Consumer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ConsumerRepository extends CrudRepository<Consumer, Long> {

    Consumer findByName(@Param("name") String name);

}
