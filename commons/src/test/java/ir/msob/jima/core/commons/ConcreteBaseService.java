package ir.msob.jima.core.commons;

import ir.msob.jima.core.commons.domain.SampleCriteria;
import ir.msob.jima.core.commons.domain.SampleDomain;
import ir.msob.jima.core.commons.domain.SampleDto;
import ir.msob.jima.core.commons.repository.SampleRepository;
import ir.msob.jima.core.commons.service.SampleService;

public class ConcreteBaseService extends SampleService<String, ConcreteBaseUser, SampleDomain<String>, SampleDto<String>, SampleCriteria<String>, SampleRepository<String, SampleDomain<String>, SampleCriteria<String>>> {
}
