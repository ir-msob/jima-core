package ir.msob.jima.core.commons.util;

import com.google.common.collect.Sets;
import ir.msob.jima.core.commons.criteria.BaseCriteria;
import ir.msob.jima.core.commons.criteria.SampleCriteria;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CriteriaUtilTest {

    @Test
    void testIdCriteriaForSingleID() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // Test creating a criteria object with an ID filter for a single ID
        Long id = 123L;
        BaseCriteria<Long> criteria = CriteriaUtil.idCriteria(SampleCriteria.class, id);

        assertNotNull(criteria);
        assertEquals(id, criteria.getId().getEq());
    }

    @Test
    void testIdCriteriaForCollectionOfIDs() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // Test creating a criteria object with an ID filter for a collection of IDs
        Set<Long> ids = new HashSet<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);

        BaseCriteria<Long> criteria = CriteriaUtil.idCriteria(SampleCriteria.class, ids);

        assertNotNull(criteria);
        assertEquals(Sets.newHashSet(ids), criteria.getId().getIn());
    }

    @Test
    void testIdCriteriaForArrayOfIDs() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // Test creating a criteria object with an ID filter for an array of IDs
        Long[] ids = {5L, 6L, 7L};
        BaseCriteria<Long> criteria = CriteriaUtil.idCriteria(SampleCriteria.class, ids);

        assertNotNull(criteria);
        assertEquals(Sets.newHashSet(ids), criteria.getId().getIn());
    }
}
