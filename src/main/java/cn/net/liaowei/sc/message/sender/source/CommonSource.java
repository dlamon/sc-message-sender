package cn.net.liaowei.sc.message.sender.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * @author LiaoWei
 */
@Component
public interface CommonSource {
    String OUTPUT = "commonOutput";

    @Output(CommonSource.OUTPUT)
    MessageChannel output();
}
