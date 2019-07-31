package cn.net.liaowei.sc.message.sender.controller;

import cn.net.liaowei.sc.message.common.CommonDTO;
import cn.net.liaowei.sc.message.common.OrderDTO;
import cn.net.liaowei.sc.message.sender.source.CommonSource;
import cn.net.liaowei.sc.message.sender.source.OrderSource;
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
@EnableBinding({CommonSource.class, OrderSource.class})
public class StreamController {
    private CommonSource commonSource;
    private OrderSource orderSource;

    public StreamController(CommonSource commonSource, OrderSource orderSource) {
        this.commonSource = commonSource;
        this.orderSource = orderSource;
    }

    @PostMapping("/common/send")
    public void commonSend(@RequestBody CommonDTO commonDTO) {
        log.info("Common stream send: {}", commonDTO);
        this.commonSource.output().send(MessageBuilder.withPayload(commonDTO).build());
    }

    @PostMapping("/order/send")
    public void orderSend(@RequestBody OrderDTO orderDTO) {
        log.info("Order stream send: {}", orderDTO);
        this.orderSource.output().send(MessageBuilder.withPayload(orderDTO).setHeader("type", orderDTO.getOrderType()).build());
    }
}
