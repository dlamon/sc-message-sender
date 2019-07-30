package cn.net.liaowei.sc.message.sender.binder;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * @author LiaoWei
 */
@Component
public interface StreamSink {
    String INPUT = "StreamQueue";

    @Output(StreamSink.INPUT)
    MessageChannel output();
}
