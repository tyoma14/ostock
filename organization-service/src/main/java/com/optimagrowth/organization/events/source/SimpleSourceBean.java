package com.optimagrowth.organization.events.source;

import com.optimagrowth.organization.events.model.OrganizationChangeModel;
import com.optimagrowth.organization.utils.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by Artyom Zheltyshev on 10.06.2025
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SimpleSourceBean {

    private final StreamBridge streamBridge;

    public void publishOrganizationChange(ActionEnum action, String organizationId) {
        log.debug("Sending Kafka message {} for Organization Id: {}", action, organizationId);
        OrganizationChangeModel change = new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                action.toString(),
                organizationId,
                UserContext.getCorrelationId()
        );
        Message<OrganizationChangeModel> message = MessageBuilder
                .withPayload(change)
                .build();
        streamBridge.send("output", message);
    }

}
