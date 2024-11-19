package ir.msob.jima.core.commons;

import ir.msob.jima.core.commons.domain.SampleDomain;
import ir.msob.jima.core.commons.repository.SampleRepository;
import ir.msob.jima.core.commons.service.SampleService;

public class ConcreteBaseService extends SampleService<String, ConcreteBaseUser, SampleDomain<String>, SampleRepository<String, ConcreteBaseUser, SampleDomain<String>>> {
}
