package com.nagarro.pos.daosimpl;

import java.io.Serializable;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.nagarro.pos.daos.GenericDAO;

@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class GenericDAOImpl < T extends Serializable >
 			extends AbstractJpaDAO< T > implements GenericDAO< T >{

}
