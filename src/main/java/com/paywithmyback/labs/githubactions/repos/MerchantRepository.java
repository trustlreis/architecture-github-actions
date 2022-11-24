package com.paywithmyback.labs.githubactions.repos;

import com.paywithmyback.labs.githubactions.data.Merchant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MerchantRepository extends CrudRepository<Merchant, Long> {

    Merchant findByName(@Param("name") String name);

}
