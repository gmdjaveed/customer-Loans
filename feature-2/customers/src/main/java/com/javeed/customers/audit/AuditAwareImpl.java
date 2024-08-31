package com.javeed.customers.audit;

import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

import static com.javeed.customers.constants.CustomerConstants.*;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(CUSTOMER_AUDIT);
    }
}
