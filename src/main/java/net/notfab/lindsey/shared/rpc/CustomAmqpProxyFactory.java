package net.notfab.lindsey.shared.rpc;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.amqp.remoting.client.AmqpProxyFactoryBean;
import org.springframework.remoting.RemoteProxyFailureException;
import org.springframework.remoting.support.RemoteInvocation;
import org.springframework.remoting.support.RemoteInvocationResult;

import java.util.Arrays;
import java.util.UUID;

public class CustomAmqpProxyFactory extends AmqpProxyFactoryBean {

    @Override
    @SuppressWarnings("ConstantConditions")
    public Object invoke(MethodInvocation invocation) throws Throwable {
        RemoteInvocation remoteInvocation = getRemoteInvocationFactory().createRemoteInvocation(invocation);

        Object rawResult;
        if (getRoutingKey() == null) {
            // Use the template's default routing key
            rawResult = this.getAmqpTemplate().convertSendAndReceive(remoteInvocation);
        } else {
            rawResult = this.getAmqpTemplate().convertSendAndReceive(this.getRoutingKey(), remoteInvocation, message -> {
                message.getMessageProperties().setCorrelationId(UUID.randomUUID().toString());
                return message;
            });
        }

        if (rawResult == null) {
            throw new RemoteProxyFailureException("No reply received from '" +
                    remoteInvocation.getMethodName() +
                    "' with arguments '" +
                    Arrays.asList(remoteInvocation.getArguments()) +
                    "' - perhaps a timeout in the template?", null);
        } else if (!(rawResult instanceof RemoteInvocationResult)) {
            throw new RemoteProxyFailureException("Expected a result of type "
                    + RemoteInvocationResult.class.getCanonicalName() + " but found "
                    + rawResult.getClass().getCanonicalName(), null);
        }

        RemoteInvocationResult result = (RemoteInvocationResult) rawResult;
        return result.recreate();
    }

}
