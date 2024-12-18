package ir.msob.jima.core.commons.util;

import com.google.common.collect.Sets;
import ir.msob.jima.core.commons.domain.SampleCriteria;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CriteriaUtilTest {

    @Test
    void testIdCriteriaForSingleID() {
        // Test creating a criteria object with an ID filter for a single ID
        Long id = 123L;
        SampleCriteria<Long> criteria = CriteriaUtil.idCriteria(SampleCriteria.class, id);

        assertNotNull(criteria);
        assertEquals(id, criteria.getId().getEq());
    }

    @Test
    void testIdCriteriaForCollectionOfIDs() {
        // Test creating a criteria object with an ID filter for a collection of IDs
        Set<Long> ids = new HashSet<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);

        SampleCriteria<Long> criteria = CriteriaUtil.idCriteria(SampleCriteria.class, ids);

        assertNotNull(criteria);
        assertEquals(Sets.newHashSet(ids), criteria.getId().getIn());
    }

    @Test
    void testIdCriteriaForArrayOfIDs() {
        // Test creating a criteria object with an ID filter for an array of IDs
        Long[] ids = {5L, 6L, 7L};
        SampleCriteria<Long> criteria = CriteriaUtil.idCriteria(SampleCriteria.class, ids);

        assertNotNull(criteria);
        assertEquals(Sets.newHashSet(ids), criteria.getId().getIn());
    }
}
