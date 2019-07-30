package cn.net.liaowei.sc.message.sender.controller;

import cn.net.liaowei.sc.message.common.CommonDTO;
import cn.net.liaowei.sc.message.sender.binder.StreamSink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiaoWei
 */
@Slf4j
@RestController
@RequestMapping("/stream")
@EnableBinding(StreamSink.class)
public class StreamController {
    private StreamSink streamSink;

    public StreamController(StreamSink streamSink) {
        this.streamSink = streamSink;
    }

    @PostMapping("/common/send")
    public void commonSend(@RequestBody CommonDTO commonDTO) {
        log.info("Common send: {}", commonDTO);
        this.streamSink.output().send(MessageBuilder.withPayload(commonDTO).build());
    }
}
