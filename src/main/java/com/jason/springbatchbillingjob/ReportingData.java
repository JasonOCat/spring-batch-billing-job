package com.jason.springbatchbillingjob;

public record ReportingData(
        BillingData billingData,
        double billingTotal
) {
}