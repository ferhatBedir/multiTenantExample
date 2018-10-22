package com.ferhat.multitenant;

import org.apache.commons.lang3.StringUtils;

public class TenantContext {

    private static final String DEFAULT_TENANT = "default";

    private static final ThreadLocal<String> tenant = new ThreadLocal<>();

    private TenantContext() {

    }

    public static String getTenant() {
        String tenantCode = tenant.get();
        if (StringUtils.isEmpty(tenantCode)) {
            tenant.set(DEFAULT_TENANT);
            tenantCode = tenant.get();
        }
        return tenantCode;
    }

    public static void setDefaultTenant() {
        tenant.set(DEFAULT_TENANT);
    }

    public static void setTenant(String tenantName) {
        tenant.set(tenantName);
    }
}
